package model;

import java.util.List;


public class UserModel {							
    private String name;
    private String email;
    private String password;
    private Post siglePost;
    private List<Post> posts;  
    private byte[] profileImg;
    

    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    
    public UserModel(String name,String email ,List<Post> posts,byte[] profileImg) {
    	this.name=name;
    	this.email=email;
    	this.posts = posts;
    	this.profileImg=profileImg;
    }
    
    public UserModel(String name,String email ,List<Post> posts) {
    	this.name=name;
    	this.email=email;
    	this.posts = posts;
    	
    }
    
public byte[] getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(byte[] profileImg) {
		this.profileImg = profileImg;
	}

public UserModel() {
	
}


    // Optional: constructor with posts




	// Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    

    public List<Post> getPosts() { return posts; }
    public void setPosts(List<Post> posts) { this.posts = posts; }

	public Post getSiglePost() {
		return siglePost;
	}

	public void setSiglePost(Post siglePost) {
		this.siglePost = siglePost;
		posts.add(siglePost);
	}
}
