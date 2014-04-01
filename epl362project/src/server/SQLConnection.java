package server;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLConnection db = new SQLConnection();
		conn = db.getDBConnection();

		if (conn == null) {
			System.err.println("Cannot connect to database through main program");
			return;
		}
	}

}
