package server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnection {

	private static boolean dbDriverLoaded = false;
	private static Connection conn = null;

	Connection getDBConnection() {
		// Insert database server, name and password here!
		// databaseName field might not be needed
		String dbConnString = "jdbc:sqlserver://<host>:1433;databaseName=<dbname>;user=<username>;password=<passwd>;";

		// Load the driver
		if (!dbDriverLoaded)
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dbDriverLoaded = true;
			} catch (ClassNotFoundException e) {
				System.err.println("Cannot load DB driver!");
				return null;
			}

		// Establish connection
		try {
			if (conn == null)
				conn = DriverManager.getConnection(dbConnString);
			else if (conn.isClosed())
				conn = DriverManager.getConnection(dbConnString);
			conn.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
		} catch (SQLException e) {
			System.err.print("Cannot connect to the DB!\nGot error: ");
			System.err.print(e.getErrorCode());
			System.err.print("\nSQL State: ");
			System.err.println(e.getSQLState());
			System.err.println(e.getMessage());
		}
		return conn;
	}

	public boolean insertAppointment(String type, int branch, Date aDate) {
		if (conn == null)
			return false;
		try {
			String statement = "INSERT INTO dbo.APPOINTMENT (ATYPE, BID, ADATE) VALUES (?,?,?)";
			PreparedStatement query = conn.prepareStatement(statement);
			query.setString(1, type);
			query.setInt(2, branch);
			query.setDate(3, aDate);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean insertClient(String firstName, String lastName, boolean flagged){
		if (conn == null)
			return false;
		try {
			String statement = "INSERT INTO dbo.CLIENT (FLAG,FNAME,LNAME,DELETED) VALUES (?,?,?,?)";
			PreparedStatement query = conn.prepareStatement(statement);
			query.setBoolean(1, flagged);
			query.setString(2, firstName);
			query.setString(3, lastName);
			query.setBoolean(4, false);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertLawyer(String firstName, String lastName, String username, String email){
		if (conn == null)
			return false;
		try {
			String statement = "INSERT INTO dbo.LAWER (USERNAME,FNAME,LNAME,EMAIL) VALUES (?,?,?,?)";
			PreparedStatement query = conn.prepareStatement(statement);
			query.setString(1, username);
			query.setString(2, firstName);
			query.setString(3, lastName);
			query.setString(4, email);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean insertUser(String username, int password, int role){
		if (conn == null)
			return false;
		try {
			String statement = "INSERT INTO dbo.USERS (USERNAME,PASSWORD,ROLE) VALUES (?,?,?)";
			PreparedStatement query = conn.prepareStatement(statement);
			query.setString(1, username);
			query.setInt(2, password);
			query.setInt(3, role);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertWarning(String username, int status, Date wDate, String details){
		if (conn == null)
			return false;
		try {
			String statement = "INSERT INTO dbo.WARNINGS (USERNAME,STATUS,WDATE,DETAILS) VALUES (?,?,?,?)";
			PreparedStatement query = conn.prepareStatement(statement);
			query.setString(1, username);
			query.setInt(2, status);
			query.setDate(3, wDate);
			query.setString(4, details);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean insertCase(int appointment, int client, int laywer, int transaction, boolean attended, boolean scheduled){
		if (conn == null)
			return false;
		try {
			String statement = "INSERT INTO dbo.CASES (AID,CID,LID,TID,ATTENDED,SCHEDULED) VALUES (?,?,?,?,?,?)";
			PreparedStatement query = conn.prepareStatement(statement);
			query.setInt(1, appointment);
			query.setInt(2, client);
			query.setInt(3, laywer);
			query.setInt(4, transaction);
			query.setBoolean(5, attended);
			query.setBoolean(6, scheduled);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLConnection db = new SQLConnection();
		conn = db.getDBConnection();

		if (conn == null) {
			System.err
					.println("Cannot connect to database through main program");
			return;
		}
	}

}
