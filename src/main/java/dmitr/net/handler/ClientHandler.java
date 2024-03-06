package dmitr.net.handler;

public class ClientHandler implements Handler {

    @Override
    public String handle(String request) {
        System.out.printf("Client received: %s\n", request);
        return "Give me 200 response :)";
    }

}
