package nirmaanam;
import java.sql.*;
import java.util.ArrayList;

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
	public void addEvent(Event e) throws SQLException{
		e.setVertical(this);
		Database db= Database.getDB();
		UpdateQuery uq = db.update("Event").addParam("vertical",this.id).where("id",e.id).execute();
	}
	
	public void addVolunteer(Volunteer v) throws SQLException{
		v.setVertical(this);
		Database db= Database.getDB();
		UpdateQuery uq = db.update("volunteer").addParam("vertical",this.id).where("id",v.id).execute();
	}
	
	public ArrayList<Volunteer> getVolunteerList() throws SQLException{
		ArrayList<Volunteer> vList = new ArrayList<Volunteer>();
		Database db = Database.getDB();
		SelectQuery sq = db.select("volunteer").where("vertical",this.id).execute();
		
		ResultSet rs = sq.getResultSet();
		while(rs.next()){
			Volunteer v= new Volunteer(rs.getString("name"), rs.getString("bitsID"), rs.getInt("year"));
			v.setId(rs.getInt("id"));
			vList.add(v);
		}
		return vList;
	}
	
	/*
	General information
	*/
	public ArrayList<Event> getEventList() throws SQLException,EntityNotFoundException{
		ArrayList<Event> eList = new ArrayList<Event>();
		Database db = Database.getDB();
		SelectQuery sq = db.select("event").where("vertical",this.id).execute();
		
		ResultSet rs = sq.getResultSet();
		while(rs.next()){
			Volunteer eh = new Volunteer().load(rs.getInt("head"));
			Event e= new Event(rs.getString("name"), rs.getString("description"), eh);	
			e.setId(rs.getInt("id"));
			eList.add(e);
		}
		return eList;
	}
	
	public ArrayList<Activity> getActivityList() throws SQLException,EntityNotFoundException{
		ArrayList<Activity> aList = new ArrayList<Activity>();
		Database db = Database.getDB();
		SelectQuery sq = db.select("event").where("vertical",this.id).execute();
		
		ResultSet rs = sq.getResultSet();
		while(rs.next()){
			Volunteer ah = new Volunteer().load(rs.getInt("head"));
			Activity a= new Activity(rs.getString("name"), rs.getString("description"), ah);	
			a.setId(rs.getInt("id"));
			a.setEvent( new Event().load(rs.getInt("event")));
			aList.add(a);
		}
		return aList;
	}
	
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
		this.id = iq.insertId();
	}
}