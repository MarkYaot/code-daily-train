package czm.client;

import czm.exception.ClientException;

public class ChatRoomClientTest2 {
    public static void main(String args[]) throws ClientException {
        ChatRoomClient client = new ChatRoomClient("czm", "127.0.0.1", 1000);
        client.start();
    }
}
