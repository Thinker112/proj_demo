package io.netty.example.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.SocketHandler;

/**
 * @author yueyubo <br>
 * @date 2024-05-28 22:52
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        for (;;) {
            // 阻塞方法
            Socket socket = serverSocket.accept();
            System.out.println("收到客户端连接");

            new Thread(()->{
                handler(socket);
            }).start();

//            handler(socket);
        }
    }

    static void handler(Socket clientSocket){
        try {
            byte[] bytes = new byte[1024];
            // 阻塞方法
            int len = clientSocket.getInputStream().read(bytes);
            System.out.println("read完毕");
            System.out.println(new String(bytes,0, len));

            clientSocket.getOutputStream().write("hello".getBytes());
            System.out.println("end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
