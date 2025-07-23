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
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import dao.DataBaseUtil;

@WebServlet("/Unlike")
public class Unlike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    StringBuilder sb = new StringBuilder();
	    
	    String line ;
	    
	    try(BufferedReader br = req.getReader()){
	    	while((line= br.readLine()) != null) {
	    		sb.append(line);
	    	}
	    }
	    
	    
	    JSONObject jb = new JSONObject(sb.toString());
	    
	    String id = jb.getString("postId");
	    String timeStamp = jb.getString("postTime");
	    ObjectId oid = new ObjectId(id);
	    HttpSession session = req.getSession(false);
	    UserModel model =(UserModel) session.getAttribute("user");
	    String unLikerId = model.getId();
	    
	    
	    boolean isUnLiked = 	DataBaseUtil.decrementLike(oid, timeStamp,unLikerId);
		List<PublicPosts>  publicPosts = DataBaseUtil.fetchAllPublicPosts();
		
		PublicPostModel publicPostModel= new PublicPostModel(publicPosts);
		
	   
	    
	    session.setAttribute("publicPostModel",publicPostModel );
		
		
		
        System.out.println("Servlet hit ✅");
        res.setContentType("application/json");
        if(isUnLiked) {
        
        res.getWriter().write("{\"status\":\"success\"}");
        }
        else {
        	 res.getWriter().write("{\"status\":\"failed\"}");
        }
		
	}

}
