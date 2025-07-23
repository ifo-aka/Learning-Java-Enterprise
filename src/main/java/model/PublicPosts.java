package model;

import java.util.*;


public class PublicPosts {
	private String id;
	private String author ;
	private String title;
	private String description;
	private String feeling;
	private LikeDetail likes;
    private	List<CommentDetails> comments;
	private String dateAndTime;
	public PublicPosts() {
		
	}

	public PublicPosts(String id,String author, String title, String description  ,String feeling, LikeDetail likes, List<CommentDetails> comments,String dateAndTime) {
	    this.setId(id);	
		this.author = author;
		this.title = title;
		this.description = description;
		this.setFeeling(feeling);
		this.likes = likes;
		this.comments = comments;
		this.dateAndTime = dateAndTime;
		
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public void setDescription(String discription) {
		this.description = discription;
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
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFeeling() {
		return feeling;
	}
	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
	

}
