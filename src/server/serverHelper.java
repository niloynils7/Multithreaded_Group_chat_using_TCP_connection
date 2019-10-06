package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class serverHelper extends Thread {

    Socket clientSocket;
    BufferedReader inFromClient;
    DataOutputStream outToClient;
    String name;

    public serverHelper(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.outToClient = new DataOutputStream(clientSocket.getOutputStream());
        this.inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void Name() {
        try {
            outToClient.writeBytes("your username: \n");
            this.name = inFromClient.readLine();
        } catch (IOException e) {
            System.out.println("username not found");
        }
    }

    public void broadcastName() {
        for (int i = 0; i < server.clientList.size(); i++) {
            try {
                server.clientList.get(i).outToClient.writeBytes(name + " joined the group chat! \n");
            } catch (IOException e) {
                System.out.println("couldn't join");
            }
        }
    }

    @Override
    public void run() {
        String message = null;
        while (true) {
            try {
                message = name + ": " + inFromClient.readLine()+ '\n';
            } catch (IOException e) {
                message = name + " left the group chat\n";
            }

            int leave = -1;
            for (int i = 0; i < server.clientList.size(); i++) {
                try {
                    server.clientList.get(i).outToClient.writeBytes(message);
                } catch (IOException e) {
                    leave = i;
                }

                if (leave >= 0 && i == (server.clientList.size() - 1)) {
                    serverHelper abc = server.clientList.remove(leave);
                    abc.suspend();
                }
            }

        }
    }
}
