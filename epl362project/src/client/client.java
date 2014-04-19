package client;

import java.io.*;
import java.net.*;

public class client {

	private static Socket sock;

	public static boolean openSocket() {
		try {
			sock = new Socket("localhost", 6789);
		} catch (UnknownHostException e) {
			System.err.println("Unable to resolve host:");
			System.err.println(e.getStackTrace());
			return false;
		} catch (IOException e) {
			System.err.println("Unable to create socket:");
			System.err.println(e.getStackTrace());
			return false;
		}
		return true;
	}

	public  static void closeSocket() {
		if (!sock.isClosed()){
			try {
				sock.close();
			} catch (IOException e) {
				System.err.println("Unable to close socket:");
				System.err.println(e.getStackTrace());
			}
		}
	}

	public static Object send(Object command) {
		if (!openSocket()) return null;
		Object o = null;
		ObjectOutputStream outToServer;
		ObjectInputStream inFromServer;
		try {
			outToServer = new ObjectOutputStream(sock.getOutputStream());
			outToServer.writeObject(command);
			inFromServer = new ObjectInputStream (sock.getInputStream());
			o = inFromServer.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		closeSocket();
		return o;
	}
}
