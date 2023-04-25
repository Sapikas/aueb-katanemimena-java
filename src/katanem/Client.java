package katanem;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	private static String ServerIP = "localhost";
	private static int ServerPort = 7000;
	
	
	public static void main(String[] args) {
		new Client().startClient();
	}

	public void startClient() {
		ArrayList<String> list; 
		Socket requestSocket = null;
		ObjectOutputStream objectOutputStream = null;
		ObjectInputStream objectInputStream = null;
		FileOutputStream  fileOutputStream = null;
		FileInputStream  fileInputStream = null;
		// String message;

		try {
			requestSocket = new Socket(ServerIP, ServerPort);

			objectOutputStream = new ObjectOutputStream(requestSocket.getOutputStream());
			objectInputStream = new ObjectInputStream(requestSocket.getInputStream());
			//fileOutputStream = new FileOutputStream(requestSocket.getOutputStream());
			//fileInputStream = new FileInputStream(requestSocket.getOutputStream());

			
			try {
				String message = objectInputStream.readUTF();
				System.out.println( message);

				Scanner sc = new Scanner(System.in);
				
				while(true){
					System.out.println("Give the path of the gpx file you want to send to the server: ");
					String msg = sc.nextLine();
					if(msg.compareTo("")==0) {
						msg = "C:/6οεξαμηνο/Κατανεμημένα Συστήματα/gpxs/route1.gpx";
						
					}
					
					GPXParser file = new GPXParser(msg);
			    	list=file.gpxParsing();
			    	for(int i=0;i<list.size();i++) {
			    		objectOutputStream.writeUTF(list.get(i));
						objectOutputStream.flush();
			    	}
					

					if(msg.equalsIgnoreCase("bye"))
						break;
				}

				sc.close();

			} catch (Exception expc) {
				System.err.println("data received in unknown format");
			}
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				
				objectInputStream.close();
				objectOutputStream.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
}


