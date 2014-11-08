class Notice{
	int id;
	String message;
	Event event;
	
	public Notice();
	public Notice(Event event, String message);
	
	public int getId(){}
	public void setId(int id){}
	
	public void setMessage(String m){}
	public void setEvent(Event e){}
	
	public String getMessage(){}
	public Event getEvent(){}
	
	public void load(){}
}