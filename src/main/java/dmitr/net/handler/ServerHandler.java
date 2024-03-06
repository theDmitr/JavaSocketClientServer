package dmitr.net.handler;

public class ServerHandler implements Handler {

    @Override
    public String handle(String request) {
        System.out.printf("Server received: %s\n", request);
        return "HTTP_OK 200";
    }

}
