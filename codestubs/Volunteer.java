package nirmaanam;

class Volunteer{
	int id;
	String 	name,
			bitsID;
	int year;
	TimeTable timetable;
	Vertical vertical;
	
	//Situational variables?
	boolean isAvailable;
	
	public Volunteer(){}
	public Volunteer(String name, String ID, int year){}
	
	
	public int getId(){}
	public void setId(int id){}
	
	public int setAvailability(){}
	public void getAvailability(){}
	
	public void setVertical(Vertical V){}	//Ideally, We'd call Vertical.addVolunteer(...) instead
	
	public String getName(){}
	public String getBitsID(){}
	public int getYear(){}
	public TimeTable getTimetable(){}
	public String getVertical(){}
	
	public void setName(String n){}
	public void setBitsID(String id){}
	public void setYear(int y){}
	public void setTimetable(TimeTable tt){}
	
	public void store(){}
	public void load(int volunteerId){}
}