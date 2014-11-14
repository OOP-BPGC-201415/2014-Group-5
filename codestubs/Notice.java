package nirmaanam;

class Notice{
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
	
	public void load(){}
	public void store(){}
}