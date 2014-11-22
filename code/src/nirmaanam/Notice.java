package nirmaanam;
import java.sql.*;

public class Notice{
	int id;
	String message;
	Event event;
	
	public Notice()
	{
		this.id=0;
		this.message=null;
		this.event=null;
	}
	public Notice(Event event, String message)
	{
		this.event=event;
		this.message=message;
	}
	public int getId()
	{
		return id;
		}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public void setMessage(String m)
	{
		this.message=m;
	}
	public void setEvent(Event e)
	{
		this.event=e;
	}
	
	public String getMessage()
	{
		return message;
		}
	public Event getEvent()
	{
		return event;
	}
	
	public Notice load(int noticeId) throws SQLException,EntityNotFoundException{
		Database db = Database.getDB();
		SelectQuery sq = db.select("Notice").where("id",noticeId).execute();
		ResultSet rs = sq.getResultSet();
		if(rs.next()){
			this.id = rs.getInt("id");
			this.message = rs.getString("message");
			this.event = new Event().load(rs.getInt("event"));
			
		}
		else
			throw(new EntityNotFoundException("Notice#"+noticeId+" not found"));
			
		return this;
	}
	
	public void store() throws SQLException,IncompleteFieldException{
		Database db = Database.getDB();
		db.checkInput(message).checkInput(event.id);	//, timestamp);
		InsertQuery iq= db.insert("EventUpdate").addParam("message",message).addParam("event",event.id).execute();
		this.id = iq.insertId();
	}
}