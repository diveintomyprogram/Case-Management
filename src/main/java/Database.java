package src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

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

public Boolean adminLogin(String username, String password) {
	String check_auth = "SELECT EXISTS (SELECT id FROM admins WHERE user_name='" + username + "' AND password='" + password + "');";
	Connection conn = getConnection();
	ResultSet rs;
	boolean isAuth = false;
	
	try {
		Statement statement = conn.createStatement();
		rs = statement.executeQuery(check_auth);
		// Go to the first result in the set
		rs.next(); 
		isAuth = rs.getBoolean(1);
	} catch(Exception e) {
		System.out.println("Database Query failed");
	    System.out.print(e.getMessage());
	  
	} finally {
		try {
			conn.close();
		} catch(SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		System.out.println("DB Connection Closed");
	}
	return isAuth;
}

public void seed() {
	String[] createTablesSQL = { 
    		"CREATE TABLE cases(suspect_id int primary key, uuid int, case_address text, case_status varchar(10), offense_id int);",
    		"CREATE TABLE inmates(ssn int primary key, first_name varchar, last_name varchar, height int, weight int, eye_color text, hair_color text, tattoos int, mugshot text, offense_id int);",
    		"CREATE TABLE admins(id int primary key, user_name varchar(30), password varchar(64));"
    };
	_runSeeds(createTablesSQL);
	String[] seedTablesSQL = {
			"INSERT INTO admins VALUES(1, 'COP123', 'cmsc495');"
	};
	_runSeeds(seedTablesSQL);
}

private void _runSeeds(String[] strings) {
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