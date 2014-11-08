class Volunteer{
	int id;
	String 	name,
			bitsID;
	int year;
	Timetable timetable;
	Vertical vertical;
	
	//Situational variables?
	boolean isAvailable;
	
	
	public void Volunteer(String name, String ID, int year){}
	
	
	public int getId(){}
	public void setId(int id){}
	
	public int setAvailability(){}
	public void getAvailability(){}
	
	public void setVertical(Vertical V){}	//Ideally, We'd call Vertical.addVolunteer(...) instead
	
	public String getName(){}
	public String getBitsID(){}
	public Year getYear(){}
	public Timetable getTimetable(){}
	public String getVertical(){}
	
	public void setName(String n){}
	public void setBitsID(String id){}
	public void setYear(Year y){}
	public void setTimetable(Timetable tt){}
	
	public void store(){}
	public void load(int volunteerId){}
}