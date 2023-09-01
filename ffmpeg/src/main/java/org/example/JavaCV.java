package org.example;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;


@Slf4j
public class JavaCV {

    public static void main(String[] args) throws Exception {
        String inputPath = "D:\\gopro.mp4";
        String outputPath = "rtsp://127.0.0.1:554/live/stream";

        FFmpegLogCallback.set();
        //打开视频文件并获取视频流信息
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputPath);
        grabber.start();
        AVFormatContext inputFormatContext = grabber.getFormatContext();
        AVCodecParameters inputCodecParameters = inputFormatContext.streams(0).codecpar();

        //创建一个视频编码器，并将其连接到网络连接
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputPath, grabber.getImageWidth(), grabber.getImageHeight());
        recorder.setFrameRate(grabber.getFrameRate());
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);

        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        recorder.setAudioChannels(2);//1 Mono单声道，2 立体声
        recorder.setFormat("rtsp");
        recorder.start();

        Frame frame;
//        BlockingQueue<Frame> frameQueue = new LinkedBlockingQueue<>();
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long prevTimestamp = -1;
//        long frameDelay = (long) (1000.0 / frameRate);

        long frameInterval = Math.round(1000.0 / grabber.getFrameRate());
        long startTime = 0;
        while ((frame = grabber.grab()) != null) {
//            long decodingTimestamp = frame.timestamp / 1000; //解码时间戳,毫秒
//
//            log.info("currentTimestamp: {}", decodingTimestamp);
//            if (prevTimestamp != -1 && decodingTimestamp < prevTimestamp) {//校验解码时间戳是否单调递增
//                log.error("prevTimestamp: {}, currentTimestamp: {}", prevTimestamp, decodingTimestamp);
//                continue;
//            }
//            prevTimestamp = decodingTimestamp;

//            frameQueue.offer(frame);

//            executor.schedule(() -> {
//                try {
//                    Frame frame1 = frameQueue.take();
//                    log.info("queueSize: " + frameQueue.size());

                    recorder.record(frame);
//
//                } catch (FFmpegFrameRecorder.Exception | InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }, decodingTimestamp, TimeUnit.MILLISECONDS);
        }

//        executor.shutdown();
//        executor.awaitTermination(10, TimeUnit.SECONDS); // 等待任务完成最多 10 秒

        //停止视频编码器和视频读取器，以及释放所有相关资源
//        if (frameQueue.size() == 0) {
            grabber.stop();
            grabber.release();

            recorder.stop();
            recorder.release();
//        }

    }
}
