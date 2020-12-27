package czm.client;

import czm.exception.ClientException;

public class ChatRoomClientTest3 {
    public static void main(String args[]) throws ClientException {
        ChatRoomClient client = new ChatRoomClient("ssary", "127.0.0.1", 1000);
        client.start();
    }
}
