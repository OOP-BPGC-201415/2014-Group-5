package nirmaanam;
import nirmaanam.DatabaseParam.ParamTypes;
import java.util.*;
import java.sql.*;

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
			whr+= w.field +"=? AND";
		}
		
		
		fields=  fields.substring(0,fields.length() - 2);
		whr=  whr.substring(0,whr.length() - 4);
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