package czm.client;

import czm.exception.ClientException;

public class ChatRoomClientTest1 {
    public static void main(String args[]) throws ClientException, InterruptedException {
        ChatRoomClient client = new ChatRoomClient("lxl", "127.0.0.1", 1000);
        client.start();
    }
}
