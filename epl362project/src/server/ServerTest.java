/**
 * Copyright 2014 Maria Christodoulou
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

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.junit.Test;

/**
 * Unit testing file for server and client components
 * @author Maria Christodoulou
 * @version 1.0
 *
 */
public class ServerTest {

	/**
	 * Tests whether the appropriate tables exist in the database.
	 */
	@Test
	public void checkTables() {
		// Open database connection
		assertTrue("Error in getDBConnection()!", server.getDBConnection());
		// Check if database tables exist
		String[] tables = { "USERS", "APPOINTMENT", "CASES", "CLIENT",
				"COMMENTED", "DISPUTE", "HISTORY", "LAWER", "LEGAL_OPINION",
				"LEGAL_RECOMMENDATION", "MEETING", "REQUESTS", "ROLES",
				"WARNINGS" };
		for (String i : tables)
			assertNotNull("Error in getResultSet()!",
					server.getResultSet("SELECT * FROM dbo." + i));
		// Close database connection
		assertTrue("Error in closeDBConnection()!", server.closeDBConnection());
	}

	/**
	 * Tests whether the server responds correctly when an invalid SQL query 
	 * is sent.
	 */
	@Test
	public void checkTableSyntax() {
		// Open database connection
		assertTrue("Error in getDBConnection()!", server.getDBConnection());
		// Check if invalid table returns proper null result
		String str = "WARN";
		assertNull("Error in getResultSet() when table not exists!",
				server.getResultSet("SELECT * FROM dbo." + str));
		// Close database connection
		assertTrue("Error in closeDBConnection()!", server.closeDBConnection());
	}

	/**
	 * Checks INSERT, UPDATE, DELETE SQL statements.
	 */
	@Test
	public void checkDatabaseFunctions() {
		// Open database connection
		assertTrue("Error in getDBConnection()!", server.getDBConnection());
		// Insert record
		String[] str = {
				"INSERT INTO dbo.USERS(USERNAME,PASSWORD,ROLE) VALUES('test','test',0)",
				"UPDATE dbo.USERS SET ROLE = 1 WHERE USERNAME = 'test'",
				"DELETE FROM dbo.USERS WHERE USERNAME = 'test'" };
		for (String i : str)
			assertTrue("Error in executeUpdate()! -> " + i,(boolean) server.executeUpdate(i));
		// Close database connection
		assertTrue("Error in closeDBConnection()!", server.closeDBConnection());
	}

	/**
	 * Checks socket connection between server / client, using a thread class 
	 * to act as a client.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void checkSocket() throws IOException, ClassNotFoundException,
			SQLException {
		// Test socket connection between server / 'client' (thread)
		String str = "BOUNCING STRING";
		
		// Thread to pose as client (threads used to avoid blocking accept
		new thClient(str).start();

		// Open connection
		assertTrue("Error in openSocket()!", server.openSocket());
		
		// Check input / output streams
		assertNotNull("Error cSock input stream is null!", server.cSock.getInputStream());
		ObjectInputStream in = new ObjectInputStream(server.cSock.getInputStream());
		assertNotNull("Error socket input stream is null!", in);
		assertNotNull("Error cSock output stream is null!", server.cSock.getOutputStream());
		ObjectOutputStream out = new ObjectOutputStream(server.cSock.getOutputStream());
		assertNotNull("Error socket output stream is null!", out);

		// Receive object from client
		String Oin = (String) in.readObject();
		assertNotNull("Error, server read NULL!", Oin);
		
		// Return object to client
		out.writeObject(Oin);
	
		// Close connection
		server.closeSocket();
	}

	Socket sock;
	Object sout;

	// Thread to serve as client for testing server socket
	private class thClient extends Thread {
		// Object to be bounced
		Object o;

		// Thread initializer object set from test
		public thClient(Object obj) {
			o = obj;
		}

		// Send data to server
		private void handleSocketIn(Object q) throws UnknownHostException,
				IOException {
			sock = new Socket("localhost", 6789);
			ObjectOutputStream out = new ObjectOutputStream(
					sock.getOutputStream());
			out.writeObject(q);
		}

		// Receive same data from server
		private void handleSocketOut() throws IOException,
				ClassNotFoundException {
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			sout = in.readObject();
			assertNotNull("Error, server responded with NULL", sout);
			assertEquals("Error, server did not sent received obj!", o, sout);
			sock.close();
		}

		public void run() {
			try {
				handleSocketIn(o);
				handleSocketOut();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
