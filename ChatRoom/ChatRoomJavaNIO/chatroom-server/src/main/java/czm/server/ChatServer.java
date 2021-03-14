package czm.server;

import czm.exception.ServerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ChatServer {
    private static final Logger logger = LoggerFactory.getLogger(ChatServer.class);

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    private static final String SERVER_PROPERTIES_FILE = "server.properties";

    private static final String PROPERTIES_QUIT_MARK = "server.quit.mark";

    private static final String PROPERTIES_SERVER_PORT = "server.port";

    private static final String PROPERTIES_SERVER_HOST = "server.host";

    private boolean isRunning = false;

    private ServerSocketChannel serverSocketChannel;

    private String quitMark;

    private Selector selector;

    public void start() throws ServerException {
        try {
            isRunning = true;
            selector = Selector.open();

            Properties prop = new Properties();
            prop.load(ClassLoader.getSystemResourceAsStream(SERVER_PROPERTIES_FILE));
            quitMark = prop.getProperty(PROPERTIES_QUIT_MARK);
            int port = Integer.parseInt(prop.getProperty(PROPERTIES_SERVER_PORT));
            logger.info("server port:{}", port);

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("Chat Server is started!");
        } catch (IOException e) {
            logger.error("Server start failed! error:{}", e.getMessage());
            throw new ServerException(e.getMessage());
        }

        new Thread(new SelectorThread()).start();
    }

    public void stop() {
        isRunning = false;
    }

    private class SelectorThread implements Runnable {
        @Override
        public void run() {
            try {
                while (isRunning) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            handleAccept(key);
                        } else if (key.isReadable()) {
                            handleRead(key);
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("IO failed, server exit! error:{}", e.getMessage());
            } finally {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    logger.error("close server failed! error:{}", e.getMessage());
                }
                logger.info("Chat Server is stoped!");
            }
        }

        private void dealOnline(SelectionKey key, String username) throws IOException {
            key.attach(username);
            String message = username + " is online";
            logger.info(message);
            broadcastMsg(username, message);
        }

        private void dealSendMsg(SelectionKey key, String message) throws IOException {
            String username = (String) key.attachment();
            message = username + " says: " + message;
            logger.info(message);
            broadcastMsg(username, message);
        }

        private void dealOffline(SelectionKey key) throws IOException {
            String username = (String) key.attachment();
            key.channel().close();
            String message = username + " is offline";
            logger.info(message);
            broadcastMsg(username, message);
        }

        private void broadcastMsg(String sourceUser, String message) throws IOException {
            Map<String, SocketChannel> map = getConnectedChannel();
            for (String targetUser : map.keySet()) {
                if (!StringUtils.isEmpty(targetUser) && !targetUser.equals(sourceUser)) {
                    map.get(targetUser).write(StandardCharsets.UTF_8.encode(message));
                }
            }
        }

        private void handleRead(SelectionKey key) throws IOException {
            try {
                //读取
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
                socketChannel.read(buffer);
                buffer.flip();
                String message = StandardCharsets.UTF_8.decode(buffer).toString();
                if (key.attachment() == null) {
                    //用户上线
                    dealOnline(key, message);
                } else if (quitMark.equals(message)) {
                    //用户下线
                    dealOffline(key);
                } else {
                    //转发消息
                    dealSendMsg(key, message);
                }
            } catch (IOException e) {
                logger.error("handle read failed! error:{}", e.getMessage());
                key.channel().close();
            }
        }

        private void handleAccept(SelectionKey key) {
            try {
                SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                logger.error("handle accept failed! error:{}", e.getMessage());
            }
        }

        private Map<String, SocketChannel> getConnectedChannel() {
            Map<String, SocketChannel> map = new HashMap<>();
            selector.keys().stream().filter(item -> item.channel() instanceof SocketChannel
                    && item.channel().isOpen()).forEach(item ->
                    map.put((String) item.attachment(), (SocketChannel) item.channel()));
            return map;
        }
    }

    public static void main(String[] args) throws ServerException {
        ChatServer server = new ChatServer();
        server.start();
    }
}