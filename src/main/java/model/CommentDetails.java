package model;

public class CommentDetails {
	String comment;
	String timeStamp;
	String commenterName;
	public CommentDetails(String comment, String timeStamp, String commenterName) {
		
		this.comment = comment;
		this.timeStamp = timeStamp;
		this.commenterName = commenterName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
	
	
	
	
	
}
