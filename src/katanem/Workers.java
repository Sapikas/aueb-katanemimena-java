package katanem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Workers extends Thread{
		Socket connectionSocketWork;
		public int id;
		private static String ServerIP = "localhost";
		private int ServerPort;
		
		// Get default listening server port number 
		public Workers() {
	    	//Initialize the server port that we will listen to
	    	this.ServerPort = 7001;
	    }
		
		// Get the server port number in object initialization
	    public Workers(int ServerPort) {
	    	//Initialize the server port that we will listen to
	    	this.ServerPort = ServerPort;
	    }
	    
	    public Workers(Socket connectionSocketWork) {
	    	this.connectionSocketWork = connectionSocketWork;
	    }
	    	 @Override
	 	    public void run() {
	 	        //bytes only!!
	 	        try{
	 	        		 	        	
		 	        this.connectionSocketWork = new Socket(ServerIP, ServerPort);
	 	        	//System.out.println("i am worker");	 	        	
	 	            OutputStream outputStream = connectionSocketWork.getOutputStream();
	 	            InputStream inputStream = connectionSocketWork.getInputStream();

	 	            this.id=(int) getId();
	 	            ObjectInputStream oisW = new ObjectInputStream(inputStream);
	 	            ObjectOutputStream oosW = new ObjectOutputStream(outputStream);

	 	            oosW.writeUTF("I AM WORKER! Connection Ok");

	 	            oosW.flush();
	 	            
	 	            while(true){

	 	                String message = oisW.readUTF();

	 	                if(message.equalsIgnoreCase("bye"))
	 	                    break;

	 	            }

	 	            oisW.close();
	 	            oosW.close();

	 	            connectionSocketWork.close();


	 	        }catch(Exception e){
	 	        	System.err.println(e.getMessage());
	 	        	e.printStackTrace();
	 	        }
	    	 }
	 				
}


