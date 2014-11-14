package nirmaanam;

class Discussion{
	int id;
	DiscussionPost[] posts;
	String title;
	Event event;
	
	public Discussion()
	{
		this.id=0;
		this.posts=null;
		this.title=null;
		this.event=null;
	}
	
	public Discussion(Event event, String title)
	{
		this.event=event;
		this.title=title;
	}
	
	
	
	public int getId()
	{ 
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	
	
	public void setEvent(Event e)
	{
		this.event=e;
	}
	public void setTitle(String t)
	{
		this.title=t;
	}
	
	
	public DiscussionPost[] getPosts()
	{
		return posts;
	}
	public String getTitle()
	{
		return title;
	}
	
	public Event getEvent()
	{
		return event;
	}
	
	
	public void addPost(DiscussionPost dp)
	{
		
	}
	
	public void load(int discussionId){} //Loads from the database. Includes all discussion posts
	public void store(){} //Creates a new discussion in the database
	
	class DiscussionPost{
		Volunteer poster;
		String 	post;
		int timeStamp;
		
		public DiscussionPost()
		{
			this.post=null;
			this.poster=null;
			this.timeStamp=0;
		}
		public DiscussionPost(Volunteer poster, String post, int timeStamp)
		{
			this.poster=poster;
			this.post=post;
			this.timeStamp=timeStamp;
		}
		
		public void setPost(String p)
		{
			this.post=p;
		}
		 public void setPoster(Volunteer v)
		 {
			 this.poster=v;
		}
		public void setTimeStamp(int ts)
		{
			this.timeStamp=ts;
		}
		
		public String getPost()
		{
			return post;
			}
		public Volunteer getPoster()
		{return poster;}
		public int getTimeStamp()
		{
			return timeStamp;
		}
		
	}
}