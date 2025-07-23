package controller;

import java.io.IOException;
import java.util.List;

import dao.DataBaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.PublicPostModel;
import model.PublicPosts;
import model.ResultsModel;
import model.UserModel;

@WebServlet("/LoginUser")		
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=  req.getParameter("name");
		String password = req.getParameter("password");
		ResultsModel result = null;
		try {
			result = DataBaseUtil.loginUser(username,password);
			
		}catch(Exception e) {
			System.out.print(e);
		}
		if(result != null) {	
			UserModel user = result.getUser();
			List<PublicPosts> publicPosts = result.getPublicPosts();
			
		PublicPostModel publicPostModel= new PublicPostModel(publicPosts);
		
		    HttpSession session = req.getSession();
		    session.setAttribute("user", user); // save user object
		    session.setAttribute("publicPostModel",publicPostModel );
		    session.setMaxInactiveInterval(10 * 60); // 10 min
		   resp.sendRedirect(req.getContextPath()+"/View/PublicView.jsp");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("loginStatus", "Invalid username or password");
			resp.sendRedirect(req.getContextPath() + "/Controller/Login.jsp");


		}
		

	
	}
	
	





	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
	

}
