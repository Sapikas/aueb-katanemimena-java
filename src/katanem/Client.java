package katanem;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {	
	///////

	//client

	//////
		public static void main(String[] args) {
			new Client().startClient();
		}

		public void startClient() {
			Socket requestSocket = null;
			ObjectOutputStream objectOutputStream = null;
			ObjectInputStream objectInputStream = null;
			// String message;

			try {
				requestSocket = new Socket("localhost", 4321);

				objectOutputStream = new ObjectOutputStream(requestSocket.getOutputStream());
				objectInputStream = new ObjectInputStream(requestSocket.getInputStream());

				try {
					String message = objectInputStream.readUTF();
					System.out.println("Server sent" + message);

					Scanner sc = new Scanner(System.in);

					while(true){
						String msg = sc.nextLine();
						objectOutputStream.writeUTF(msg);
						objectOutputStream.flush();

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


