package nirmaanam;
//import nirmaanam.*;
import java.sql.*;
import java.util.ArrayList;

public class Event implements NirmaanEntity{
	int id;
	String 	name,
			description;
	Vertical vertical;
	Volunteer head;
	
	public Event(){
		this.id=0;
		this.name=null;
		this.description=null;
		this.head=null;
		this.vertical = null;
	}
	
	public Event(String name, String description, Volunteer head){
		this.name=name;
		this.description=description;
		this.head=head;
	}
	
	public int getId(){
		return id;//get method returns bruh
	}
	public void setId(int id){
		this.id=id;
	}
	public void setName(String n){
		this.name=n;
	}
	public String getName(){
		return name;
	}
	public void setDescription(String d){
		this.description=d;
	}
	
	public String getDescription(){
		return this.description;
	}
	public void setHead(Volunteer v){
		this.head=v;
	}
	public Volunteer getHead(){
		return head;
	}
	public void setVertical(Vertical vert){
		this.vertical=vert;
	}
	public Vertical getVertical(){
		return this.vertical;
	}
	
	public void addActivity(Activity A) throws SQLException, IncompleteFieldException{
		A.setEvent(this);
		A.store();
	}
	/*public void addMeeting(Meeting M){
		M.setEvent(this);
	}*/
	public void addNotice(Notice N){
		//N.setEvent(this);
		//N.store();
	}
	public void addUpdate(EventUpdate EU){
		//EU.setEvent(this);
		//EU.store();
	}
	
	//TODO!
	public ArrayList<Activity> getActivityList() throws SQLException, EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("Activity").where("event",this.id).execute();
		ArrayList<Activity> aList = new ArrayList<Activity>();
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
	
	public ArrayList<Notice> getNoticeList() throws SQLException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("Notice").where("event",this.id).execute();
		
		ResultSet rs = sq.getResultSet();
		ArrayList<Notice> nList = new ArrayList<Notice>();
		while(rs.next()){
			Notice n = new Notice(this, rs.getString("message"));	
			nList.add(n);
		}
		return nList;
	}
	public ArrayList<EventUpdate> getUpdates() throws SQLException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("EventUpdate").where("event",this.id).execute();
		
		ResultSet rs = sq.getResultSet();
		ArrayList<EventUpdate> uList = new ArrayList<EventUpdate>();
		while(rs.next()){
			uList.add(new EventUpdate(this, rs.getString("message")));
		}
		return uList;
	}
	
	public Event load(int eventId) throws SQLException,EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("event").where("id",eventId).execute();
		ResultSet rs = sq.getResultSet();
		if(rs.next()){
			id = rs.getInt("id");
			name = rs.getString("name");
			description = rs.getString("description");
			
			vertical = new Vertical();
			vertical.load(rs.getInt("vertical"));
			head = new Volunteer();
			head.load(rs.getInt("head"));
		}
		else
			throw(new EntityNotFoundException("Event#"+eventId+" not found"));
		
		return this;
	}
	
	public void store() throws SQLException, IncompleteFieldException{
		Database db = Database.getDB();
		db.checkInput(name).checkInput(head.id,vertical.id);
		InsertQuery iq= db.insert("Event").addParam("name",name).addParam("description",description).addParam("head",head.id).addParam("vertical",vertical.id).execute();
		this.id = iq.insertId();
	}
	
}