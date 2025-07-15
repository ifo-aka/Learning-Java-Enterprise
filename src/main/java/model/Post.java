package model;

public class Post {
	String title ;
	String description ;
	String feeling;
	public Post(String title, String description, String feeling) {
	
		this.title = title;
		this.description = description;
		this.feeling = feeling;
	}
	public String getTitle() {		
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFeeling() {
		return feeling;
	}
	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
	
	

}
