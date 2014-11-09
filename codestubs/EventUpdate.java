package nirmaanam;

class EventUpdate{
	int id;
	String message;
	Event event;
	int timeStamp;
	
	
	public int getId(){}
	public void setId(int id){}
	
	
	public EventUpdate(){}
	public EventUpdate(Event event, String message){}	//timeStamp defaults to currentTime
	public EventUpdate(Event event, String message, int timeStamp){}
	public void setMessage(String m){}
	public void setEvent(Event e){}
	public void setTimeStamp(int ts){}
	
	public String getMessage(){}
	public Event getEvent(){}
	public int getTimeStamp(){}
}