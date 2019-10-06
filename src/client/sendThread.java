package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class sendThread extends Thread {

    DataOutputStream outToServer;

    public sendThread(Socket clientSocket) throws IOException {
        this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.nextLine();
            try {
                outToServer.writeBytes(msg + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
