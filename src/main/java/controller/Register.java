package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.UserModel;

import java.io.IOException;


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
		  
		UserModel user = null;
		try {
			user = DataBaseUtil.registerUser(userName,userEmail,hashPassword,imgBytes);
			
		}catch(Exception e) {
			System.out.print(e);
		}
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(10*60);
			res.sendRedirect(req.getContextPath()+"/View/UserUi.jsp");
		}
		else {
			res.sendRedirect(req.getContextPath()+"/Controller/Login.jsp");
		}
		   
		   
		   
		   
		   
		  
		  
		  
		  
		  
		
	}

}
