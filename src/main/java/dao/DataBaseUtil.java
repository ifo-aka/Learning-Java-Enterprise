package dao;

import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.*;

import model.Post;
import model.UserModel;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.push;


public class DataBaseUtil {
 static	MongoClient client = MongoClients.create("mongodb://localhost:27017");
static	MongoDatabase database = client.getDatabase("BlogApp");
	
	private static List<Post> parsePostsFromDoc(Document doc){
		 List<Document> postDocs = doc.getList("posts", Document.class);
		    List<Post> postList = new ArrayList<>();
		    for (Document post : postDocs) {
		        postList.add(new Post(post.getString("title"), post.getString("description"), post.getString("feeling")));
		    }
		    return postList;
	}
	
	
	UserModel model = new UserModel();
	public static UserModel registerUser(String name,String email,String password,byte[] profileImg) {
		MongoCollection<Document> collection= database.getCollection("SignUp");
		Document user = new Document("username",name).append("email", email).append("password", password).append("profileImg", new org.bson.types.Binary(profileImg)).append("posts", new ArrayList<>());
		collection.insertOne(user);
				
		Document  userDoc = collection.find(and(eq("username",name),eq("email",email))).first();
		
		if(userDoc != null) {
			List<Post> postList = parsePostsFromDoc(userDoc);

			byte [] imgBytes = null;
			if(userDoc.containsKey("profileImg")) {
				imgBytes= userDoc.get("profileImg", org.bson.types.Binary.class).getData();
			}
			
			return new UserModel(name,email,postList,imgBytes);
		}
		
		
		return null;
		
		
	}
	
	
	 
	public static  UserModel loginUser(String name, String password) {
	    MongoCollection<Document> collection = database.getCollection("SignUp");
	    String hashPass = PasswordUtil.hashPassword(password);

	    Document userDoc = collection.find(and(eq("username", name), eq("password", hashPass))).first();

	    if (userDoc != null) {
	    	List<Post> postList = parsePostsFromDoc(userDoc);

	    	byte [] imgBytes = null;
			if(userDoc.containsKey("profileImg")) {
				imgBytes= userDoc.get("profileImg", org.bson.types.Binary.class).getData();
			}
	        String email = userDoc.getString("email");
	        return new UserModel(name,email,postList,imgBytes);		 // returning user object
	    }		

	    return null;
	}														

	public static UserModel addPost(String name, String email, Post post) {
	    MongoCollection<Document> collection = database.getCollection("SignUp");

	    Document newPost = new Document("title", post.getTitle())
	            .append("description", post.getDescription())
	            .append("feeling", post.getFeeling());

	    collection.updateOne(and(eq("username", name), eq("email", email)), push("posts", newPost));

	    // Re-fetch the updated document from DB
	    Document updatedUser = collection.find(and(eq("username", name), eq("email", email))).first();

	    List<Post> postList = parsePostsFromDoc(updatedUser);

		byte [] imgBytes = null;
		if(updatedUser.containsKey("profileImg")) {	
			imgBytes= updatedUser.get("profileImg", org.bson.types.Binary.class).getData();
		}

	    String emailFromDb = updatedUser.getString("email");
	    return new UserModel(name, emailFromDb, postList,imgBytes);
	}


}
