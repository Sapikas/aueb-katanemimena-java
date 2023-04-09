package katanem;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread{

    Socket connectionSocket;

    public ServerThread(Socket socket){
        this.connectionSocket = socket;
    }
    
    @Override
    public void run() {
        //bytes only!!
        try{
            OutputStream outputStream = connectionSocket.getOutputStream();
            InputStream inputStream = connectionSocket.getInputStream();


            ObjectInputStream ois = new ObjectInputStream(inputStream);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeUTF("Connection is ok!");

            oos.flush();

            while(true){

                String message = ois.readUTF();

                if(message.equalsIgnoreCase("bye"))
                    break;

                System.out.println("Thread id: "+getId()+" Client sent: "+message);

            }

            ois.close();
            oos.close();

            connectionSocket.close();


        }catch(Exception e){

        }
				
    }
}

