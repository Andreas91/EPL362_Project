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

public class server implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ServerSocket sock;
	private static Socket cSock;
	private static boolean dbDriverLoaded = false;
	private static Connection conn = null;
	
	private static boolean getDBConnection() {
		
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
			JOptionPane.showMessageDialog(null, "Cannot connect to the DB!\nGot error:" +
			e.getErrorCode() + "\nSQL State: " + e.getSQLState() + "\nMessage: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	private static boolean openSocket() {
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
	

	private static void closeSocket() {
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
	

	
	private static ResultSet getResultSet(String str) {
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
	
	
	public static Object[][] executeQuery(String query) throws SQLException{
	    ResultSet rs = getResultSet(query);
	    if (rs==null) return null; 
	    ResultSetMetaData rsMetaData = rs.getMetaData();
	    int columnCount = rsMetaData.getColumnCount();
	    ArrayList<Object[]> result = new ArrayList<Object[]>();
	    Object[] header = new Object[columnCount];
	    
	    // Get the labels
	    for (int i=1; i <= columnCount; i++){
	        Object label = rsMetaData.getColumnLabel(i);
	        header[i-1] = label;
	    }
	    
	    // Get the results
	    while (rs.next()){
	        Object[] str = new Object[columnCount];
	        for (int i=1; i <= columnCount; i++){
	            Object obj = rs.getObject(i);
	            str[i-1] = obj;
	        }
	        result.add(str);
	    }
	    
	    // Create 2D Object array from ResultSet
	    int resultLength = result.size();
	    Object[][] finalResult = new Object[resultLength+1][columnCount];
	    try{
	    	finalResult[0] = header;
	    	for(int i=1;i<=resultLength;i++){
		        Object[] row = result.get(i-1);
		        finalResult[i] = row;
		    }
	    }catch(ArrayIndexOutOfBoundsException e){
	    	return null;
	    }
	    return finalResult;
	}
	
	public static Object executeUpdate(String str){
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
		
		System.out.print("> Connecting with database...");
		if (!getDBConnection()){
			System.out.println("Error!");
			return;
		}
		System.out.println("Done!");
		
		System.out.println("> Waiting for client...");
		while (true) {
			openSocket();
			ObjectInputStream inFromClient = new ObjectInputStream(cSock.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(cSock.getOutputStream());
			String Oin = (String)inFromClient.readObject();
			System.out.println("> Receive: " + Oin);
			Object Oout=null;
			if (Oin.charAt(0)=='S' || Oin.charAt(0)=='s')
				Oout = executeQuery(Oin);
			else
				Oout = executeUpdate(Oin);
			outToClient.writeObject(Oout);
			closeSocket();
		}
	}
}