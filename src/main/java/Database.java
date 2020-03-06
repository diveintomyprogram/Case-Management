package src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

private String dbHost, dbName, dbUser, dbPassword;
private int port=5432; 

public Database() {
	dbHost="localhost";
	dbName="test";
	dbUser="test";
	dbPassword="password";
}

public Database(String host, String name, String user, String password) {
	dbHost=host;
	System.out.println("Host:" + host);
	dbName=name;
	dbUser=user;
	dbPassword=password;
}

private Connection getConnection() {
	Connection conn = null;
	try {
		//Ensure that the class is loaded
		Class.forName("org.postgresql.Driver");
		// Attempt a connection to the database
		conn = DriverManager.getConnection("jdbc:postgresql://" + dbHost + ":" + port + "/" + dbName,
				dbUser, dbPassword);
	} catch (Exception e) {
		e.printStackTrace();
		System.err.println(e.getClass().getName()+": "+e.getMessage());
		System.exit(0);
	}
	return conn;
}

public void seed() {
	String[] seedCasesSQL = { 
    		"CREATE TABLE cases(suspect_id int primary key, uuid int, case_status varchar, case_photos text, offense_id int);",
    		"CREATE TABLE inmates(suspect_id int primary key, uuid int, case_status varchar, case_photos text, offense_id int);"    		
    };
	runQueries(seedCasesSQL);
}

public void runQueries(String[] strings) {
	Connection conn = getConnection();
    try {
    	for (String line : strings) { 	   	
		    Statement statement = conn.createStatement();
		    statement.executeUpdate(line);
		    statement.close();
		    System.out.println("Seeding Database ");
    	}
	} catch(Exception e) {
		System.out.println("Database seeding failed");
	    System.out.print(e);
	  
	} finally {
		try {
			conn.close();
		} catch(SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		System.out.println("DB Connection Closed");
	}   
}

}