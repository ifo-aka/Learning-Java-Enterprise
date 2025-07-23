package controller;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import dao.DataBaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;	
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CommentDetails;
import model.LikeDetail;

import model.Post;
import model.PublicPostModel;
import model.PublicPosts;
import model.ResultsModel;
import model.UserModel;

@WebServlet("/makePost")

public class makePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String title = req.getParameter("title");
		String description = req.getParameter("description"); // FIXED SPELLING
		String feeling = req.getParameter("feeling");
		String visibility = req.getParameter("visibility");
		  LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        String formatted = now.format(formatter);
	        List<String> list = null;
	        List<CommentDetails> commentList= null;
	        
 	       LikeDetail likeDetail= new LikeDetail(list);	
	        
	     

		Post singlePost = new Post(title, description, feeling,visibility,formatted,likeDetail,commentList);
	
		
	
	
		      
		     
	   

		 
		 HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");  // FIXED

		if (user != null) {
			ResultsModel  result =DataBaseUtil.addPost(user.getName(),user.getEmail(), singlePost); 
			List<PublicPosts> publicPosts = result.getPublicPosts();
			PublicPostModel publicPostModel= new PublicPostModel(publicPosts);
			user= result.getUser();			  
			    session.setAttribute("user", user);
			    session.setAttribute("publicPostModel",publicPostModel );
			res.sendRedirect(req.getContextPath()+"/View/PublicView.jsp");
		} else {
			res.sendRedirect(req.getContextPath()+"/Controller/Login.jsp");
		}
	}
}
