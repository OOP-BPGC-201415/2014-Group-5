package nirmaanam;
import nirmaanam.*;
import nirmaanam.Activity.Availability;
import java.sql.*;

public class Volunteer{
	int id;
	String 	name,
			bitsID;
	int year;
	TimeTable timetable;
	Vertical vertical;
	Availability avail;
	
	public Volunteer()
	{
		this.id=0;
		this.name=null;
		this.bitsID=null;
		this.year=0;
		this.timetable=null;
		this.vertical=null;
	}
	public Volunteer(String name, String ID, int year){
		this.name=name;
		this.bitsID=ID;
		this.year=year;
	}
	
	
	public int getId(){return id;}
	public void setId(int id){
		this.id=id;
	}
	
	public void setAvailability(Availability a)
	{
		this.avail=a;
	}
	public Availability getAvailability()
	{
		return avail;
	}
	
	public void setVertical(Vertical V)
	{
		this.vertical=V;
	}	//Ideally, We'd call Vertical.addVolunteer(...) instead
	
	public String getName(){return name;}
	public String getBitsID(){return bitsID;}
	public int getYear(){return year;}
	public TimeTable getTimetable(){return timetable;}
	public Vertical getVertical(){return vertical;}
	
	public void setName(String n){
		this.name=n;
	}
	public void setBitsID(String id){
		this.bitsID=id;
	}
	public void setYear(int y){
		this.year=y;
	}
	public void setTimetable(TimeTable tt){
		this.timetable=tt;
	}	
	
	public void store() throws SQLException,IncompleteFieldException{
		Database db = Database.getDB();
		db.checkInput(year,vertical.id).checkInput(name,bitsID);//.checkInput(vertical);
		InsertQuery iq= db.insert("volunteer").addParam("name",name).addParam("bitsID",bitsID).addParam("year",year).addParam("vertical",vertical.id).execute();
		this.id = iq.insertId();
		System.out.println("GOT " + this.id + " AS insertId");
	}
	
	
	public Volunteer load(int volunteerId) throws SQLException,EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("volunteeer").where("id",volunteerId).execute();
		ResultSet rs = sq.getResultSet();
		
		if(rs.next()){
			this.id = rs.getInt("id");
			this.year = rs.getInt("year");
			
			this.name = rs.getString("name");
			this.bitsID = rs.getString("bitsID");
			this.vertical = new Vertical().load(rs.getInt("vertical"));
		}
		else
			throw(new EntityNotFoundException("Volunteer#"+volunteerId+" not found"));
			
		return this;
	}
}