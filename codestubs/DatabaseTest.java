package nirmaanam;

import nirmaanam.Database.*;
import nirmaanam.DatabaseQuery.*;
import nirmaanam.DatabaseParam.*;
import java.sql.*;

class DatabaseTest{
	public static void main(String args[]){
		
		try{
			Database db = Database.getDB();
			//InsertQuery iq = db.createInsertQuery("testtable").addParam("id",101).addParam("name","sasi").execute();			System.out.println(iq.getResult());
			
			ResultSet rs = db.select("testtable").addParam("id",101).execute().getResultSet();
			while(rs.next()){
				System.out.print("ID: " + rs.getInt("id"));
				System.out.print(", Name: " + rs.getString("name"));
			}
			
		}catch(Exception e){
			System.out.println("EXCEPTION!" + e);
			e.printStackTrace(System.out);
		}
		
		
	}
}