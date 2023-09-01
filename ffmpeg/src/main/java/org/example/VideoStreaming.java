package org.example;


import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class VideoStreaming {

    public static void main(String[] args) {
        // 使用VLCj的本地库发现机制
        if (!new NativeDiscovery().discover()) {
            System.out.println("未找到VLC库");
            return;
        }

        // 创建MediaPlayer工厂和MediaPlayer对象
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

        // 设置输入视频文件路径
        String videoFilePath = "D:\\gopro.mp4";

        // 设置串流目标地址及端口号
        String rtspUrl = "rtsp://localhost:554/live/stream";

        // 设置串流选项
        String[] options = {
                ":sout=#rtp{sdp=rtsp://127.0.0.1:554/live/stream}", // RTSP输出选项
                ":sout-keep" // 保持输出连接的持久性
        };

        // 开始串流
        mediaPlayer.media().play(videoFilePath, options);

        // 等待串流结束（或根据需要设置合适的等待时间）
        try {
            Thread.sleep(60000); // 60秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 停止播放并释放资源
        mediaPlayer.controls().stop();
        mediaPlayer.release();
        mediaPlayerFactory.release();
    }
}