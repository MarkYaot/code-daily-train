package czm.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ChatClient {
    private static final Logger logger = LoggerFactory.getLogger(ChatClient.class);

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    private static final String CLIENT_PROPERTIES_FILE = "client.properties";

    private static final String PROPERTIES_QUIT_MARK = "quit.mark";

    private int port;

    private String host;

    private Scanner scanner;

    private String quitMark;

    private String username;

    private SocketChannel socketChannel;

    private Selector selector;

    private boolean isRunning = false;

    public ChatClient(String username, String host, int port) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            Properties prop = new Properties();
            prop.load(ClassLoader.getSystemResourceAsStream(CLIENT_PROPERTIES_FILE));
            quitMark = prop.getProperty(PROPERTIES_QUIT_MARK);

            isRunning = true;
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(host, port));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);


            while (isRunning) {
                selector.select();
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isConnectable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                        }
                        channel.write(StandardCharsets.UTF_8.encode(username));

                        channel.register(selector, SelectionKey.OP_READ);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    while (isRunning && scanner.hasNext()) {
                                        String message = scanner.nextLine();
                                        channel.write(StandardCharsets.UTF_8.encode(message));
                                        if (quitMark.equals(message)) {
                                            isRunning = false;
                                            scanner.close();
                                            channel.close();
                                        }
                                    }
                                } catch (IOException e) {
                                    logger.error("IO failed! error:{}", e.getMessage());
                                }
                            }
                        }).start();
                    } else if (key.isReadable()) {
                        try {
                            SocketChannel channel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(DEFAULT_BUFFER_SIZE);
                            channel.read(byteBuffer);
                            byteBuffer.flip();
                            String message = StandardCharsets.UTF_8.decode(byteBuffer).toString();
                            logger.info(message);
                        } catch (IOException e) {
                            logger.error("Connection to server failed! address:{}, port:{}", host, port);
                            return;
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Client start failed! error:{}", e.getMessage());
        }
    }
}
