package dmitr.net;

import dmitr.net.handler.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server extends Thread {

    private final int port;
    private final LinkedList<SocketThread> threads;
    private final Handler handler;

    public Server(int port, Handler handler) {
        this.port = port;
        this.handler = handler;
        this.threads = new LinkedList<>();
    }

    @Override
    public void run() {
        try {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server started!");
                while (true) {
                    Socket socket = serverSocket.accept();
                    SocketThread thread = new SocketThread(socket, handler);
                    thread.start();
                    threads.add(thread);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public synchronized LinkedList<SocketThread> getThreads() {
        return threads;
    }

}
