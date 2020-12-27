package czm.server;

import czm.exception.ServerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ChatRoomServer {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomServer.class);

    private static ChatRoomServer server;

    private ServerSocket serverSocket;

    private boolean isRunning = false;

    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);

    private List<ProcessConnectionThread> connections = new ArrayList<>();

    private static String QUIT_MSG = "quit";

    /**
     * 单例模式
     *
     * @return
     */
    public static ChatRoomServer getSingleton() {
        if (server == null) {
            server = new ChatRoomServer();
        }
        return server;
    }

    public void start(int port) throws ServerException {
        //参数校验
        if (port <= 0) {
            logger.error("Server port is invalid! port:{}", port);
            throw new ServerException();
        }

        try {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            new Thread(new ReciveConnectionThread()).start();
            new Thread(new PushMsgThread()).start();
            logger.info("Server start succeed!");
        } catch (IOException e) {
            logger.error("Server start failed! port:{}", port);
            throw new ServerException();
        }
    }

    public void stop() {
        isRunning = false;
        return;
    }

    private class PushMsgThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    String message = queue.take();
                    if (!StringUtils.isEmpty(message)) {
                        for (ProcessConnectionThread connection : connections) {
                            connection.sendMsg(message);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ReciveConnectionThread implements Runnable {
        public void run() {
            while (isRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    new Thread(new ProcessConnectionThread(socket)).start();
                } catch (IOException e) {
                    logger.error("Accept new connection failed!");
                    return;
                }
            }
        }
    }

    private class ProcessConnectionThread implements Runnable {
        ProcessConnectionThread(Socket socket) {
            try {
                this.socket = socket;
                this.writer = new BufferedWriter(
                        new OutputStreamWriter(this.socket.getOutputStream()));
                this.reader = new BufferedReader(
                        new InputStreamReader(this.socket.getInputStream()));
                this.username = reader.readLine();
                connections.add(this);
                pushMsg(username + " is online");
            } catch (IOException e) {
                logger.error("User:{} open writer failed!", username);
            }
        }

        private BufferedReader reader;

        private BufferedWriter writer;

        private Socket socket;

        private String username;

        public void run() {
            try {
                while (isRunning) {
                    String content = reader.readLine();
                    if (checkQuitMask(content)) break;
                    String message = username + " says:" + content;
                    pushMsg(message);
                }
            } catch (IOException e) {
                logger.error("User:{} connection lost!", username);
            } finally {
                logger.info("User:{} is offline!", username);
                connections.remove(this);
                pushMsg(username + " is offline!");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean checkQuitMask(String content) throws IOException {
            if (content.equals(QUIT_MSG)) {
                sendMsg(QUIT_MSG);
                return true;
            }
            return false;
        }

        public void pushMsg(String message) {
            try {
                queue.put(message);
                logger.info(message);
            } catch (InterruptedException e) {
                logger.error("Push username:{}, message:{} into queue failed!", username, message);
            }
        }

        public void sendMsg(String message) {
            if (!StringUtils.isEmpty(message)) {
                try {
                    writer.write(message);
                    writer.write("\n");
                    writer.flush();
                    logger.info("Send message:{} to username:{}", message, username);
                } catch (IOException e) {
                    logger.error("Send message to user:{} failed!", username);
                }
            }
        }
    }
}
