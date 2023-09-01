package org.example;

import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVIOContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacpp.BytePointer;


public class PushStream {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("error: 未输入视频文件路径");
            System.exit(1);
        }

        String vf_path = args[0];
        try {

            //1.创建一个 AVFormatContext并设置输出配置
            AVFormatContext outputFmtCtx = avformat.avformat_alloc_context();
            // 设置输出格式 (e.g., "flv", "rtmp", etc.)
            outputFmtCtx.oformat(avformat.av_guess_format("flv", "rtmp://127.0.0.1:5505/live/test", null));

            //2. 打开输出URL进行流式传输
            AVIOContext pb = avformat.avio_alloc_context(new BytePointer(avutil.av_malloc(4096)), 4096, 1, null, null, null, null);
            outputFmtCtx.pb(pb);
            //打开视频文件
            int ret = avformat.avio_open(outputFmtCtx.pb(), vf_path, avformat.AVIO_FLAG_WRITE);
            if (ret < 0) {
                System.out.printf("Open video file %s failed \n", vf_path);
                throw new IllegalStateException();
            }

//            //3. 获取H264视频编码器并设置编解码器上下文
            AVCodec codec = avcodec.avcodec_find_encoder(avcodec.AV_CODEC_ID_H264);
            AVStream outStream = avformat.avformat_new_stream(outputFmtCtx, codec);
            AVCodecContext codecContext = avcodec.avcodec_alloc_context3(codec);

            //4. Set the codec parameters (bitrate, resolution, framerate, etc.)
            codecContext.width(1920);
            codecContext.height(1080);
            codecContext.time_base().num(1).den(25);

            //5. Open the video codec
            avcodec.avcodec_open2(codecContext, codec, (AVDictionary) null);

            //6. Start streaming the video frames
            AVPacket packet = avcodec.av_packet_alloc();
//             Loop through your video frames and encode them using codecContext.

//             Pack the encoded data into the packet and write it to the output URL.

//             Your code for initializing and streaming video here

            // Close the video codec and free its context
            avcodec.avcodec_close(codecContext);
            avcodec.avcodec_free_context(codecContext);

            // Free the AVPacket and its allocated data
            avcodec.av_packet_free(packet);

            // Close the output URL for streaming
            avformat.av_write_trailer(outputFmtCtx);
            if ((outputFmtCtx.oformat().flags() & avformat.AVFMT_NOFILE) == 0) {
                avformat.avio_closep(outputFmtCtx.pb());
            }

            // Free the AVFormatContext and all its streams
            avformat.avformat_free_context(outputFmtCtx);
        } catch (Exception e) {
            // Handle exceptions here
            e.printStackTrace();
        }

    }
}
