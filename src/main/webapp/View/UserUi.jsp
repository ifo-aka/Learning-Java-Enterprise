<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.UserModel,model.Post,java.util.*" %>
    <%  UserModel  model = (UserModel) session.getAttribute("user");
    if(model == null){
    	response.sendRedirect(request.getContextPath()+"/Controller/Login.jsp");
    	return;
    	}
    List<Post> posts= model.getPosts();
    int totalPost = posts.size();
    %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>My Blog</title>
  <link rel="stylesheet" href="<%= application.getContextPath() %>/style/userUi.css" />
</head>
<body>
     <header>
        <nav>
            <ul>
                <li><a href="<%=application.getContextPath() %>/View/UserUi.jsp">Home</a></li>
                <li><a href="<%=application.getContextPath() %>/Controller/SignUp.jsp">Sign Up</a></li>
                <li><a href="<%=application.getContextPath() %>/Controller/Login.jsp">Login</a></li>
                <li><a href="<%=application.getContextPath() %>/Controller/makePost.jsp">Add Post</a></li>
                
                
               
            </ul>
         <div class="profile-container">
         <%   byte[] img = model.getProfileImg();
         String base64Img = java.util.Base64.getEncoder().encodeToString(img);
         %>
  <img src="data:image/png;base64,<%= base64Img %>" alt="Profile" class="profile-icon" onclick="toggleDropdown()" />

  <div class="dropdown-box" id="dropdown">
    <p>👤 <%= model.getName() %></p>
    <p><%= model.getEmail() %></p>
    <p>Total No of Posts : <%= totalPost %>  </p>
    <a href="<%=application.getContextPath() %>/Logout">🚪 Logout</a>
  </div>
</div>

        </nav>
    </header>

  <main>
   <div class="postsContainer">
  <%
  if(posts.isEmpty()){
	
	  %>
	  <h2>no posts add some posts to see</h2>
	  
<% 
  }else{
  
  for(Post post: posts){
%>  
	  
  

  
   
      <!-- Repeatable post card -->
      <div class="post">
        <h3> Post name : <%= post.getTitle()  %></h3>
        <p class="postDescription"><b>Post description </b> <br> <%= post.getDescription() %></p>
        <p>Status :  <%= post.getFeeling() %></p>
      </div>
  
     <% }} %>
      
    </div>
  </main>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/userUI.js"></script>
</body>
</html>
    