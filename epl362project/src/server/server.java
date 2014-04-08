package server;

import java.io.*;
import java.net.*;

/*
 * Supported commands:
 * VERIFY [username] [password]
 * --returns -1,0,1,2,3
 */

public class server {

	private static ServerSocket sock;
	private static Socket cSock;

	private static boolean openSocket() {
		try {
			sock = new ServerSocket(6789);
		} catch (IOException e) {
			System.err.println("Unable to create server socket:");
			System.err.println(e.getStackTrace());
			return false;
		}
		return true;
	}

	private static void closeSocket() {
		if (!sock.isClosed())
			try {
				sock.close();
			} catch (IOException e) {
				System.err.println("Unable to close socket:");
				System.err.println(e.getStackTrace());
			}
		if (!cSock.isClosed())
			try {
				cSock.close();
			} catch (IOException e) {
				System.err.println("Unable to close socket:");
				System.err.println(e.getStackTrace());
			}
	}

	private static boolean send(String... data) {
		if (!cSock.isConnected())
			return false;
		try {
			DataOutputStream outToClient = new DataOutputStream(
					cSock.getOutputStream());
			for (String i : data)
				outToClient.writeBytes(i + ' ');
		} catch (IOException e) {
			System.err.println("Unable to send data to client:");
			System.err.println(e.getStackTrace());
			return false;
		}
		return true;
	}

	private static String receive() {
		if (!cSock.isConnected())
			return null;
		String ret = new String();
		String temp = new String();
		try {
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(cSock.getInputStream()));
			if ((ret = inFromClient.readLine()) == null) // Should only read one
															// line from client
				throw new IOException();
			callFunction(ret);
		} catch (IOException e) {
			System.err.println("Unable to read data from client:");
			System.err.println(e.getStackTrace());
			return null;
		}
		return ret;

	}

	private static void callFunction(String clientData) {
		String[] args = clientData.split(" ");
		// Determine what kind of message was sent from client, what command to
		// invoke
		if (args[0].compareTo("VERIFY") == 0) {
			int role = Verification.sVerifyUser(args[1], args[2]);
			send(new String(""+role));
		}
	}

	public static void main(String argv[]) throws Exception {
		while (true) {
			openSocket();
			cSock = sock.accept();
			receive();
			closeSocket();
		}
	}
}