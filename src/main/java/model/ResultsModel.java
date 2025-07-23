package model;

import java.util.List;

public class ResultsModel {
	UserModel user ;
	List<PublicPosts> publicPosts;
	public ResultsModel(UserModel user, List<PublicPosts> publicPosts) {
		super();
		this.user = user;
		this.publicPosts = publicPosts;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public List<PublicPosts> getPublicPosts() {
		return publicPosts;
	}
	public void setPublicPosts(List<PublicPosts> publicPost) {
		this.publicPosts = publicPost;
	}
	

}
