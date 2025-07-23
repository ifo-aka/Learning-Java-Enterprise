package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.PublicPostModel;
import model.PublicPosts;
import model.UserModel;

import java.io.BufferedReader;
import java.io.IOException;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import java.util.*;

import dao.DataBaseUtil;


@WebServlet("/Like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print(request);
		
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		
		try(BufferedReader br = req.getReader()){
			while((line= br.readLine()) != null) {
				sb.append(line);
			}
		}
		JSONObject jb = new JSONObject(sb.toString());
		
		String id = jb.getString("postId");
		String timeStamp= jb.getString("postTime");
		
		ObjectId Oid = new ObjectId(id);
		HttpSession session = req.getSession(false);
		
		UserModel model=(UserModel) session.getAttribute("user");
		String likerId = model.getId();
	
	boolean isliked = 	DataBaseUtil.incrementLike(Oid, timeStamp,likerId);
		List<PublicPosts>  publicPosts = DataBaseUtil.fetchAllPublicPosts();
		
		PublicPostModel publicPostModel= new PublicPostModel(publicPosts);
		
	   
	    
	    session.setAttribute("publicPostModel",publicPostModel );
		
		
		
		
		
		
        System.out.println("Servlet hit ✅");
        res.setContentType("application/json");
        if(isliked) {
        
        res.getWriter().write("{\"status\":\"success\"}");
        }
        else {
        	 res.getWriter().write("{\"status\":\"failed\"}");
        }
		System.out.print(id+timeStamp);
	}

}
