package nirmaanam;
import nirmaanam.*;
import java.sql.*;

class DirtyTest{
	public static void main(String args[]) throws SQLException,ClassNotFoundException,IncompleteFieldException{
		
		 
		 Volunteer vol= new Volunteer("SomeVolunteer","XXXXXXXXXXXG",3);
		 Vertical vert= new Vertical("testVertical", "Some description", vol);
		 Database db=null;
		 try{
			db =  Database.getDB();
			db.beginTransaction();
			vol.setVertical(vert);
			vert.setHead(vol);
			db.beginTransaction();
			
			vol.store();
			vert.store();
			vert.addVolunteer(vol);
			db.commit();
		}catch(Exception e){
			System.out.println("EXCEPTION " + e);
			e.printStackTrace(System.out);
			db.rollback();
		}
	
	}
}