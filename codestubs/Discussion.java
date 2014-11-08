class Discussion{
	int id;
	DiscussionPost[] posts;
	String title;
	Event event;
	
	public Discussion(){}
	
	public Discussion(Event event, String title){}
	
	
	
	public int getId(){}
	public void setId(int id){}
	
	
	
	public void setEvent(Event e){}
	public void setTitle(String t){}
	
	
	public DiscussionPost[] getPosts(){}
	public String getTitle(){}
	
	public Event getEvent(){}
	
	
	public void addPost(DiscussionPost dp){}
	
	public void load(int discussionId){} //Loads from the database. Includes all discussion posts
	public void store(){} //Creates a new discussion in the database
	
	class DiscussionPost{
		Volunteer poster;
		String 	post;
		int timeStamp;
		
		public DiscussionPost(){}
		public DiscussionPost(Volunteer poster, String post, int timeStamp){}
		
		public void setPost(String p){}
		public String setPoster(Volunteer v){}
		public void setTimeStamp(int ts){}
		
		public String getPost(){}
		public Volunteer getPoster(){}
		public int getTimeStamp(){}
		
	}
}