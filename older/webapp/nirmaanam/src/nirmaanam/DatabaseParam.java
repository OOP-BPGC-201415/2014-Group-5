package nirmaanam;
import java.sql.*;

class DatabaseParam{
	ParamTypes type;
	String field;
	
	int intVal;
	String stringVal;
	
	enum ParamTypes{
		INT, STRING
	}
	
	DatabaseParam(String field,int intVal){
		this.type = ParamTypes.INT;
		this.field = field;
		this.intVal = intVal;
		
	}
	
	DatabaseParam(String field,String stringVal){
		this.type = ParamTypes.STRING;
		this.field = field;
		this.stringVal = stringVal;
	}
	
}

//class SelectQuery extends DatabaseQuery{
	
//}