package dmitr.net;

import dmitr.net.handler.Handler;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread {

    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final Handler handler;

    public SocketThread(Socket socket, Handler handler) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.handler = handler;
    }

    public SocketThread(String host, int port, Handler handler) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.handler = handler;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            String data;
            try {
                data = in.readLine();
                String response = handler.handle(data);
                ;
                if (response != null)
                    send(response);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public synchronized void send(String data) throws IOException {
        out.write(data);
        out.newLine();
        out.flush();
    }

}
