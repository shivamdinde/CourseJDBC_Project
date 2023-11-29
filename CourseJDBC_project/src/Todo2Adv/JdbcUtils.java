package Todo2Adv;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils {
	
	public static Connection buildConnection() throws Exception {
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		Class.forName(driverClassName);
		String connectionUrl = "jdbc:mysql://localhost:3306/CourseProjectAdv?useSSL=false";
		String userId = "root";
		String password = "shivam";
			
		Connection	dbConnection = DriverManager.getConnection(connectionUrl, userId, password);
		return dbConnection;
	}
}
