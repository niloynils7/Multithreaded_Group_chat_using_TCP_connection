package client;

import java.io.IOException;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 7689);

        sendThread send = new sendThread(clientSocket);
        receiveThread rcv = new receiveThread(clientSocket);
        send.start();
        rcv.start();
    }
}
