package czm.client;

import czm.exception.ClientException;
import czm.server.ChatRoomServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatRoomClient {
    private int port;
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private boolean isRunning;
    private String username;
    private String address;
    private static final String QUIT_MASK = "quit";
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomServer.class);

    ChatRoomClient(String username, String address, int port) {
        this.username = username;
        this.address = address;
        this.port = port;
    }

    public void start() throws ClientException {
        try {
            socket = new Socket(address, port);
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            sendMsg(username);
            isRunning = true;
            new Thread(new ReadConnectionThread()).start();
            new Thread(new WriteConnectionThread()).start();
        } catch (IOException e) {
            logger.error("Connection to server failed! address:{}, port:{}", address, port);
            throw new ClientException();
        }
    }

    private void sendMsg(String message) throws IOException {
        writer.write(message);
        writer.write("\n");
        writer.flush();
    }

    public void stop() {
        this.isRunning = false;
    }

    private class ReadConnectionThread implements Runnable {
        public void run() {
            try {
                while (isRunning) {
                    String message = reader.readLine();
                    if (checkQuitMask(reader, message)) break;
                    logger.info(message);
                }
            } catch (IOException e) {
                logger.error("Connection to server failed! address:{}, port:{}", address, port);
                return;
            }
        }

        private boolean checkQuitMask(BufferedReader reader, String message) throws IOException {
            if (message.equals(QUIT_MASK)) {
                logger.info(username + " quit from chatroom!");
                reader.close();
                socket.close();
                return true;
            }
            return false;
        }
    }

    private class WriteConnectionThread implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (isRunning && scanner.hasNext()) {
                String message = scanner.nextLine();
                try {
                    sendMsg(message);
                } catch (IOException e) {
                    logger.error("User:{}, send message to server failed!", username);
                }
            }
        }
    }
}
