package czm.server;

import czm.exception.ServerException;

public class ChatRoomServerTest {
    public static void main(String[] args) throws ServerException {
        ChatRoomServer server = ChatRoomServer.getSingleton();
        server.start(1000);
    }
}
