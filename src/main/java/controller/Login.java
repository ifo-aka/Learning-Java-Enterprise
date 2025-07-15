package controller;

import java.io.IOException;


import dao.DataBaseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;

@WebServlet("/LoginUser")		
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=  req.getParameter("name");
		String password = req.getParameter("password");
	UserModel user = null;;
	try {
		user = DataBaseUtil.loginUser(username, password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		if (user != null) {
		    HttpSession session = req.getSession();
		    session.setAttribute("user", user); // save user object
		    session.setMaxInactiveInterval(10 * 60); // 10 min
		   resp.sendRedirect(req.getContextPath()+"/View/UserUi.jsp");
		} else {
		    req.setAttribute("loginStatus", "Invalid username or password");
		    resp.sendRedirect(req.getContextPath()+"/Controller/Login.jsp");
		}
}
	
	





	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
	

}
