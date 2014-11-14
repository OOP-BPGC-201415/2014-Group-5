package nirmaanam;
import java.sql.*;

class EventUpdate{
	int id;
	String message;
	Event event;
	int timeStamp;
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public EventUpdate()
	{
		this.id=0;
		this.message=null;
		this.event=null;
		this.timeStamp=0;
	}
	public EventUpdate(Event event, String message)
	{
		this.event=event;
		this.message=message;
	}	//timeStamp defaults to currentTime
	public EventUpdate(Event event, String message, int timeStamp)
	{
		this.event=event;
		this.message=message;
		this.timeStamp=timeStamp;
	}
	public void setMessage(String m)
	{
		this.message=m;
	}
	public void setEvent(Event e)
	{
		this.event=e;
	}
	public void setTimeStamp(int ts)
	{
		this.timeStamp=ts;
	}
	
	public String getMessage()
	{
		return message;
	}
	public Event getEvent()
	{
		return event;
	}
	public int getTimeStamp()
	{
		return timeStamp;
	}

	public EventUpdate load(int EventUpdateId) throws SQLException,EntityNotFoundException{
		/*Database db = Database.getDB();
		selectQuery sq = db.select("Activity").where("id",EventUpdateId).execute();
		ResultSet rs = sq.getResultSet();
		if(rs.next()){
			this.id = rs.getInt("id");
			this.name = rs.getString("name");
			this.description = rs.getString("description");
			this.head = new Volunteer().load(rs.getInt(head));
		}
		else
			throw(new EntityNotFoundException("EventUpdate#"+EventUpdateId+" not found"));
			*/
		return this;
	} //Loads from the database
	
	public void store() throws SQLException,IncompleteFieldException{
		/*
		Database db = Database.getDB();
		db.checkInput(message).checkInput(Event).checkInput(timeStamp);
		InsertQuery iq= db.insert("Activity").addParam("name",name).addParam("description",description).addParam("head",head.id).execute();
		*/
	} //Stores into the database

}//end of class