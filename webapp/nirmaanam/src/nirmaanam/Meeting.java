package nirmaanam;
import java.sql.*;

public class Meeting{
	int id;
	int time;
	String 	location,
			purpose;
	Volunteer[] attendees;
	
	public Meeting()
	{
		this.id=0;
		this.time=0;
		this.location=null;
		this.purpose=null;
		this.attendees=null;
	}
	public Meeting(int time, String location, String purpose)
	{
		this.time=time;
		this.location=location;
		this.purpose=purpose;
	}
	
	
	public int getId(){return id;}
	public void setId(int id){this.id=id;}	
	
	public void setTime(int t){this.time=t;}
	public void setLocation(String loc){this.location=loc;}
	public void setPurpose(String p){this.purpose=p;}
	public void setAttendees(Volunteer[] v){this.attendees=v;}
	
	
	public int getTime(){return time;}
	public String getLocation(){return location;}
	public String getPurpose(){return purpose;}
	public Volunteer[] getAttendees(){return attendees;}
	
	public void addAttendee(Volunteer v)  throws SQLException{
		Database db = Database.getDB();
		InsertQuery sq = db.insert("Meeting_attendees").addParam("meeting",this.id).addParam("volunteer",v.id).execute();
	}
	
	public void addAttendee(Vertical vert) throws SQLException{
		String query = "INSERT INTO Meeting_attendees (meeting,volunteer) (SELECT " + this.id + ", id FROM volunteer WHERE vertical=?)";
		PreparedStatement iq = Database.getDB().getConn().prepareStatement(query);
		iq.setInt(1, vert.id);
		iq.executeUpdate();
	}
	
	public Meeting load(int meetingId) throws SQLException,EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("Meeting").where("id",meetingId).execute();
		ResultSet rs = sq.getResultSet();
		if(rs.next()){
			this.id = rs.getInt("id");
			this.location = rs.getString("location");
			this.purpose= rs.getString("purpose");
			this.time = rs.getInt("time");
		}
		else
			throw(new EntityNotFoundException("Meeting#"+meetingId+" not found"));
			
		return this;
	}
	
	public void store() throws SQLException,IncompleteFieldException{
		Database db = Database.getDB();
		db.checkInput(time).checkInput(purpose,location);	//, timestamp);
		InsertQuery iq= db.insert("Meeting").addParam("time",time).addParam("location",location).addParam("purpose",purpose).execute();
		this.id = iq.insertId();
	}
}