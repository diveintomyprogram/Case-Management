
public class Display {

	public static void main(String[] args) {
		String dbHost = System.getenv("DATABASE_HOST");
		if (dbHost == null) {
			// Set default value
			dbHost = "192.168.99.100";
		}
		Database db = new Database(dbHost, "case", "CMSC495", "SuperSecret");
		db.seed();
	}

}
