class Activity{
	String 	name,
			description,
			report;
	Volunteer head;
	
	public Activity(){}
	public Activity(String name, String descpription, Volunteer head){}
	
	public String getName(){}
	public String getDescription(){}
	public Volunteer getHead(){}
	public Event getEvent(){}
	
	public void setName(String n){}
	public void setDescription(String id){}
	public void setHead(Volunteer v){}
	public void setEvent(Event e){}
	
	public void setReport(String report){}
	
	
	public void confirmAvailability(Volunteer v){}
	
	public void load(int activityId){} //Loads from the database
	public void store(){} //Stores into the database
	
}