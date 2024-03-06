package dmitr.net;

import dmitr.net.handler.ClientHandler;
import dmitr.net.handler.Handler;
import dmitr.net.handler.ServerHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ServerTest {

    @Test
    public void test() throws IOException {
        Handler serverHandler = new ServerHandler();
        Handler clientHandler = new ClientHandler();

        Server server = new Server(4004, serverHandler);
        server.start();

        SocketThread client = new SocketThread("localhost", 4004, clientHandler);
        client.start();
        client.send("First message from client!");
    }

}