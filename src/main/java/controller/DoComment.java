package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CommentDetails;
import model.PublicPostModel;
import model.PublicPosts;
import model.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import dao.DataBaseUtil;


@WebServlet("/doComment")
public class DoComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		
		try(BufferedReader br = req.getReader()) {
			while((line= br.readLine()) != null) {
				sb.append(line);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		JSONObject jb = new JSONObject(sb.toString());
		
		String commentText = jb.getString("commentText");
		String postId = jb.getString("postId");
		String timeStamp= jb.getString("timeStamp");
		  LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        String commentedTime = now.format(formatter);
	        
		
		HttpSession session = req.getSession(false);
		
		UserModel model =(UserModel) session.getAttribute("user");
		String name = model.getName();
		String id = model.getId();
	   boolean isCommented = DataBaseUtil.doComment(new ObjectId(postId), timeStamp, new CommentDetails(commentText,commentedTime,name));
		
	List<PublicPosts>  publicPosts = DataBaseUtil.fetchAllPublicPosts();
		
		PublicPostModel publicPostModel= new PublicPostModel(publicPosts);
		
		model =  DataBaseUtil.getUpdatedposts(new ObjectId(id));
		
		
	   
	    
	    session.setAttribute("publicPostModel",publicPostModel );
	    session.setAttribute("user", model);
		
		
		
		
		
		
        System.out.println("Servlet hit ✅");
        res.setContentType("application/json");
        if(isCommented) {
        
        res.getWriter().write("{\"status\":\"success\"}");
        }
        else {
        	 res.getWriter().write("{\"status\":\"failed\"}");
        }
		
		
		System.out.print("Servlet hit at doComment");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
