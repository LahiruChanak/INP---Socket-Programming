package lk.ijse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {

        //server side program steps

       try {
           //create server socket
           ServerSocket serverSocket = new ServerSocket(5000);

           //create local socket
           Socket socket = serverSocket.accept();

           //sout
           System.out.println("Client Accepted!");

           //data reading
           DataInputStream dataInputStream  =new DataInputStream(socket.getInputStream());

           //sout - read data
           String message = dataInputStream.readUTF();
           System.out.println(message);

           //data write to the client for two-way communication
           Scanner sc = new Scanner(System.in);

           while (true) {
               DataInputStream dataInputStream1 = new DataInputStream(socket.getInputStream());
               String message2 = dataInputStream1.readUTF();
               System.out.println(message2);

               System.out.print("Server Message: ");
               String message1 = sc.nextLine();
               DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
               dataOutputStream.writeUTF("\nFrom Client: " + message1);
               dataOutputStream.flush();
               if (message1.equals("exit")) {
                   break;
               }
           }

           //connection close
           socket.close();

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

}
