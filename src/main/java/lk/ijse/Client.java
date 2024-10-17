package lk.ijse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        //client side program steps

        try {
            //remote socket
            //request to the server
            Socket socket = new Socket("localhost", 5000);  //localhost = 127.0.0.1

            //data write
            String message = "Hi I'm from client side";

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(message);

            //send written data
            dataOutputStream.flush();

            while (true) {
                DataOutputStream dataOutputStream2 = new DataOutputStream(socket.getOutputStream());

                Scanner sc = new Scanner(System.in);
                System.out.print("Client Message: ");
                String message1 = sc.nextLine();
                dataOutputStream2.writeUTF("\nFrom Server: " + message1);
                dataOutputStream2.flush();

                //data read from the server for two-way communication
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String message2 = dataInputStream.readUTF();
                System.out.println(message2);

                if (message2.equals("exit")) {
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
