import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
   public static void main(String args[]) {
	String dbHost = "null";
   	try {
      		String tmp = System.getenv("DATABASE_HOST");
		dbHost = tmp;
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
   }
}
