package dao;

import org.bson.Document;

import org.bson.types.ObjectId;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import java.util.*;

import model.CommentDetails;
import model.LikeDetail;

import model.Post;
import model.PublicPosts;
import model.ResultsModel;
import model.UserModel;
import static com.mongodb.client.model.Updates.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.push;


public class DataBaseUtil {
 static	MongoClient client = MongoClients.create("mongodb://localhost:27017");
static	MongoDatabase database = client.getDatabase("BlogApp");
	
	private static List<Post> parsePostsFromDoc(Document doc){
		List<Document> posts = doc.getList("posts", Document.class);
		List<Post> postList = new ArrayList<>();

		for (Document post : posts) {
		    List<String> likeIds = new ArrayList<>();
		    List<CommentDetails> commentList = new ArrayList<>();
		    
		    if (post.containsKey("likes")) {
		        List<Document> likeDocs = post.getList("likes", Document.class);
		        if (likeDocs != null) {
		            for (Document likeDoc : likeDocs) {
		                if (likeDoc.containsKey("id")) {
		                    likeIds.add(likeDoc.getString("id"));
		                }
		            }
		        }
		    }
		    if(post.containsKey("comments")){
		    	List<Document> commentDoc = post.getList("comments",Document.class);
		    if(commentDoc != null) {
		    	for(Document comment : commentDoc) {
		    		commentList.add(new CommentDetails(comment.getString("comment"),comment.getString("commentTime"),comment.getString("commenterName")));
		    	}
		    }
		    	
		    }

		    postList.add(new Post(
		        post.getString("title"),
		        post.getString("description"),
		        post.getString("feeling"),
		        post.getString("visibility"),
		        post.getString("timeStamp"),
		        new LikeDetail(likeIds),
		        commentList
		        
		    ));
		}
		    return postList;
	}
	
	
	public static Boolean incrementLike(ObjectId id, String timeStamp,String likerId) {

		MongoCollection<Document> collection = database.getCollection("SignUp");
        Document likeDoc = new Document("id",likerId);	 
        try {
        collection.updateOne(
        		and(
        			eq("_id",id),
        			eq("posts.timeStamp",timeStamp)
        			),
        push("posts.$.likes",likeDoc)
        );
        return true;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return false;	

	
		
	}
	public static boolean decrementLike(ObjectId id ,String timeStamp,String unLikerId) {
		MongoCollection<Document> collection = database.getCollection("SignUp");
		Document unLikeDoc= new Document("id",unLikerId);
		try {
			collection.updateOne(
					and(
							eq("_id",id),
							eq("posts.timeStamp",timeStamp)
							),
					pull("posts.$.likes",unLikeDoc)
					
					);
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean doComment(ObjectId postId,String postTimeStamp, CommentDetails details) {
		MongoCollection<Document> collection = database.getCollection("SignUp");
		
		Document comment = new Document("comment",details.getComment()).append("commentTime", details.getTimeStamp()).append("commenterName", details.getCommenterName());
		try {
		  collection.updateOne(
				  and(
						  eq("_id",postId),
						  eq("posts.timeStamp",postTimeStamp)
						  
						  ),
				  push("posts.$.comments",comment)
				  );
		  return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		}
		
		
		return false;
	}
	
	
	public  static boolean updatePost(ObjectId id , String postStamp, String title,String description,String feeling ,String visibility, String updateTime ) {
		MongoCollection<Document> collection = database.getCollection("SignUp");
		
		Document updateFields= new Document("posts.$.title",title).append("posts.$.description",description).append("posts.$.feeling", feeling).append("posts.$.visibility", visibility).append("posts.$.timeStamp", updateTime);
		Document updateOperation = new Document("$set",updateFields);
		try{
		collection.updateOne(
				and(
						eq("_id",id),
						eq("posts.timeStamp",postStamp)
						),
				
				updateOperation
				);
		return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

	
	public static List<PublicPosts> fetchAllPublicPosts(){
		MongoCollection<Document> collection= database.getCollection("SignUp");
		
		List<PublicPosts> publicPosts = new ArrayList<>();
		
		for(Document user: collection.find()) {
			String author = user.getString("username");
			String id = user.getObjectId("_id").toHexString();
			List<Document> posts= user.getList("posts", Document.class);
			if(posts != null) {
				
				for(Document post: posts) {
					if(post.getString("visibility").equalsIgnoreCase("public")) {
						String title = post.getString("title");
						String description = post.getString("description");
						String feeling = post.getString("feeling");
						String timeStamp= post.getString("timeStamp");
						List<String> likesList= new ArrayList<>();
						 List<CommentDetails> commentList = new ArrayList<>();
						    
						
						if(post.containsKey("likes")) {
							List<Document> likes = post.getList("likes", Document.class);
							if(likes != null) {
						        for (Document likesDoc : likes) {
					                if (likesDoc.containsKey("id")) {
					                    likesList.add(likesDoc.getString("id"));
					                }
					            }
							}
					      
						}
						
						 if(post.containsKey("comments")){
						    	List<Document> commentDoc = post.getList("comments",Document.class);
						    if(commentDoc != null) {
						    	for(Document comment : commentDoc) {
						    		commentList.add(new CommentDetails(comment.getString("comment"),comment.getString("commentTime"),comment.getString("commenterName")));
						    	}
						    }
						    	
						    }
						publicPosts.add(new PublicPosts(id,author,title,description,feeling,new LikeDetail(likesList),commentList,timeStamp));
					}
				}
			}
		}
		return publicPosts;
		
	}
	
	
	
	public static ResultsModel registerUser(String name,String email,String password,byte[] profileImg) {
		MongoCollection<Document> collection= database.getCollection("SignUp");
		Document user = new Document("username",name).append("email", email).append("password", password).append("profileImg", new org.bson.types.Binary(profileImg)).append("posts", new ArrayList<>());
		collection.insertOne(user);
				
		Document  userDoc = collection.find(and(eq("username",name),eq("email",email))).first();
		
		if(userDoc != null) {
			List<Post> postList = parsePostsFromDoc(userDoc);
			List<PublicPosts> allPulicPosts= fetchAllPublicPosts();
           String id = userDoc.getObjectId("_id").toHexString();
			byte [] imgBytes = null;
			if(userDoc.containsKey("profileImg")) {
				imgBytes= userDoc.get("profileImg", org.bson.types.Binary.class).getData();
			}	
			
				
			return new ResultsModel(new UserModel(name,email,id,postList,imgBytes),allPulicPosts);
		}
		
		
		return null;
		
		
	}
	public static boolean deletePost(ObjectId id, String timeStamp) {
	    MongoCollection<Document> collection = database.getCollection("SignUp");

	    try {
	        collection.updateOne(
	            eq("_id", id),
	            Updates.pull("posts", new Document("timeStamp", timeStamp))
	        );
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	// updated posts
	
	public static UserModel getUpdatedposts(ObjectId userId) {
	    MongoCollection<Document> collection = database.getCollection("SignUp");
	    
		   Document updatedUser = collection.find(eq("_id", userId)).first();

		    List<Post> postList = parsePostsFromDoc(updatedUser);
		    
		    String id = updatedUser.getObjectId("_id").toHexString();
           String name = updatedUser.getString("username");
			byte [] imgBytes = null;
			if(updatedUser.containsKey("profileImg")) {	
				imgBytes= updatedUser.get("profileImg", org.bson.types.Binary.class).getData();
			}

		    String emailFromDb = updatedUser.getString("email");
		    return new UserModel(name,emailFromDb,id,postList,imgBytes);	
	}
	 
	public static  ResultsModel loginUser(String name, String password) {
	    MongoCollection<Document> collection = database.getCollection("SignUp");
	    String hashPass = PasswordUtil.hashPassword(password);

	    Document userDoc = collection.find(and(eq("username", name), eq("password", hashPass))).first();

	    if (userDoc != null) {
	    	List<Post> postList = parsePostsFromDoc(userDoc);
	    	List<PublicPosts> allPulicPosts= fetchAllPublicPosts();
	    	  String id = userDoc.getObjectId("_id").toHexString();
	    	byte [] imgBytes = null;
			if(userDoc.containsKey("profileImg")) {
				imgBytes= userDoc.get("profileImg", org.bson.types.Binary.class).getData();
			}
	        String email = userDoc.getString("email");
	        return new ResultsModel(new UserModel(name,email,id,postList,imgBytes),allPulicPosts);	 // returning user object
	    }		

	    return null;
	}														

	public static ResultsModel addPost(String name, String email, Post post) {
	    MongoCollection<Document> collection = database.getCollection("SignUp");

	    Document newPost = new Document("title", post.getTitle())
	            .append("description", post.getDescription())
	            .append("feeling", post.getFeeling()).append("visibility", post.getVisibility()).append("timeStamp", post.getDateAndTimeOfPost()).append("likes", new ArrayList<Document>()).append("comments", new ArrayList<CommentDetails>());

	    collection.updateOne(and(eq("username", name), eq("email", email)), push("posts", newPost));

	    
	    Document updatedUser = collection.find(and(eq("username", name), eq("email", email))).first();

	    List<Post> postList = parsePostsFromDoc(updatedUser);
	    List<PublicPosts> allPulicPosts= fetchAllPublicPosts();
	    String id = updatedUser.getObjectId("_id").toHexString();

		byte [] imgBytes = null;
		if(updatedUser.containsKey("profileImg")) {	
			imgBytes= updatedUser.get("profileImg", org.bson.types.Binary.class).getData();
		}

	    String emailFromDb = updatedUser.getString("email");
	    return new ResultsModel(new UserModel(name,emailFromDb,id,postList,imgBytes),allPulicPosts);	
	}


	


}
