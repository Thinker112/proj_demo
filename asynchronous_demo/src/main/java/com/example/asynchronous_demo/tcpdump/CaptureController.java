package com.example.asynchronous_demo.tcpdump;

/**
 * @author yueyubo
 * @date 2025-02-28
 */
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.Consumer;

@RestController
public class CaptureController {

    // 任务缓存（线程安全）
    private final Map<String, CaptureTask> taskCache = new ConcurrentHashMap<>();

    // 调度线程池（用于执行超时回调）
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    // 启动抓包接口（带超时自动终止）
    @PostMapping("/capture/start")
    public ResponseEntity<String> startCapture(
            @RequestParam(defaultValue = "30") int timeoutSeconds) throws IOException {

        // 参数校验
        if (timeoutSeconds < 10 || timeoutSeconds > 3600) {
            throw new IllegalArgumentException("超时时间必须在10-3600秒之间");
        }

        String taskId = UUID.randomUUID().toString();
        Path outputPath = Paths.get(System.getProperty("java.io.tmpdir"), "capture_" + taskId + ".pcap");

        // 启动抓包进程
        Process process = new ProcessBuilder(
                "sudo", "tcpdump", "-w", outputPath.toString()
        ).start();

        // 定义超时回调函数
        Consumer<String> timeoutCallback = taskIdParam -> {
            CaptureTask task = taskCache.get(taskIdParam);
            if (task != null && task.getProcess().isAlive()) {
                System.out.println("任务超时自动终止: " + taskIdParam);
                task.getProcess().destroy();
                cleanupTask(taskIdParam, task.getOutputPath());
            }
        };

        // 调度超时检查
        ScheduledFuture<?> scheduledFuture = scheduler.schedule(
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
        taskCache.put(taskId, new CaptureTask(process, outputPath, scheduledFuture));

        return ResponseEntity.ok(taskId);
    }

    // 停止抓包接口
    @GetMapping("/capture/stop")
    public ResponseEntity<Resource> stopCapture(@RequestParam String taskId)
            throws IOException, InterruptedException {

        CaptureTask task = taskCache.get(taskId);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        // 取消超时回调
        task.getScheduledFuture().cancel(true);

        // 终止进程
        Process process = task.getProcess();
        if (process.isAlive()) {
            process.destroy();
            process.waitFor(5, TimeUnit.SECONDS);
        }

        // 构建可下载资源
        File file = task.getOutputPath().toFile();
        Resource resource = createAutoDeleteResource(file, taskId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // 创建自动删除的Resource（带缓存清理）
    private Resource createAutoDeleteResource(File file, String taskId) {
        return new FileSystemResource(file) {
            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file) {
                    @Override
                    public void close() throws IOException {
                        super.close();
                        cleanupTask(taskId, file.toPath());
                    }
                };
            }
        };
    }

    // 清理任务资源
    private void cleanupTask(String taskId, Path outputPath) {
        try {
            Files.deleteIfExists(outputPath);
        } catch (IOException e) {
            System.err.println("文件删除失败: " + outputPath);
        }
        taskCache.remove(taskId);
        System.out.println("清理任务: " + taskId);
    }

    // 内部任务封装类
    private static class CaptureTask {
        private final Process process;
        private final Path outputPath;
        private final ScheduledFuture<?> scheduledFuture;

        public CaptureTask(Process process, Path outputPath, ScheduledFuture<?> scheduledFuture) {
            this.process = process;
            this.outputPath = outputPath;
            this.scheduledFuture = scheduledFuture;
        }

        // Getters
        public Process getProcess() { return process; }
        public Path getOutputPath() { return outputPath; }
        public ScheduledFuture<?> getScheduledFuture() { return scheduledFuture; }
    }
}