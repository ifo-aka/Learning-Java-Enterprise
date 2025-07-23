<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp</title>
<link   rel="stylesheet" href="<%= application.getContextPath() %>/style/SignUp.css" >
</head>
<body>
     <button id="theme-toggle">🌙 Toggle Theme</button>
<main>
      <div class="signup-container">
    <h2>Create Account</h2>
<form action="<%=application.getContextPath()%>/RegisterUser" method="post" class="signup-form" enctype="multipart/form-data">
  <input type="text" name="name" placeholder="Username" required>
  <input type="email" name="email" placeholder="Email address" required>
  <input type="password" name="password" placeholder="Password" required>
  <label>Choose profile image</label>
  <input type="file" name="profilePic" accept="image/*" required> <!-- Fix: Add name -->
  <button type="submit">Sign Up</button>
  <p class="login-link">Already have an account? <a href="Login.jsp">Login</a></p>
</form>

  </div>
  </main>
       <script type="text/javascript" src="${pageContext.request.contextPath}/script/Toggle.js"></script>
</body>
</html>