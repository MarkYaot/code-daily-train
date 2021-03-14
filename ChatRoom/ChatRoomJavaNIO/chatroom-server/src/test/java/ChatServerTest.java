import czm.exception.ServerException;
import czm.server.ChatServer;

public class ChatServerTest {
    public static void main(String[] args) throws ServerException {
        ChatServer server = new ChatServer();
        server.start();
    }
}
