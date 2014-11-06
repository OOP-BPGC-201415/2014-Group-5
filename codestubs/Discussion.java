class Discussion{

	DiscussionPost[] posts;
	String title;
	Event event;
	
	public Discussion(){}
	
	public Discussion(Event event, String title){}
	
	public void setEvent(Event e){}
	public void setTitle(String t){}
	public void addPost(DiscussionPost){}
	
	public DiscussionPost[] getPosts(){}
	public String getTitle(){}
	public Event getEvent(){}
	
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