package katanem;

import java.io.*;
import java.net.*;
import java.net.Socket;
import java.io.*;


public class Server {

	public static void main(String[] args) {
		new Server().openServer();
	}

	public void openServer() {
		ServerSocket serverSocket = null;
		Socket connectionSocket = null;
		// String message = null;
		try {
			serverSocket = new ServerSocket(4321);

			while (true) {
				connectionSocket = serverSocket.accept();

				ServerThread thread = new ServerThread(connectionSocket);

				thread.start();
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
}

// hello gbhnjmk,cvbnmrftyujkcfghjk








