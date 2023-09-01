package org.example;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.util.concurrent.ArrayBlockingQueue;

public class VideoToRTSPStreamer {
    public static void main(String[] args) throws FFmpegFrameGrabber.Exception {
        String videoFilePath = "D:\\gopro.mp4"; // Update this with your video file path
        String rtspUrl = "rtsp://127.0.0.1:554/live/stream"; // RTSP server URL
        int frameRate = 30; // Adjust the frame rate here (frames per second)
        int bufferSize = 50; // Adjust the buffer size here
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFilePath);
        grabber.start();
        try {
            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(rtspUrl, grabber.getImageWidth(), grabber.getImageHeight());

            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            recorder.setFrameRate(frameRate);
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            recorder.setAudioChannels(2);//1 Mono单声道，2 立体声
            recorder.setFormat("rtsp");
            recorder.start();

            // Create a buffer of size bufferSize
            ArrayBlockingQueue<Frame> buffer = new ArrayBlockingQueue<>(bufferSize);

            // Consumer thread
            Thread consumerThread = new Thread(() -> {
                while (true) {
                    try {
                        // Get a frame from the buffer, if the buffer is empty, block and wait
                        Frame frame = buffer.take();

                        // Push the frame to the RTSP stream
                        recorder.record(frame);
                    } catch (InterruptedException | FFmpegFrameRecorder.Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            consumerThread.start();

            // Producer thread
            Frame frame;
            while ((frame = grabber.grab()) != null) {
                // Process each frame here (optional)

                // Put the frame into the buffer, if the buffer is full, block and wait
                buffer.put(frame);
            }

            // Wait for the consumer thread to finish
            consumerThread.join();

            grabber.stop();
            grabber.release();

            recorder.stop();
            recorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
