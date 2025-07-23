package model;

import java.util.List;

public class Post {
	String title ;
	String description ;
	String feeling;
	String visibility;
	String DateAndTimeOfPost;
	LikeDetail likes;
	List<CommentDetails> comments;
	public Post(String title, String description, String feeling,String visibility,String DateAndTimeOfPost,LikeDetail likes,List<CommentDetails> comments) {
	
		this.title = title;
		this.description = description;
		this.feeling = feeling;
		this.visibility=visibility;	
		this.DateAndTimeOfPost=DateAndTimeOfPost;
		this.likes=likes;
		this.comments=comments;
	}
	public LikeDetail getLikes() {
		return likes;
	}
	public void setLikes(LikeDetail likes) {
		this.likes = likes;
	}
	public List<CommentDetails> getComments() {
		return comments;
	}
	public void setComments(List<CommentDetails> comments) {
		this.comments = comments;
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
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getDateAndTimeOfPost() {
		return DateAndTimeOfPost;
	}
	public void setDateAndTimeOfPost(String dateAndTimeOfPost) {
		DateAndTimeOfPost = dateAndTimeOfPost;
	}
	
	

}
