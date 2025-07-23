<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserModel,model.CommentDetails,java.util.*,model.PublicPostModel,model.PublicPosts" %>

<%
    UserModel model = (UserModel) session.getAttribute("user");
    if (model == null) {
        response.sendRedirect(request.getContextPath() + "/Controller/Login.jsp");
        return;
    }

    PublicPostModel publicPostModel = (PublicPostModel) session.getAttribute("publicPostModel");
    if (publicPostModel == null) {
        publicPostModel = new PublicPostModel();
        session.setAttribute("publicPostModel", publicPostModel);
    }

    List<PublicPosts> posts = publicPostModel.getPublicList();
    Collections.shuffle(posts);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Public Feed</title>
    <link rel="stylesheet" href="<%=application.getContextPath()%>/style/publicFeed.css">
</head>
<body>

<header>
    <h1>🌍 BLOG</h1>
    <%
        byte[] img = model.getProfileImg();
        String base64Img = java.util.Base64.getEncoder().encodeToString(img);
    %>
    <a href="<%=application.getContextPath()%>/Controller/makePost.jsp">Add Post</a>
    <a href="<%=application.getContextPath()%>/Logout">Logout</a>
    <a href="<%=application.getContextPath()%>/View/UserUi.jsp" class="profile-button">
        <img src="data:image/png;base64,<%= base64Img %>" alt="Profile" class="profile-icon">
    </a>
</header>

<div class="feed-container">
    <% if (!posts.isEmpty()) {
        for (PublicPosts post : posts) { %>
            <div class="post-wrapper">
                <div class="post-card">
                    <div class="post-title"><%= post.getTitle() %></div>
                    <div class="post-meta">Posted by: <%= post.getAuthor() %> | <%= post.getDateAndTime() %></div>
                    <div class="post-description"><%= post.getDescription() %></div>
                    <div class="post-feeling feeling-<%= post.getFeeling().toLowerCase() %>">Feeling <%= post.getFeeling() %></div>
                    <ul class="post-stats">
                        <li class="likes">Likes: <%= post.getLikes().getId().size() %></li>
                        <li>Comments: <%= post.getComments().size() %></li>
                    </ul>
                    <div class="btn-cont">
                        <button class="like-btn"
                                onclick="dolike(this)"
                                data-liked="<%= post.getLikes().getId().contains(model.getId()) %>"
                                data-id="<%= post.getId() %>"
                                data-stamp="<%= post.getDateAndTime() %>">
                            Like
                        </button>
                        <button class="comment-btn" onclick="toggleComment(this)">Add comment</button>
                        <button class="showCommentBtn" onclick="showComment(this)">Show comments</button>
                    </div>

                    <!-- Comment Input Section -->
                    <div class="comment-section hidden">
                        <div class="commentandbtn">
                            <input type="text" class="comment-box" placeholder="Write a comment...">
                            <button data-stamp="<%= post.getDateAndTime() %>"
                                    data-id="<%= post.getId() %>"
                                    onclick="doComment(this)">Add</button>
                        </div>
                    </div>

                    <!-- Public Comments -->
                    <% if (!post.getComments().isEmpty()) { %>
                        <div class="public-comments">
                            <% for (CommentDetails comment : post.getComments()) { %>
                                <div class="comment">
                                    <div class="comment-header">
                                        <span class="commenter-name"><%= comment.getCommenterName() %></span>
                                        <span class="comment-time"><%= comment.getTimeStamp() %></span>
                                    </div>
                                    <div class="comment-body">
                                        <%= comment.getComment() %>
                                    </div>
                                </div>
                            <% } %>
                        </div>
                    <% }else { %>
                    <div class="public-comments" >no comment yet</div>
                    <% } %>

                </div> <!-- post-card -->
            </div> <!-- post-wrapper -->
    <%  } // end for
    } // end if %>
</div> <!-- feed-container -->

<!-- Warning Message -->
<div class="warning">
    <h4>Warning</h4>
    <p>please write a comment to add.</p>
</div>

<script type="text/javascript" src="<%= application.getContextPath() %>/script/PublicFeed.js"></script>
</body>
</html>
