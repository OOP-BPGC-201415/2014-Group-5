class Volunteer{
	String 	name,
			ID;
	Branch branch;
	Timetable timetable;
	Vertical vertical;
	
	//Situational variables?
	
	
	public void Volunteer(String name, String ID, Branch branch){}
	
	
	public void setVertical(Vertical V){}	//Ideally, We'd call Vertical.addVolunteer(...) instead
	
	public String getName(){}
	public String getID(){}
	public Branch getBranch(){}
	public Timetable getTimetable(){}
	public String getVertical(){}
	
	public void setName(String n){}
	public void setID(String id){}
	public void setBranch(Branch b){}
	public void setTimetable(Timetable tt){}
	
	public void store(){}
	public void load(int volunteerId){}
}