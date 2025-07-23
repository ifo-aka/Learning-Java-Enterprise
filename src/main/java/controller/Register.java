package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.PublicPostModel;
import model.PublicPosts;
import model.ResultsModel;
import model.UserModel;

import java.io.IOException;
import java.util.List;

import dao.DataBaseUtil;
import dao.PasswordUtil;


@WebServlet("/RegisterUser")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		  String userName = req.getParameter("name");
		  String userEmail= req.getParameter("email");
		  String userPassword = req.getParameter("password");
		  String hashPassword = PasswordUtil.hashPassword(userPassword);
		  Part filePart = req.getPart("profilePic");
		  byte[] imgBytes = filePart.getInputStream().readAllBytes();
		  
		ResultsModel result = null;
		try {
			result = DataBaseUtil.registerUser(userName,userEmail,hashPassword,imgBytes);
			
		}catch(Exception e) {
			System.out.print(e);
		}
		if(result != null) {
			UserModel user = result.getUser();
			List<PublicPosts> publicPosts = result.getPublicPosts();
			
			PublicPostModel publicPostModel= new PublicPostModel(publicPosts);
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			 session.setAttribute("publicPostModel",publicPostModel );
			session.setMaxInactiveInterval(10*60);
			res.sendRedirect(req.getContextPath()+"/View/PublicView.jsp");
		}
		else {
			res.sendRedirect(req.getContextPath()+"/Controller/Login.jsp");
		}
		   
		   
		   
		   
		   
		  
		  
		  
		  
		  
		
	}

}
