package nirmaanam;

class Vertical{
	int id;
	String 	name,
			description;
	Volunteer head;
	
	public Vertical(){}
	public Vertical(String name, String description, Volunteer head){}
	
	public int getId(){}
	public void setId(int id){}	


	public String getName(){
		
	}
	public String getDescription(){
		
	}
	
	public Volunteer getHead(){
	
	}
	
	public void setName(String n){
		
	}
	
	public void setDescription(String desc){
		
	}
	
	public void setHead(Volunteer v){
		
	}
	
	
	
	public void addEvent(Event e){}
	
	public void addVolunteer(Volunteer v){
		//Call Volunteer.setVertical
	}
	
	public Volunteer[] getVolunteerList(){
		
	}
	
	/*
	General information
	*/
	
	
	public Event[] getEventList(){}
	
	public Activity[] getActivityList(){}
	
	//public Meeting[] getMeetingList(){}
	public void load(int verticalId){}
	public void store(){}
}