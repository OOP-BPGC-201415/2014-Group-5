package nirmaanam;

import java.sql.*;
import java.util.HashMap;

//Mock class for testing
class Database{
	static ResultSet mock;
	
	public static void setMock(ResultSet rs){
		mock = rs;
	}
	
	public static ResultSet query(String dummy){
		return mock;
	}
}

/*

static class TargetDatabase{
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
	
	public void insert(String table, ArrayList<DatabaseParam> params){
		insertStatment = conn.prepareStatement(updateString);
		for(param : params){
			if(param.type == DatabaseParam.INT){
				//insertStatment.
			}
			else if(param.type == DatabaseParam.STRING){
				//insertStatment.
			}
		}
		
	}
	
	public class DatabaseParam{
		int type ;
		String field;
		
		int intVal;
		String stringVal;
		
		public static const int INT = 1;
		public static const int STRING = 2;
		
	}
}
*/