class TimeTable{
	TimeSlot[] busyslots;
	//Volunteer v;	//The volunteer this belongs to ?
	
	public void addSlot(TimeSlot ts){}
	public void removeSlot(TimeSlot ts){}
	public boolean checkForClash(Day day, int firstSlot,int lastSlot){}
	
	void load(Volunteer v){}	//Loads the volunteer's timetable
	public void store(){}	//Updates and stores the new timetable
	
	
	enum Day{
		SUN,MON,TUE,WED,THU,FRI,SAT
	}
	class TimeSlot{
		public Day day;
		public int startTime;	//Valid values are between 00 and 23, Timeslots last 1 hour each.
		
		public TimeSlot(){}
		public TimeSlot(Day day, int startTime){}
	}
	
}