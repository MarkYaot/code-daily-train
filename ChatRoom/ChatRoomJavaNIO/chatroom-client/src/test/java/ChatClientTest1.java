import czm.client.ChatClient;
import czm.exception.ClientException;

public class ChatClientTest1 {
    public static void main(String[] args) throws ClientException {
        ChatClient client = new ChatClient("czm", "114.115.144.69", 1024);
        client.start();
    }
}
