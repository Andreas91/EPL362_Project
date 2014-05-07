/**
 * Copyright 2014 Andreas Andreou & Maria Christodoulou
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package server;

import java.io.*;
import java.net.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * The server class implements the server communicating with the SQLServer 
 * database to execute statements (sent and received as Objects).
 * @author Maria Christodoulou
 * @author Andreas Andreou
 * @version 1.0
 */
public class server implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Server side socket
	 */
	private static ServerSocket sock;
	
	/**
	 * Client side socket
	 */
	static Socket cSock;
	
	/**
	 * Boolean to check if JDBC driver is loaded
	 */
	private static boolean dbDriverLoaded = false;
	
	/**
	 * Connection to SQLServer database
	 */
	private static Connection conn = null;

	/**
	 * Connects to the SQLServer database.
	 * 
	 * @return <tt>true</tt> on success, <tt>false</tt> otherwise
	 */
	static boolean getDBConnection() {

		String dbConnString = "jdbc:sqlserver://apollo.in.cs.ucy.ac.cy:1433;databaseName=lawcs;user=lawcs;password=H9pCFzXb;";

		// Load the driver
		if (!dbDriverLoaded)
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dbDriverLoaded = true;
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Cannot load DB driver!");
				return false;
			}

		// Establish connection
		try {
			if (conn == null || conn.isClosed())
				conn = DriverManager.getConnection(dbConnString);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Cannot connect to the DB!\nGot error:" + e.getErrorCode()
							+ "\nSQL State: " + e.getSQLState() + "\nMessage: "
							+ e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Closes an empty SQLServer connection.
	 * 
	 * @return <tt>true</tt> on success, <tt>false</tt> otherwise
	 */
	static boolean closeDBConnection() {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Opens a socket for accepting connections from clients.
	 * 
	 * @return <tt>true</tt> on success, <tt>false</tt> otherwise
	 */
	static boolean openSocket() {
		try {
			sock = new ServerSocket(6789);
			cSock = sock.accept();
		} catch (IOException e) {
			System.err.println("Unable to create server socket:");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Closes the server's socket.
	 */
	static void closeSocket() {
		if (!sock.isClosed())
			try {
				sock.close();
			} catch (IOException e) {
				System.err.println("Unable to close socket:");
				e.printStackTrace();
			}
		if (!cSock.isClosed())
			try {
				cSock.close();
			} catch (IOException e) {
				System.err.println("Unable to close socket:");
				e.printStackTrace();
			}
	}

	/**
	 * Executes a query on the SQLServer database and returns its ResultSet.
	 * 
	 * @param str The SQL query to be executed
	 * @return The ResultSet returned from execution
	 */
	static ResultSet getResultSet(String str) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(str);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Sends a SQL SELECT query to be executed on the SQLServer database 
	 * (calls the getResultSet() function) and returns a modified ResultSet.
	 * 
	 * @param query The SQL query to be sent for execution
	 * @return A modified ResultSet to a Object[][] array with the first row 
	 * being the names of the returned columns of the ResultSet, or <tt>null</tt> 
	 * if a problem occurs.
	 * @throws SQLException If there is a problem processing the ResultSet
	 */
	public static Object[][] executeQuery(String query) throws SQLException {
		ResultSet rs = getResultSet(query);
		if (rs == null)
			return null;
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		ArrayList<Object[]> result = new ArrayList<Object[]>();
		Object[] header = new Object[columnCount];

		// Get the labels
		for (int i = 1; i <= columnCount; i++) {
			Object label = rsMetaData.getColumnLabel(i);
			header[i - 1] = label;
		}

		// Get the results
		while (rs.next()) {
			Object[] str = new Object[columnCount];
			for (int i = 1; i <= columnCount; i++) {
				Object obj = rs.getObject(i);
				str[i - 1] = obj;
			}
			result.add(str);
		}

		// Create 2D Object array from ResultSet
		int resultLength = result.size();
		Object[][] finalResult = new Object[resultLength + 1][columnCount];
		try {
			finalResult[0] = header;
			for (int i = 1; i <= resultLength; i++) {
				Object[] row = result.get(i - 1);
				finalResult[i] = row;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return finalResult;
	}

	/**
	 * Executes a SQL INSERT, UPDATE or DELETE query on the SQL Server.
	 * @param str The SQL query to be executed
	 * @return <tt>true</tt> on success, <tt>false</tt> otherwise (cast as Object)
	 */
	public static Object executeUpdate(String str) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(str);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String argv[]) throws Exception {
		// Connect with database
		System.out.print("> Connecting with database...");
		if (!getDBConnection()) {
			System.out.println("Error!");
			return;
		}
		System.out.println("Done!");
		System.out.println("> Waiting for client...");
		
		// Keep running to respond to client connections
		while (true) {
			// Open socket to listen to connections
			openSocket();
			// Input / output streams to communicate with client
			ObjectInputStream inFromClient = new ObjectInputStream(cSock.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(cSock.getOutputStream());
			// Read data sent from client
			String Oin = (String) inFromClient.readObject();
			System.out.println("> Receive: " + Oin);
			// Sent query to execution according to type of statement (SELECT, INSERT...)
			Object Oout = null;
			if (Oin.charAt(0) == 'S' || Oin.charAt(0) == 's')
				Oout = executeQuery(Oin);
			else
				Oout = executeUpdate(Oin);
			// Send result to client
			outToClient.writeObject(Oout);
			// Close client connection
			closeSocket();
		}
	}
}