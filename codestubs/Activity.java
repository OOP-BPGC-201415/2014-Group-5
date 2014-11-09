package nirmaanam;

Enum Availability{
	PENDING, AVAILABLE, UNAVAILABLE
}

class Activity{
	
	int id;
	String 	name,
			description,
			report;
	Volunteer head;
	
	//Situational
	Volunteer[] assignees;
	//Constants
	
	public Activity(){}
	public Activity(String name, String description, Volunteer head){}
	
	public int getId(){}
	public void setId(int id){}
	
	public String getName(){}
	public String getDescription(){}
	public Volunteer getHead(){}
	public Event getEvent(){}
	
	
	public void setName(String n){}
	public void setDescription(String id){}
	public void setHead(Volunteer v){}
	public void setEvent(Event e){}
	
	public void setReport(String report){}
	
	public void addAssignee(Volunteer v){}
	
	public Volunteer[] getAssignees(){
		//Does a database query
		//Gets a result set
		//Returns an array of Volunteers
	}
	public boolean isAssignee(Volunteer v){
		//Does a query
		//Gets a result set
		//Returns true or false accordingly
	}
	
	public Availability getAvailability(Volunteer v){
		//Does a database query
		//Gets a result set
		//Returns true, false or not set
	}
	public void confirmAvailability(Volunteer v,Availability av){}
	
	
	
	public void load(int activityId){} //Loads from the database
	public void store(){} //Stores into the database
	
}