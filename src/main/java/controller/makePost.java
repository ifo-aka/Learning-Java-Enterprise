package controller;

import java.io.IOException;

import dao.DataBaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Post;
import model.UserModel;

@WebServlet("/makePost")

public class makePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String title = req.getParameter("title");
		String description = req.getParameter("description"); // FIXED SPELLING
		String feeling = req.getParameter("feeling");

		Post singlePost = new Post(title, description, feeling);

		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");  // FIXED

		if (user != null) {
			user =DataBaseUtil.addPost(user.getName(),user.getEmail(), singlePost); 
			  
			    session.setAttribute("user", user);
			res.sendRedirect(req.getContextPath()+"/View/UserUi.jsp");
		} else {
			res.sendRedirect(req.getContextPath()+"/Controller/Login.jsp");
		}
	}
}
