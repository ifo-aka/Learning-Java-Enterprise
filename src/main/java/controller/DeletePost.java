package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;

import java.io.BufferedReader;
import java.io.IOException;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import dao.DataBaseUtil;


@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		String timeStamp= jb.getString("postStamp");
		
		HttpSession session = req.getSession(false);
		
		UserModel model=(UserModel) session.getAttribute("user");
		ObjectId  id = new ObjectId(model.getId());
		
		
		boolean isDeleted = DataBaseUtil.deletePost(id, timeStamp);
		
		
		if(isDeleted) {
			  
				     model= DataBaseUtil.getUpdatedposts(id);
				     session.setAttribute("user",model);
				       
				        res.getWriter().write("{\"status\":\"success\"}");
				        
				        }
				        else {
				        	 res.getWriter().write("{\"status\":\"failed\"}");
				        }
			
			
			
		
		
		
		
	}

}
