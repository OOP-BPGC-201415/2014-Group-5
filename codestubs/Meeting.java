package nirmaanam;

class Meeting{
	int id;
	int time;
	String 	location,
			Purpose;
	Volunteer[] Attendees;
	
	public Meeting()
	{
		this.id=0;
		this.time=0;
		this.location=null;
		this.Purpose=null;
		this.Attendees=null;
	}
	public Meeting(int time, String location, String purpose)
	{
		this.time=time;
		this.location=location;
		this.Purpose=purpose;
	}
	
	
	public int getId(){return id;}
	public void setId(int id){this.id=id;}	
	
	public void setTime(int t){this.time=t;}
	public void setLocation(String loc){this.location=loc;}
	public void setPurpose(String p){this.Purpose=p;}
	public void setAttendees(Volunteer[] v){this.Attendees=v;}
	
	
	public int getTime(){return time;}
	public String getLocation(){return location;}
	public String getPurpose(){return Purpose;}
	public Volunteer[] getAttendees(){return Attendees;}
	
	public void addAttendee(Volunteer v)
	{
	}
	
	public void load(int meetingId){}
	public void store(){}
	
}