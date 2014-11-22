package nirmaanam;
import nirmaanam.DatabaseParam.ParamTypes;
import java.util.*;
import java.sql.*;
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
		preparedStatement = database.conn.prepareStatement(formSQL(),PreparedStatement.RETURN_GENERATED_KEYS); //PreparedStatement.RETURN_GENERATED_KEYS is for getting insertId
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
