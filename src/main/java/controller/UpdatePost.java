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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.bson.types.ObjectId;
import org.json.JSONObject;



import dao.DataBaseUtil;


@WebServlet("/UpdatePost")
public class UpdatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       




	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		StringBuilder sb = new StringBuilder();
		String line ;
		
		try(BufferedReader br = req.getReader()){
			while((line= br.readLine()) != null) {
				sb.append(line);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		JSONObject jso = new JSONObject(sb.toString());
		
		
		HttpSession session = req.getSession(false);
		UserModel model =(UserModel) session.getAttribute("user");
		
		// mandatory to find and update
		ObjectId id = new ObjectId(model.getId());
		String timeStamp = jso.getString("postStamp");
		
		// update post data
		String newTitle= jso.getString("newTitle");
		String newDescription = jso.getString("newDescription");
		String newFeeling = jso.getString("newFeeling");
		String newVisibility = jso.getString("newVisibility");
		
		  LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        String formattedTime = now.format(formatter);
	        
		boolean isUpdated= DataBaseUtil.updatePost(id, timeStamp, newTitle, newDescription, newFeeling, newVisibility, formattedTime);
		
		
        System.out.println("Servlet hit ✅");
        res.setContentType("application/json");
        if(isUpdated) {
     model= DataBaseUtil.getUpdatedposts(id);
     session.setAttribute("user",model);
       
        res.getWriter().write("{\"status\":\"success\"}");
        
        }
        else {
        	 res.getWriter().write("{\"status\":\"failed\"}");
        }
		
        
	}

}
