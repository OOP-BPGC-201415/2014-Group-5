package nirmaanam;
import nirmaanam.DatabaseParam.ParamTypes;
import java.util.*;
import java.sql.*;

class SelectQuery extends DatabaseQuery{
	
	public SelectQuery(Database database, String table){
		super(database,table);
	}
	String formSQL(){
		String fields = "";
		
		
		for(DatabaseParam param : params){
			fields+= param.field  +"=? AND ";
		}
		fields =  fields.substring(0,fields.length() - 4);
		System.out.println("SELECT * FROM " + table + " WHERE " + fields );
		return "SELECT * FROM " + table + " WHERE " + fields ;
		
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
