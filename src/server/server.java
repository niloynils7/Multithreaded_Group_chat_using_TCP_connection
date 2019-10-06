package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class server {

    protected static Vector<serverHelper> clientList = new Vector<>();

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(7689);
        while (true)
        {
            Socket clientSocket = serverSocket.accept();
            serverHelper serverObj= new serverHelper(clientSocket);
            serverObj.Name();
            clientList.addElement(serverObj);
            serverObj.broadcastName();
            serverObj.start();
        }

    }
}
