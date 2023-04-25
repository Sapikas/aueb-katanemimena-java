package katanem;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.io.*;


public class Server {

	private int ClientsPort = 7000;
	private int WorkersPort = 7001;
	
	public static void main(String[] args) {
		new Server().openServer();
		
	}

	public void openServer() {
		ServerSocket serverSocket1 = null;
		ServerSocket serverSocket2 = null;
		Socket connectionSocket = null;
		Socket connectionSocketWork = null;
		
		
		
		String message = null;
		try {
			 serverSocket1 =  new ServerSocket(ClientsPort);
			 serverSocket2 =  new ServerSocket(WorkersPort);
			
			for(int i=0;i<4;i++) {
				Workers worker = new Workers(WorkersPort);
				worker.start();
				serverSocket2.accept();
			}
		
			while (true) {
				connectionSocket = serverSocket1.accept();
				ServerThread thread = new ServerThread(connectionSocket);
				thread.start();	
			}
		} 
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
					
			
		finally {
			try {
				serverSocket1.close();
				serverSocket2.close();
				
			}
			catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
}









