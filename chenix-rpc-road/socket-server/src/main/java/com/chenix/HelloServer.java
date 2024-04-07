package com.chenix;

import com.chenix.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author Chenix
 */
public class HelloServer {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    public void start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Server started on port: " + port);
            while (true) {
                try {
                    Socket socket = server.accept();
                    logger.info("Client connected");

                    // 为每个客户端连接创建一个新的线程
                    /*new Thread(() -> {
                        handleClient(socket);
                    }).start();*/
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    ExecutorService threadPool
                            = new ThreadPoolExecutor(
                                    10,
                            100,
                            1,
                            TimeUnit.MINUTES,
                            new ArrayBlockingQueue<>(100), threadFactory
                    );
                    threadPool.execute(() -> {
                        // 创建 socket 连接
                        handleClient(socket);
                    });
                } catch (IOException e) {
                    logger.error("IOException occurred while accepting connection:", e);
                }
            }
        } catch (IOException e) {
            logger.error("IOException occurred while creating ServerSocket:", e);
        }
    }

    private void handleClient(Socket socket) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

            // 读取客户端发送的请求信息
            Message message = (Message) objectInputStream.readObject();
            logger.info("Server received message: " + message.getContent());

            // 修改消息内容并发送回客户端
            message.setContent("New content from server");
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

        } catch (IOException | ClassNotFoundException e) {
            logger.error("Exception occurred while handling client:", e);
        } finally {
            try {
                socket.close(); // 在线程结束时关闭Socket
                logger.info("Client disconnected");
            } catch (IOException e) {
                logger.error("Error closing socket:", e);
            }
        }
    }

    public static void main(String[] args) {
        HelloServer helloServer = new HelloServer();
        helloServer.start(6666);
    }
}