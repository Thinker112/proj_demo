package org.example;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVIOContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.PointerPointer;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.concurrent.*;

import static org.bytedeco.ffmpeg.global.avcodec.*;
import static org.bytedeco.ffmpeg.global.avformat.*;
import static org.bytedeco.ffmpeg.global.avutil.*;

//@Slf4j
public class FfmpegStream {

    public static void main(String[] args) throws Exception {
        try {
            String inputPath = "D:\\gopro.mp4";
            String outputPath = "rtsp://127.0.0.1:554/live/stream";
            //初始化 input AVFormatContext
            AVFormatContext ifmt_ctx = avformat.avformat_alloc_context();
            checkNull(ifmt_ctx, "Could not allocate input format context");
            //打开输入流并读取头信息
            if (avformat_open_input(ifmt_ctx, inputPath, null, null) < 0) {
                throw new Exception("Could not open input file");
            }
            //读取媒体文件的数据包以获取流信息
            long start = System.currentTimeMillis();
            if (avformat_find_stream_info(ifmt_ctx, (PointerPointer) null) < 0) {
                throw new Exception("Could not find stream info");
            }

            //初始化 output AVFormatContext
            AVFormatContext ofmt_ctx = avformat.avformat_alloc_context();
            checkNull(ofmt_ctx, "Could not allocate output format context");


            //为输出格式分配一个 AVFormatContext
            if (avformat_alloc_output_context2(ofmt_ctx, null, "rtsp", outputPath) < 0) {
                throw new Exception("Could not allocate output context");
            }

            for (int i = 0; i < ifmt_ctx.nb_streams(); i++) {
                AVStream in_stream = ifmt_ctx.streams(i);
                //获取当前媒体流的编解码器参数
                AVCodecParameters in_codecpar = in_stream.codecpar();
                //向媒体文件添加新流
                AVStream out_stream = avformat_new_stream(ofmt_ctx, null);
                AVCodecParameters out_codecpar = out_stream.codecpar();
                //in_codecpar 的内容复制到 out_codecpar
                if (avcodec_parameters_copy(out_codecpar, in_codecpar) < 0) {
                    throw new Exception("Could not copy codec parameters");
                }
                out_codecpar.codec_tag(0);
            }

            //打印输出格式详细信息
            av_dump_format(ofmt_ctx, 0, outputPath, 1);

            if (ofmt_ctx != null && (ofmt_ctx.oformat().flags() & AVFMT_NOFILE) == 0) {
                PointerPointer<AVIOContext> pp = new PointerPointer<>(1L);
                BytePointer bp = new BytePointer(outputPath); // 将字符串转换为 BytePointer
                int ret = avformat.avio_open(pp, bp, avformat.AVIO_FLAG_WRITE);
                if (ret < 0) {
                    throw new Exception("Could not open output file"); // 如果打开文件失败，抛出异常
                }
                AVIOContext avioContext = new AVIOContext(pp.get()); // 获取新创建的AVIOContext
                ofmt_ctx.pb(avioContext); // 将新创建的AVIOContext设置为ofmt_ctx的pb
            }
            //分配流私有数据并将流标头写入输出媒体文件。
            if (avformat_write_header(ofmt_ctx, (PointerPointer) null) < 0) {
                throw new Exception("Could not write header");
            }

            //创建一个新的AVPacket对象，用于存储从输入流中读取的数据帧
            AVPacket packet = new AVPacket();
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            // 创建一个线程安全的队列用于存储视频数据包
//            BlockingQueue<AVPacket> packetQueue = new LinkedBlockingQueue<>();
            // 创建一个线程安全的优先级队列，用于按时间戳排序数据包
            PriorityBlockingQueue<AVPacket> packetQueue = new PriorityBlockingQueue<>(1000, Comparator.comparingLong(AVPacket::pts));

            long basePts = -1;
            long baseTime = -1;
            long baseDts = -1;
            long lastDts = AV_NOPTS_VALUE; // 保存上一帧的解码时间戳
            while (true) {
                int ret = av_read_frame(ifmt_ctx, packet);

                if (ret < 0) {
                    if (ret == avutil.AVERROR_EOF) {
                        long end = System.currentTimeMillis();
                        long total = end - start;
//                        log.info("视频文件读取完毕！耗时: {}ms", total);
                        break;
                    } else {
                        throwExceptionForNegativeResult(ret, "Error while reading frame");
                    }
                }
                AVPacket packetToAdd = new AVPacket();
                av_packet_ref(packetToAdd, packet);
                packetQueue.offer(packetToAdd);

                // 释放原始packet对象
                av_packet_unref(packet);

                // 对数据包的 PTS（Presentation Timestamp）进行转换和重新计算，以适配输出流的时间基准
                packetToAdd.pts(av_rescale_q(packetToAdd.pts(),
                        ifmt_ctx.streams(packetToAdd.stream_index()).time_base(), //输入流的时间基
                        ofmt_ctx.streams(packetToAdd.stream_index()).time_base()));//输出流的时间基

                // 将输入视频数据包（packet）的解码时间戳（DTS）从输入流的时间基转换为输出流的时间基
                packetToAdd.dts(av_rescale_q(packetToAdd.dts(),
                        ifmt_ctx.streams(packetToAdd.stream_index()).time_base(),
                        ofmt_ctx.streams(packetToAdd.stream_index()).time_base()));

                if (baseDts == -1){
                    baseDts = packetToAdd.dts();
//                    log.info("baseDts: {}", baseDts);
                }

                // 如果这是第一帧，记录基本PTS和基本时间
                if (basePts == -1) {
                    basePts = packetToAdd.pts();
//                    log.info("basePts: {}", basePts);
                    baseTime = System.nanoTime(); // 将基本时间单位改为纳秒
                }

//                log.info("pts: {},   dts: {}", packetToAdd.pts(), packetToAdd.dts());

                // 检查DTS是否有效，是否单调递增
                if (lastDts != AV_NOPTS_VALUE && packetToAdd.dts() <= lastDts) {
                    throw new Exception("Invalid non-monotonically increasing DTS");
                }

                // 计算当前帧的PTS与基本PTS的时间差
                long ptsDifference = packetToAdd.pts() - basePts;
                long dtsDiff = packetToAdd.dts() - baseDts;
//                log.info("dtsDiff : {}", dtsDiff);

                // 获取当前数据包流索引对应输出流的时间基准
                AVRational timeBaseOut = ofmt_ctx.streams(packetToAdd.stream_index()).time_base();

                // 使用输出流的时间基准将PTS差值转换为微秒
                long ptsTimeMicroseconds = (ptsDifference * 1000000 * timeBaseOut.num()) / timeBaseOut.den();

                // 通过将计算得到的时间加上基本时间（以纳秒为单位）得到最终的显示时间（单位为微秒）
                long displayTimeMicroseconds = baseTime + ptsTimeMicroseconds;

                long delay = displayTimeMicroseconds - System.nanoTime() / 1000; // 将延迟时间单位改为微秒
                System.out.println("delay = " + delay);
                long delayDts = dtsDiff / 90;
                if (delayDts < 0){
                    delayDts = 0;
                }
                System.out.println("delayDts = " + delayDts);
//                throwExceptionForNegativeResult(av_interleaved_write_frame(ofmt_ctx, packetToAdd), "Could not write frame");

                // 模拟实时播放，以便让帧在正确的时间点被写入输出流
                executor.schedule(() -> {
                    AVPacket queuePacket = null;
                    try {
                        queuePacket = packetQueue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (queuePacket != null) {
                        // 将数据包写入输出流
                        try {
                            throwExceptionForNegativeResult(av_interleaved_write_frame(ofmt_ctx, queuePacket), "Could not write frame");
                            // 释放队列中的数据包对象
                            av_packet_unref(queuePacket);
                        } catch (Exception e) {
                            throw new RuntimeException("写入输出流错误" ,e);
                        }
                    }
                }, delayDts, TimeUnit.MILLISECONDS);

                lastDts = packet.dts();
            }

            av_write_trailer(ofmt_ctx);
            //释放 input AVFormatContext
            avformat_close_input(ifmt_ctx);

            if (ofmt_ctx != null && (ofmt_ctx.oformat().flags() & AVFMT_NOFILE) == 0) {
                avio_closep(ofmt_ctx.pb());
            }

            //释放 output AVFormatContext
            avformat_free_context(ofmt_ctx);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkNull(Object obj, String errorMessage) throws Exception {
        if (obj == null) {
            throw new Exception(errorMessage);
        }
    }

    private static void throwExceptionForNegativeResult(int result, String operation) throws Exception {
        if (result < 0) {
            byte[] data = new byte[1024]; // 用于存储错误信息的缓冲区
            av_strerror(result, data, data.length);
            throw new Exception(operation + " failed: " + new String(data, StandardCharsets.UTF_8).trim());
        }
    }
}
