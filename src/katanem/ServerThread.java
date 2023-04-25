package katanem;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ServerThread extends Thread{

    Socket connectionSocket;
    Socket connectionSocketWork;
    //Queue<ArrayList <String>> fifo = new LinkedList<ArrayList <String>>();
    Queue<String> fifo = new LinkedList<String>();
    //public ArrayList<String> returnedData = new ArrayList<String>();
    public ArrayList<String> chunkList = new ArrayList<String>();
    
    public ServerThread(Socket socket){
        this.connectionSocket = socket;
       // this.connectionSocketWork = socketWork;
    }
    public ServerThread(Socket socket, Socket connectionSocketWork){
        this.connectionSocketWork = connectionSocketWork;
    }
    

    
    public void map(){
    	
    	
    }
    public void run() {
        //bytes only!!
        try{
        	//connection with client
            OutputStream outputStream = connectionSocket.getOutputStream();
            InputStream inputStream = connectionSocket.getInputStream();
            
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            
            
            ////connection with Worker            
           /* OutputStream outputStreamWork = connectionSocketWork.getOutputStream();
            InputStream inputStreamWork = connectionSocketWork.getInputStream();
            
            ObjectInputStream oisW = new ObjectInputStream(inputStreamWork);
            ObjectOutputStream oosW = new ObjectOutputStream(outputStreamWork);
           
*/
            oos.writeUTF("SERVER OK");

            oos.flush();
            int i=0;
            System.out.println("Client with thread id " + getId() + ", send : ");
            while(true){
                String message = ois.readUTF();
               // System.out.println("message" + message);
                fifo.add(message);
                if(message.equalsIgnoreCase("bye"))
                    break;
                
	                chunkList.add(message);	 	                
	                System.out.println(chunkList.get(i++));
	                
	                oos.writeUTF("Connection is ok!");
	                
            }      
            
            
           
            int counter=0;
            for(int k=0;k<this.chunkList.size();k+=4) {
            	ArrayList <String> chunk =  new ArrayList<String>();
            	System.out.println("ok");
        		for(int j=k;j<=k+3 && j<this.chunkList.size();j++) {
        			
        			chunk.add(chunkList.get(j));
        			System.out.println("Chunk: " + chunkList.get(j));
        			fifo.add(chunk.get(k-j));
        		}
        		  
        		 System.out.println(fifo);
        		
    			
           	}
            System.out.println("Data in the top: " + fifo.peek());
            System.out.println(fifo);
            
            ois.close();
            oos.close();
            connectionSocket.close();
                
           


        }catch(Exception e){

        }
				
    }
}

