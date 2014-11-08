import java.sql.*;
static class Database{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/test";
	// Database credentials
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	
	public Database(boolean connectNow=true){
		if(connectNow)
			connect();
	}
	
	public void connect(){
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
	}
	
}