import czm.client.ChatClient;
import czm.exception.ClientException;

public class ChatClientTest1 {
    public static void main(String[] args) throws ClientException {
        ChatClient client = new ChatClient("czm", "127.0.0.1", 8000);
        client.start();
    }
}
