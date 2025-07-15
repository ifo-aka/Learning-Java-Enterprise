<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  

   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<link   rel="stylesheet"    href="<%=application.getContextPath() %>/style/Login.css" >
</head>
<body>
<body>
<button id="theme-toggle">🌙 Toggle Theme</button>
    <main>
      <div class="login-container">
    
    <h2>Login here</h2>
    <form action="<%=application.getContextPath()%>/LoginUser" method="post" class="login-form">
      <input type="text" name="name" placeholder="Username" required>
      <input type="password" name="password" placeholder="Password" required>
      <button type="submit">login</button>
      <p class="msg-valid" style="color: red">${loginStatus} </p>
      <p class="login-link">Don't have an account? <a href="${pageContext.request.contextPath}/Controller/SignUp.jsp">signUp</a></p>
    </form>
  </div>
    </main>
  <script type="text/javascript" src="${pageContext.request.contextPath}/script/userUI.js"></script>

</body>
</html>