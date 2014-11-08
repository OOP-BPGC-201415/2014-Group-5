class Meeting{
	int id;
	int time;
	String 	location,
			Purpose;
	Volunteer[] Attendees;
	
	public Meeting(){}
	public Meeting(int time, String location, String purpose){}
	
	
	public int getId(){}
	public void setId(int id){}	
	
	public void setTime(int t){}
	public void setLocation(String loc){}
	public void setPurpose(String p){}
	public void setAttendees(Volunteer[] v){}
	
	
	public int getTime(){}
	public String getLocation(){}
	public String getPurpose(){}
	public Vounteer[] getAttendees(){}
	
	public void addAttendee(Volunteer v){}
	
	public void load(int meetingId){}
	public void store(){}
	
}