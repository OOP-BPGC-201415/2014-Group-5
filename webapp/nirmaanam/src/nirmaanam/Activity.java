package nirmaanam;

import java.sql.*;
import java.util.ArrayList;

public class Activity implements NirmaanEntity{
	public enum Availability{
		PENDING, AVAILABLE, UNAVAILABLE
	}
	int id;
	String 	name,
			description,
			report;
	Volunteer head;
	Event event;
	//Situational
	ArrayList<Volunteer> assignees;
	//Constants
	
	public Activity()
	{
		this.id=0;
		this.name=null;
		this.description=null;
		this.report=null;
		this.head=null;
	}
	public Activity(String name, String description, Volunteer head)
	{
		this.name=name;
		this.description=description;
		this.head=head;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getName()
	{
		return name;
	}
	public String getDescription()
	{
		return description;
	}
	public Volunteer getHead()
	{
		return head;
	}
	public Event getEvent()
	{
		return event;
	}
	
	
	public void setName(String n)
	{
		this.name=n;
	}
	public void setDescription(String id)
	{
		this.description=id;
	}
	public void setHead(Volunteer v)
	{
		this.head=v;
	}
	public void setEvent(Event e)
	{
		this.event=e;
	}
	public String getReport(){
		return this.report;
	}
	public void setReport(String report) throws SQLException{
		this.report=report;
		Database db = Database.getDB();
		UpdateQuery uq = db.update("activity").addParam("report",report).where("id",this.id).execute();
	}
	
	public void addAssignee(Volunteer v) throws SQLException{
		Database db = Database.getDB();
		InsertQuery iq = db.insert("Activity_assignee").addParam("activity",this.id).addParam("volunteer",v.id).execute();
	}
	
	public ArrayList<Volunteer> getAssignees() throws SQLException{//Does a database query		//Gets a result set		//Returns an array of Volunteers
		Database db = Database.getDB();
		SelectQuery sq = db.select("Activity_assignee").where("activity",this.id).execute();
		ResultSet rs = sq.getResultSet();
		assignees = new ArrayList<Volunteer>();
		while(rs.next()){
			Volunteer v= new Volunteer(rs.getString("name"), rs.getString("bitsID"), rs.getInt("year"));
			v.setId(rs.getInt("id"));
			assignees.add(v);
		}
		return this.assignees;
	}
	public boolean isAssignee(Volunteer v) throws SQLException {
		Database db = Database.getDB();
		SelectQuery sq = db.select("Activity_assignee").where("volunteer",v.id).execute();
		ResultSet rs = sq.getResultSet();
		return (rs.next())?true:false;
	}
	
	public Availability getAvailability(Volunteer v) throws SQLException,EntityNotFoundException {
        Database db = Database.getDB();
		SelectQuery sq = db.select("Activity_assignee").where("activity",this.id).where("volunteer",v.id).execute();
		ResultSet rs = sq.getResultSet();
		if(rs.next()){
			return Availability.values()[rs.getInt("availability")];
		}
		else
			throw(new EntityNotFoundException("Volunteer#"+v.id+" may not be an assignee of Activity#"+this.id));
		
		
	}
	public void confirmAvailability(Volunteer v,Availability av) throws SQLException {
		Database db = Database.getDB();
		UpdateQuery uq = db.update("Activity_assignee").addParam("availability",av.ordinal()).where("activity",this.id).where("volunteer",v.id).execute();
	}
	
	public Activity load(int activityId) throws SQLException,EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("Activity").where("id",activityId).execute();
		ResultSet rs = sq.getResultSet();
		if(rs.next()){
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
			this.description = rs.getString("description");
			this.report = rs.getString("report");
			this.head = new Volunteer().load(rs.getInt("head"));
			this.event = new Event().load(rs.getInt("event"));
		}
		else
			throw(new EntityNotFoundException("Activity#"+activityId+" not found"));
			
		return this;
	} //Loads from the database
	
	public void store() throws SQLException,IncompleteFieldException{
		Database db = Database.getDB();
		db.checkInput(name).checkInput(head.id);
		InsertQuery iq= db.insert("Activity").addParam("name",name).addParam("description",description).addParam("head",head.id).addParam("event",event.id).execute();
		this.id = iq.insertId();
	} //Stores into the database
	
}