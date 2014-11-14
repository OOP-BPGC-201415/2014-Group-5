package nirmaanam;

import nirmaanam.Database.*;
import nirmaanam.DatabaseQuery.*;
import nirmaanam.DatabaseParam.*;
import java.sql.*;

class DatabaseTest{
	public static void main(String args[]){
		
		try{
			Database db = Database.getDB();
			
			InsertQuery iq = db.insert("testtable").addParam("name","Nandy").execute();
			System.out.println("InsertId is "+ iq.insertId());
			ResultSet rs = db.select("testtable").addParam("id",101).execute().getResultSet();
			while(rs.next()){
				System.out.print("ID: " + rs.getInt("id"));
				System.out.print(", Name: " + rs.getString("name"));
			}
			
			UpdateQuery uq = db.update("testtable").addParam("name","Velayudhan").where("id",101).execute();
			
			System.out.println("UPDATE RESULT IS:"+uq.getResult());
			
		}catch(Exception e){
			System.out.println("EXCEPTION!" + e);
			e.printStackTrace(System.out);
		}
		
		
	}
}