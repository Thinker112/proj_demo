package com.example.asynchronous_demo.tcpdump;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("/capture")
public class CaptureController {

    private static final String FILE_DIRECTORY = "/tmp/tcpdump"; // 文件存储目录

    // 任务缓存
    private final Map<String, CaptureTask> taskCache = new ConcurrentHashMap<>();

    // 调度线程池（用于执行超时回调）
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    // 启动抓包接口
    @GetMapping("/start")
    public ResponseEntity<String> startCapture(@RequestParam(defaultValue = "60") int timeoutSeconds,
                                               @RequestParam(value = "cmd", required = false) String cmd) throws IOException {

        // 参数校验
        if (timeoutSeconds < 10 || timeoutSeconds > 360) {
            throw new IllegalArgumentException("超时时间必须在10-360秒之间");
        }

        String taskId = UUID.randomUUID().toString();
        log.info("[capture]-taskId: {}, start", taskId);
        // 创建抓包文件路径
        Path outputDir = Paths.get(FILE_DIRECTORY);
        if (!Files.exists(outputDir)) {
            Files.createDirectories(outputDir); // 确保目录存在
        }
        Path outputPath = outputDir.resolve("capture_" + taskId + ".pcap");
        ProcessBuilder processBuilder;
        // 启动抓包进程
        if (StringUtils.hasText(cmd)) {
            processBuilder = new ProcessBuilder(cmd, outputPath.toString());
        }else {
            processBuilder = new ProcessBuilder("tcpdump",
                    "-U", // 启用 packet-buffered 模式
                    "-w", outputPath.toString());
        }

        Process process = processBuilder.start();
        // 打印完整命令
        String command = String.join(" ", processBuilder.command());
        log.info("[capture]-taskId: {}, command: {}", taskId, command);

        // 定义超时回调函数
        Consumer<String> timeoutCallback = taskIdParam -> {
            CaptureTask task = taskCache.get(taskIdParam);
            if (task != null && task.getProcess().isAlive()) {
                log.info("[capture]-taskId: {}, 任务超时自动终止", taskIdParam);
                task.getProcess().destroy();
                taskCache.remove(taskIdParam);
            }
        };

        // 调度超时检查
        ScheduledFuture<?> timeoutFuture = scheduler.schedule(
                () -> timeoutCallback.accept(taskId),
                timeoutSeconds,
                TimeUnit.SECONDS
        );

        // 读取errorStream 防止缓冲区满，导致进程挂起
        CompletableFuture.runAsync(() -> {
            try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info("[tcpdump],{}", line);
                }
            } catch (IOException e) {
                log.error("[tcpdump],{}", e.getMessage());
            }
        }, scheduler);

        // 保存任务信息
        taskCache.put(taskId, new CaptureTask(process, outputPath, timeoutFuture));

        return ResponseEntity.ok(taskId);
    }

    // 停止抓包接口
    @GetMapping("/stop")
    public ResponseEntity<Resource> stopCapture(@RequestParam String taskId) throws InterruptedException {
        log.info("[capture]-taskId: {}, stop", taskId);

        CaptureTask task = taskCache.get(taskId);
        if (task == null) {
            log.info("[capture]-taskId: {}, not found", taskId);
            return ResponseEntity.notFound().build();
        }

        // 取消超时回调
        task.timeoutFuture().cancel(true);

        // 终止进程
        Process process = task.getProcess();
        if (process.isAlive()) {
            // 1. 尝试终止，使资源正确释放
            process.destroy(); // 发送 SIGTERM
            boolean exited = process.waitFor(3, TimeUnit.SECONDS);

            // 2. 超时后强制终止
            if (!exited) {
                process.destroyForcibly(); // 发送 SIGKILL
                log.info("[capture]-taskId: {}, 进程未在超时时间内退出，已强制终止", taskId);
            }
        }

        // 构建可下载资源
        File file = task.getOutputPath().toFile();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(task.getOutputPath()));
    }

    @GetMapping("/info")
        public ResponseEntity<List<Map<String, Object>>> getFileInfo(@RequestParam(value = "date", required = false) String dateStr) {
        File folder = new File(FILE_DIRECTORY);
        if (!folder.exists() || !folder.isDirectory()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        List<Map<String, Object>> fileList = new ArrayList<>();
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                LocalDate fileDateTime = Instant.ofEpochMilli(file.lastModified()).atZone(ZoneId.systemDefault()).toLocalDate();
                boolean equal;
                if (StringUtils.hasText(dateStr)) {
                    LocalDate dateS = LocalDate.parse(dateStr);
                    equal = fileDateTime.isEqual(dateS);
                }else {
                    equal = fileDateTime.isEqual(LocalDate.now());
                }

                if (file.isFile() && equal) { // 按日期匹配文件名
                    Map<String, Object> fileInfo = new HashMap<>();
                    fileInfo.put("fileName", file.getName());
                    fileInfo.put("size", String.format("%.2f MB", file.length() / (1024.0 * 1024)));
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
                    fileInfo.put("creationDate", localDateTime.toString());
                    fileList.add(fileInfo);
                }
            }
        }

        return ResponseEntity.ok(fileList);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
        try {
            Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 内部任务封装类
    private static class CaptureTask {
        private final Process process;
        private final Path outputPath;
        private final ScheduledFuture<?> timeoutFuture;

        public CaptureTask(Process process, Path outputPath, ScheduledFuture<?> timeoutFuture) {
            this.process = process;
            this.outputPath = outputPath;
            this.timeoutFuture = timeoutFuture;
        }

        public Process getProcess() { return process; }
        public Path getOutputPath() { return outputPath; }
        public ScheduledFuture<?> timeoutFuture() { return timeoutFuture; }
    }
}