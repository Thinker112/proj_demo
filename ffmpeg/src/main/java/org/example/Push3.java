package org.example;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;

import java.util.Timer;
import java.util.TimerTask;

import static org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_AAC;
import static org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_YUV420P;

/**
 * @Description
 * @Author @SHIXIANGLONG
 * @Date 2022/6/1 13:48
 */
public class Push3 {
    private static String filePath = "D:\\12345.mp4";
    private static String VideoStream = "rtsp://127.0.0.1:554/live/stream";
    private static Timer timer = new Timer();
    private static volatile long time = 0;
    public static void main(String[] args) throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception, InterruptedException {
        avutil.av_log_set_level(avutil.AV_LOG_INFO);
        FFmpegLogCallback.set();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time = System.currentTimeMillis();
            }
        }, 0,1000);

        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath);

        grabber.start();
        int frameRate = (int)grabber.getFrameRate();
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(VideoStream, grabber.getImageWidth(), grabber.getImageHeight(),grabber.getAudioChannels());
        recorder.setInterleaved(true);

        recorder.setOption("hwaccel", "cuvid");
        //缓存
        recorder.setOption("buffer_size", "1024000");
        //超时时间
        recorder.setOption("stimeout", "20000000");
        //rtsp tcp
        recorder.setOption("rtsp_transport", "tcp");
        //降低编码延时
        recorder.setVideoOption("tune", "zerolatency");
        //提升编码速度
        // video options //
        recorder.setVideoOption("preset", "ultrafast");
        recorder.setVideoOption("crf", "40");
        recorder.setPixelFormat(AV_PIX_FMT_YUV420P);
        recorder.setVideoBitrate(grabber.getVideoBitrate());
        recorder.setVideoCodecName("h264_cuvid");
        recorder.setAudioCodec(AV_CODEC_ID_AAC);
        recorder.setAudioBitrate(grabber.getAudioBitrate());
        recorder.setFormat("rtsp");
        // 变为两倍，也可用原来的
        recorder.setFrameRate(frameRate*2);
        recorder.setGopSize(frameRate*2);
        recorder.setMaxDelay(500000);
        recorder.start();
        push(grabber,recorder,1000/(int)grabber.getFrameRate()/10);
    }

    private static void push(FFmpegFrameGrabber grabber, FFmpegFrameRecorder recorder, int interVal ) throws FrameGrabber.Exception, FFmpegFrameRecorder.Exception, InterruptedException {

        System.out.println(interVal+"---->");
        Frame frame;
        int count = 0;
        while (null!=(frame=grabber.grab())) {
            if (0L==time) {
                time = System.currentTimeMillis();
            }
            if(recorder.getTimestamp() == 0){
                // 时间戳
                recorder.setTimestamp(1000 * (System.currentTimeMillis() - time));
            }

            if((frame.image == null || frame.image.length == 0) && (frame.samples == null || frame.samples.length == 0)  && (frame.data == null || frame.data.array().length == 0)){
                count++;
                System.out.println("空包");
                continue;
            }
            /**
             * 此处对应上面的 recorder.setFrameRate(frameRate*2); 这样写出来的效果还可以， record一次有种抽帧的感觉
             * recorder.recordPacket(pkt) 这个是源数据不做处理， frame可以处理后再推，比如加水印
             */
            recorder.record(frame);
            recorder.record(frame);
            frame.close();
            // 停顿一下再推送,不然可能会有点快
//            Thread.sleep(interVal);
        }
        grabber.restart();
        System.out.println(count);
        push(grabber, recorder, interVal);
    }

}
