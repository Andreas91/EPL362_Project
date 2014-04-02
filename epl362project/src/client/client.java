package client;

import java.io.*;
import java.net.*;

class client {

	private static Socket sock;

	public static int verifyUser(String username, String password)
			throws IOException {
		if (!openSocket())
			throw new IOException();
		if (!send("VERIFY", username, password))
			throw new IOException();
		String serverResponse = receive();
		int retVal;
		try {
			retVal = Integer.parseInt(serverResponse); // Server should send one
														// int as response in
														// this case

		} catch (NumberFormatException e) {
			throw new IOException();
		}
		closeSocket();
		return retVal;
	}

	private static boolean openSocket() {
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

	private static void closeSocket() {
		if (!sock.isClosed())
			try {
				sock.close();
			} catch (IOException e) {
				System.err.println("Unable to close socket:");
				System.err.println(e.getStackTrace());
			}
	}

	private static boolean send(String... data) {
		if (!sock.isConnected())
			return false;
		try {
			DataOutputStream outToServer = new DataOutputStream(
					sock.getOutputStream());
			for (String i : data)
				outToServer.writeBytes(i + ' ');
		} catch (IOException e) {
			System.err.println("Unable to send data to server:");
			System.err.println(e.getStackTrace());
			return false;
		}
		return true;
	}

	private static String receive() {
		if (!sock.isConnected())
			return null;
		String ret = new String();
		String temp = new String();
		try {
			BufferedReader inFromServer = new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
			while ((temp = inFromServer.readLine()) != null)
				ret = ret + temp + '\n';
		} catch (IOException e) {
			System.err.println("Unable to read data from server:");
			System.err.println(e.getStackTrace());
			return null;
		}
		return ret;

	}
}
