package nirmaanam;

import java.sql.*;

class Activity{
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
	Volunteer[] assignees;
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
	
	public void setReport(String report)
	{
		this.report=report;
		
	}
	
	public void addAssignee(Volunteer v){}
	
	public Volunteer[] getAssignees(){return null;
		//Does a database query
		//Gets a result set
		//Returns an array of Volunteers
	}
	public boolean isAssignee(Volunteer v){
		return false;
		//Does a query
		//Gets a result set
		//Returns true or false accordingly
	}
	
	public Availability getAvailability(Volunteer v){
           return null;
		//Does a database query
		//Gets a result set
		//Returns true, false or not set
	}
	public void confirmAvailability(Volunteer v,Availability av){
		
	}
	
	public Activity load(int activityId) throws SQLException,EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("Activity").where("id",activityId).execute();
		ResultSet rs = sq.getResultSet();
		if(rs.next()){
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
			this.description = rs.getString("description");
			this.head = new Volunteer().load(rs.getInt("head"));
		}
		else
			throw(new EntityNotFoundException("Activity#"+activityId+" not found"));
			
		return this;
	} //Loads from the database
	
	public void store() throws SQLException,IncompleteFieldException{
		Database db = Database.getDB();
		db.checkInput(name).checkInput(head.id);
		InsertQuery iq= db.insert("Activity").addParam("name",name).addParam("description",description).addParam("head",head.id).execute();
	} //Stores into the database
	
}