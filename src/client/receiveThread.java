package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class receiveThread extends Thread {

    BufferedReader inFromServer;

    public receiveThread(Socket clientSocket) throws IOException {
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = inFromServer.readLine();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
