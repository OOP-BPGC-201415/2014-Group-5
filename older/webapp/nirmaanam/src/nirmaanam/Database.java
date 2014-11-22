package nirmaanam;

import java.sql.*;
//import java.util.HashMap;
import java.util.ArrayList;
import nirmaanam.DatabaseQuery;
import nirmaanam.DatabaseParam.ParamTypes;

class Database{
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/nirmaanam";
	// Database credentials
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";
	
	public static Database mainInstance = null;
	static Connection conn;
	
	public Database() throws SQLException{		connect();	}
	
	/**
		Turns off autocommit for transaction
	**/
	public void beginTransaction() throws SQLException{
		conn.setAutoCommit(false);
	}
	
	public void commit() throws SQLException{
		conn.commit();
		conn.setAutoCommit(true);
	}
	
	public void rollback() throws SQLException{
		conn.rollback();
		conn.setAutoCommit(true);
	}
	/**
		Establishes a connection to the mysql server
	**/
	public void connect() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
		}catch(ClassNotFoundException CNFE){
			throw(new SQLException("Could not load the drivers"));
		}
	}
	
	/**
		Returns the connection object in use by this instance
	*/
	public Connection getConn(){
		return conn;
	}
	
	/**
		Creates a SelectQuery object for select queries and returns it for use.
	**/
	public SelectQuery select(String table){
		return new SelectQuery(this,table);
	}
	
	/**
		Creates an updateQuery object for insert queries and returns it for use.
	**/
	public UpdateQuery update(String table){
		return new UpdateQuery(this,table);
	}
	
	/**
		Creates an insertQuery object for insert queries and returns it for use.
	**/
	public InsertQuery insert(String table){
		return new InsertQuery(this,table);
	}
	
	/**
		returns the instance that has the common connection
	**/
	public static Database getDB() throws SQLException{
		if(mainInstance == null ){
			mainInstance = new Database();
		}
		return mainInstance;
	}
	//Few validation functions
	/**
		Checks if the input is invalid(0)
	**/
	Database checkInput(int... args) throws IncompleteFieldException{
		for(int arg: args){
			if(arg==0)
				;//throw(new IncompleteFieldException(""));
		}
		return this;
	}
	/**
		Checks if the input is invalid( "" ) else Throws IncompleteFieldException
	**/

	Database checkInput(String... args) throws IncompleteFieldException{
		for(String arg: args){
			if(arg=="")
				;//throw(new IncompleteFieldException(""));
		}
		return this;
	}
	
	/**
		Checks if the input is invalid( null object )
	**/
	/*
	Database checkInput(Object... args) throws IncompleteFieldException{
		for(Object arg: args){
			if(arg==null)
				throw(new IncompleteFieldException IFE());
		}
		return this;
	}
	*/
}