package com.example.asynchronous_demo.tcpdump;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("/capture")
public class CaptureController {

    // 任务缓存
    private final Map<String, CaptureTask> taskCache = new ConcurrentHashMap<>();

    // 调度线程池（用于执行超时回调）
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    // 启动抓包接口
    @PostMapping("/start")
    public ResponseEntity<String> startCapture(@RequestParam(defaultValue = "60") int timeoutSeconds) throws IOException {

        // 参数校验
        if (timeoutSeconds < 10 || timeoutSeconds > 3600) {
            throw new IllegalArgumentException("超时时间必须在10-3600秒之间");
        }

        String taskId = UUID.randomUUID().toString();
        log.info("[capture]-taskId: {}, start", taskId);
        Path outputPath = Paths.get(System.getProperty("java.io.tmpdir"), "capture_" + taskId + ".pcap");

        // 启动抓包进程
        ProcessBuilder processBuilder = new ProcessBuilder("sudo", "tcpdump",
                "-U", // 启用 packet-buffered 模式
                "-w", outputPath.toString());
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
                taskCache.remove(taskId);
            }
        };

        // 调度超时检查
        ScheduledFuture<?> timeoutFuture = scheduler.schedule(
                () -> timeoutCallback.accept(taskId),
                timeoutSeconds,
                TimeUnit.SECONDS
        );

//        CompletableFuture.runAsync(() -> {
//            while (process.isAlive()) {
//                // 循环监控进程,监控逻辑
//            }
//        }, scheduler);

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
            process.destroy();
            process.waitFor(5, TimeUnit.SECONDS);
        }

        // 构建可下载资源
        File file = task.getOutputPath().toFile();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(task.getOutputPath()));
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