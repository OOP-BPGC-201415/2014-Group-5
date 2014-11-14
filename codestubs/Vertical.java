package nirmaanam;
import java.sql.*;

class Vertical{
	int id;
	String 	name,
			description;
	Volunteer head;
	
	public Vertical(){
		this.name = null;
		this.description= null;
		this.head = null;
	}
	public Vertical(String name, String description, Volunteer head){
		this.name = name;
		this.description= description;
		this.head = head;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}	


	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	
	public Volunteer getHead(){
		return head;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public void setHead(Volunteer v){
		head = v;
	}
	
	
	//TODO
	public void addEvent(Event e){}
	
	public void addVolunteer(Volunteer v) throws SQLException{
		//Call Volunteer.setVertical
		Database db= Database.getDB();
		UpdateQuery uq = db.update("volunteer").addParam("vertical",this.id).where("id",v.id).execute();
	}
	
	public Volunteer[] getVolunteerList(){return null;
		
	}
	
	/*
	General information
	*/
	
	
	public Event[] getEventList(){return null;}
	
	public Activity[] getActivityList(){return null;}
	
	//public Meeting[] getMeetingList(){}
	public Vertical load(int verticalId) throws SQLException,EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("vertical").where("id",verticalId).execute();
		ResultSet rs = sq.getResultSet();
		
		if(rs.next()){
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
			this.description = rs.getString("description");
			this.head = new Volunteer().load(rs.getInt("head"));
		}
		else
			throw(new EntityNotFoundException("Vertical#"+verticalId+" not found"));
		return this;
	}
	
	public void store() throws SQLException,IncompleteFieldException{
		Database db = Database.getDB();
		db.checkInput(name).checkInput(head.id);
		InsertQuery iq= db.insert("vertical").addParam("name",name).addParam("description",description).addParam("head",head.id).execute();
	}
}