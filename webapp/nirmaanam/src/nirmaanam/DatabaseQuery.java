package nirmaanam;

import nirmaanam.Database;
import nirmaanam.DatabaseParam;
import nirmaanam.DatabaseParam.ParamTypes;
//import nirmaanam.*;
import java.sql.*;
import java.util.*;

abstract class DatabaseQuery{
	ArrayList<DatabaseParam> params;
	String table;
	Database database;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	int intResult;
	
	DatabaseQuery(Database database,String table){
		params = new ArrayList<DatabaseParam>();
		this.table = table;
		this.database = database;
	}
	/**
		Adds a parameter to the query. What it does depends on which query ( see derived classes )
		Currently supports only int and String as values
	**/
	DatabaseQuery addParam(String field,int val) throws SQLException{	//Returns this so we can chain!
		params.add(new DatabaseParam(field,val));
		return this;
	}
	
	DatabaseQuery addParam(String field,String val) throws SQLException{
		params.add(new DatabaseParam(field, val));
		return this;
	}
	
	
	/**
		Generates the parametrized SQL of the query
	**/
	abstract String formSQL();//Code to construct the SQL
	
	/**
		Binds the values to the parametrized fields
	**/
	abstract void bindParams() throws SQLException;//Code to construct the SQL
	
	
	/**
		Calls formSQL, bindParams and stores the result after calling the relevant method
	**/
	abstract DatabaseQuery execute() throws SQLException;
	
	//abstract void execute() throws SQLException;
	
	
	/**
		Returns the ResultSet for select queries
	**/
	ResultSet getResultSet(){
		return resultSet;
	}
	
	
	/**
		Returns the rows affected for relevant queries
	**/
	int getResult(){
		return intResult;
	}
}
