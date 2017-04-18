import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	public static Connection getConnection() {
		Connection conn = null;
		try {

			System.out.println("Username: ");
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/internship", "postgres",
					"admin");

			if (conn != null) {
				System.out.println("Connection successful!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
