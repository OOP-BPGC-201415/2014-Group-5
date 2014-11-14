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

class InsertQuery extends DatabaseQuery{
	
	public InsertQuery(Database database, String table){
		super(database,table);
	}
	
	String formSQL(){	
		String fields = "( ";
		String values = " ( ";
		for(DatabaseParam param : params){
			fields+= param.field + ',';
			values+= "?,";
		}
		fields =  fields.substring(0,fields.length() - 1) + ')';
		values = values.substring(0, values.length()-1) + ')';
		//System.out.println("INSERT INTO " + table +  fields + " VALUES " + values );
		//return "INSERT INTO " + table +  fields + " VALUES " + values;
		return "REPLACE INTO " + table +  fields + " VALUES " + values;
		
	}
	InsertQuery addParam(String field,int val) throws SQLException{
		return (InsertQuery)super.addParam(field,val);
	}
	
	InsertQuery addParam(String field,String val) throws SQLException{
		return (InsertQuery)super.addParam(field,val);
	}
	
	void bindParams() throws SQLException{
		int i=1;
		//BindParams
		for(DatabaseParam param : params){
			if(param.type == ParamTypes.INT){
				preparedStatement.setInt(i++, param.intVal);
			}
			else if(param.type == ParamTypes.STRING){
				preparedStatement.setString(i++, param.stringVal);
			}
		}
	}
	
	InsertQuery execute() throws SQLException{
		preparedStatement = database.conn.prepareStatement(formSQL());
		bindParams();
		intResult = preparedStatement.executeUpdate();
		return this;
	}
	
	int insertId() throws SQLException{
		ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
		if (generatedKeys.next())
			return generatedKeys.getInt(1);
		else
			throw new SQLException("Insert ID could not be obtained.");
	}
}

class SelectQuery extends DatabaseQuery{
	
	public SelectQuery(Database database, String table){
		super(database,table);
	}
	String formSQL(){
		String fields = " WHERE ";
		
		
		for(DatabaseParam param : params){
			fields+= param.field  +"=? ";
		}
		fields =  fields.substring(0,fields.length() - 1);
		
		return "SELECT * FROM " + table + fields ;
		
	}
	SelectQuery where(String field, int val) throws SQLException{
		addParam(field,val);
		return this;
	}
	
	SelectQuery where(String field, String val) throws SQLException{
		addParam(field,val);
		return this;
	}
	
	void bindParams() throws SQLException{
		//BindParams
		int i=1;
		for(DatabaseParam param : params){
			if(param.type == ParamTypes.INT){
				preparedStatement.setInt(i++, param.intVal);
			}
			else if(param.type == ParamTypes.STRING){
				preparedStatement.setString(i++, param.stringVal);
			}
		}
	}
	
	SelectQuery execute() throws SQLException{
		preparedStatement = database.conn.prepareStatement(formSQL());
		bindParams();
		resultSet = preparedStatement.executeQuery();
		return this;
	}
	
	SelectQuery addParam(String field,int val) throws SQLException{
		return (SelectQuery)super.addParam(field,val);
	}
	
	SelectQuery addParam(String field,String val) throws SQLException{
		return (SelectQuery)super.addParam(field,val);
	}
}

class UpdateQuery extends DatabaseQuery{
	
	ArrayList<DatabaseParam> where;
	public UpdateQuery(Database database, String table){
		super(database,table);
		where = new ArrayList<DatabaseParam>();
	}
	
	String formSQL(){
		String whr = " ";
		String fields = "";
		
		for(DatabaseParam param : params){
			fields+= param.field +"=?, ";
		}
		
		
		for(DatabaseParam w : where){
			whr+= w.field +"=? ";
		}
		fields =  fields.substring(0,fields.length() - 2);
		//System.out.println("UPDATE " + table + " SET " + fields + " WHERE " + whr);
		return "UPDATE " + table + " SET " + fields + " WHERE " + whr;
	}
	
	UpdateQuery where(String field, int val) throws SQLException{
		where.add(new DatabaseParam(field, val));
		return this;
	}
	
	UpdateQuery where(String field, String val) throws SQLException{
		where.add(new DatabaseParam(field, val));
		return this;
	}
	
	void bindParams() throws SQLException{
		//BindParams
		int i=1;
		
		for(DatabaseParam param : params){
			if(param.type == ParamTypes.INT){
				preparedStatement.setInt(i++, param.intVal);
			}
			else if(param.type == ParamTypes.STRING){
				preparedStatement.setString(i++, param.stringVal);
			}
		}
		
		for(DatabaseParam w : where){
			if(w.type == ParamTypes.INT){
				preparedStatement.setInt(i++, w.intVal);
			}
			else if(w.type == ParamTypes.STRING){
				preparedStatement.setString(i++, w.stringVal);
			}
		}
	}
	
	UpdateQuery execute() throws SQLException{
		preparedStatement = database.conn.prepareStatement(formSQL());
		bindParams();
		intResult = preparedStatement.executeUpdate();
		return this;
	}
	
	UpdateQuery addParam(String field,int val) throws SQLException{
		return (UpdateQuery)super.addParam(field,val);
	}
	
	UpdateQuery addParam(String field,String val) throws SQLException{
		return (UpdateQuery)super.addParam(field,val);
	}
	
}