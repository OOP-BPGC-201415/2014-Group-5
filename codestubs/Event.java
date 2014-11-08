class Event{
	String 	name,
			description;
	Volunteer head;
	
	public Event(){}
	public Event(String name, String description, Volunteer head){}
	
	public String getName(){}
	public String getDescription(){}
	public Volunteer getHead(){}
	public Volunteer getVertical(){}
	
	public void setName(String n){}
	public void setDescription(String id){}
	public void setHead(Volunteer v){}
	public void setVertical(Vertical vert){}
	
	public void addActivity(){}
	//public void addMeeting(){}
	public void addNotice(){}
	public void addUpdate(){}
	
	public Activity[] getActivityList(){}
	public Notice[] getNoticeList(){}
	public EventUpdate[] getUpdates(){}
	
	public void load(int eventId){}
	public void store(){}
}