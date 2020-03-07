package src.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
*
* @author ddanielr
*/
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
public ArrayList<Inmate> searchInmates(int i) {
	Connection conn = getConnection();
	ResultSet rs = null;
	ArrayList<Inmate> inmates = new ArrayList<Inmate>();
	try {
		Statement statement = conn.createStatement();
		String s = "SELECT * from inmates WHERE ssn=" + i + ";"; 
		rs = statement.executeQuery(s);
		
		while (rs.next()) {
			Inmate in = new Inmate(rs.getInt("ssn"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("height"), rs.getInt("weight"),
					rs.getString("eye_color"), rs.getString("hair_color"), rs.getInt("tattoos"), rs.getString("mugshot"));
			inmates.add(in);
		}
	} catch(Exception e) {
		System.out.println("Inmate Search failed");
	    System.out.print(e.getMessage());
	  
	} finally {
		try {
			conn.close();
		} catch(SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		System.out.println("DB Connection Closed");
	}
	return inmates;
}


public ArrayList<Inmate> searchInmates(String name) {
	Connection conn = getConnection();
	ResultSet rs = null;
	ArrayList<Inmate> inmates = new ArrayList<Inmate>();
	try {
		Statement statement = conn.createStatement();
		String s = "SELECT * from inmates WHERE last_name='" + name + "' OR first_name='" + name +"';"; 
		rs = statement.executeQuery(s);
		
		while (rs.next()) {
			Inmate in = new Inmate(rs.getInt("ssn"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("height"), rs.getInt("weight"),
					rs.getString("eye_color"), rs.getString("hair_color"), rs.getInt("tattoos"), rs.getString("mugshot"));
			inmates.add(in);
		}
	} catch(Exception e) {
		System.out.println("Inmate Search failed");
	    System.out.print(e.getMessage());
	  
	} finally {
		try {
			conn.close();
		} catch(SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		System.out.println("DB Connection Closed");
	}
	return inmates;
}

public String createInmate(Inmate in) {
	Connection conn = getConnection();
	try {
		Statement statement = conn.createStatement();
		String s = "INSERT INTO inmates VALUES(" + in.toString() + ");";
		System.out.println(s);
		statement.execute(s);
	} catch(Exception e) {
		System.out.println("Inmate Creation failed");
	    System.out.print(e.getMessage());
	  
	} finally {
		try {
			conn.close();
		} catch(SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		System.out.println("DB Connection Closed");
	}
	return "Created Inmate: " + in.ssn;
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
    		"CREATE TABLE cases(suspect_id int, uuid int primary key, case_address text, case_status varchar(10), offense_type text);",
       		"CREATE TABLE inmates(ssn int primary key, first_name varchar, last_name varchar, height int, weight int, eye_color text, hair_color text, tattoos int, mugshot text);",
    		"CREATE TABLE admins(id int primary key, user_name varchar(30), password varchar(64));"
    };
	_runSeeds(createTablesSQL);
	String[] seedTablesSQL = {
			"INSERT INTO admins VALUES(1, 'COP123', 'cmsc495');",
			"INSERT INTO cases VALUES(218445657, 12, '1428 Elm Street', 'closed', 'assault and battery');",
			"INSERT INTO cases VALUES(212325613, 11, '370 Beech Street Highland Park, IL 60035', 'closed', 'Destruction of Property');",
			"INSERT INTO inmates VALUES(218445657, 'john', 'doe', 72, 240, 'brown', 'black', 4, 'src/main/resources/8475.jpg');",
			"INSERT INTO inmates VALUES(212325613, 'john', 'philips', 62, 200, 'grey', 'blue', 0, 'src/main/resources/8375.jpg');"
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