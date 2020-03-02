import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
   public static void main(String args[]) {
	var dbHost = "";
  	try {
         dbHost = System.getenv("DATABASE_HOST");
	}
	catch(NullPointerException e) {
		System.out.println("Cannot find value 'DATABASE_HOST'");
   }
   Connection c = null;
   try {
      Class.forName("org.postgresql.Driver");
      c = DriverManager.getConnection("jdbc:postgresql://" + dbHost + ":5432/case",
	 	                                "CMSC495",
					                       "SuperSecret");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
      seed(c);
   }

   public static void seed(Connection database) {
      // Only do this if the table values do not previously exist
      String seedSQL = "CREATE TABLE cases(suspect_id int primary key, uuid int, case_status varchar, case_photos text, offense_id int);";
      try {
               // Will probably need a mutex class around creating a db pool object.
               Statement statement = database.createStatement();
               statement.executeUpdate(seedSQL);
               statement.close();
               System.out.println("Seeded Database");
      } catch(Exception e) {
            System.out.println("Database seeding failed");
            System.out.print(e);
      }
   }

   public static void query(Connection database, String query){
      // Do nothing for now, figure out if we want this in the main class or in a separate database class
   }
}
