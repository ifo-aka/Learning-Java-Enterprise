<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserModel,model.Post,java.util.*,model.*" %>
<%
    UserModel model = (UserModel) session.getAttribute("user");
    if (model == null) {
        response.sendRedirect(request.getContextPath() + "/Controller/Login.jsp");
        return;
    }
    List<Post> posts = model.getPosts();
    int totalPost = posts.size();
    int totalLikes= 0;
    int totalComments=0;
    for(Post post: posts){
    	if(post.getLikes().getId().size() >0){
    	     totalLikes +=	post.getLikes().getId().size();
    	     totalComments +=  post.getComments().size();
    	}
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel="stylesheet" href="<%= application.getContextPath() %>/style/userUi.css"/>
</head>
<body>
<header>
  <nav>
    <p>PERSONAL PROFILE</p>
    <ul>
      <li><a href="<%= application.getContextPath() %>/View/PublicView.jsp">Home</a></li>
    </ul>
    <div class="profile-container">
      <%
        byte[] img = model.getProfileImg();
        String base64Img = java.util.Base64.getEncoder().encodeToString(img);
      %>
      <img src="data:image/png;base64,<%= base64Img %>" alt="Profile" class="profile-icon" onclick="toggleDropdown()" />
      <div class="dropdown-box" id="dropdown">
        <p>👤 <%= model.getName() %></p>
        <p><%= model.getEmail() %></p>
        <p>Total No of Posts: <%= totalPost %></p>
        <a href="<%= application.getContextPath() %>/Logout">🚪 Logout</a>
      </div>
    </div>
  </nav>
</header>

<!-- Floating Add Button -->
<a href="<%= application.getContextPath() %>/Controller/makePost.jsp" class="add-post-btn" title="Add Post">+</a>

<section class="profile-summary">
  <div class="profile-summary-card">
    <div class="user-img-box">
      <img src="data:image/png;base64,<%= base64Img %>" alt="User" />
    </div>
    <div class="summary-metrics">
      <div class="metric-box">
        <h4>Total Posts</h4>
        <p><%= totalPost %></p>
      </div>
      <div class="metric-box">
        <h4>Total Likes</h4>
        <p><%= totalLikes %></p>
      </div>
      <div class="metric-box">
        <h4>Total Comments</h4>
        <p><%= totalComments %></p>
      </div>
    </div>
  </div>
</section>

<main>

  <div class="postsContainer">
    <% if (posts.isEmpty()) { %>
      <h2>No posts yet. Start sharing your thoughts!</h2>
    <% } else {
         for (Post post : posts) { %>
          <div class="post-wrapper">
     <div class="post"   data-id="<%= post.getDateAndTimeOfPost() %>">
  <div class="post-header">
      <p>Upload Date: <%= post.getDateAndTimeOfPost().split(" ")[0] %>
     | Time: <%= post.getDateAndTimeOfPost().split(" ")[1] %></p>
   
   
    <div class="post-options">
      <button class="dots-btn" onclick="togglePostMenu(this)">
        ⋮
      </button>
      <div class="post-menu hidden">
      <button class="update-btn" onclick="showUpdateForm('<%= post.getDateAndTimeOfPost() %>')">✏️ Update</button>
      
        <button class="menu-btn delete-btn"   onclick="deletePost('<%= post.getDateAndTimeOfPost() %>')">🗑️ Delete</button>
      </div>
    </div>
  </div>
  <div class="post-data">
 <h3 class="post-title"><%= post.getTitle() %></h3>
 <p >POST Thoughts</p>
  <p class="post-description" id="discription"> <%= post.getDescription() %></p>
  <p class="post-feeling  feeling-<%=post.getFeeling().toLowerCase().trim() %>" >Status: <%= post.getFeeling() %></p>
 </div>
   <ul class="post-stats">
                        <li class="likes">Likes: <%= post.getLikes().getId().size() %></li>
                        <li>Comments: <%= post.getComments().size() %></li>
                    </ul>
                    <div class="btn-cont">
                        <button class="like-btn"
                                onclick="dolike(this)"
                                data-liked="<%= post.getLikes().getId().contains(model.getId()) %>" 
                                data-id="<%= model.getId() %>"
                                data-stamp="<%= post.getDateAndTimeOfPost() %>">Like
                        </button>
                        <button class="comment-btn" onclick="toggleComment(this)">Add comment</button>
                        <button class="showCommentBtn" onclick="showComment(this)">Show comments</button>
                    </div>
                     <div class="comment-section hidden">
                        <div class="commentandbtn">
                            <input type="text" class="comment-box" placeholder="Write a comment...">
                            <button data-stamp="<%= post.getDateAndTimeOfPost() %>"
                                    data-id="<%= model.getId() %>"
                                    onclick="doComment(this)">Add</button>
                        </div>
                        
 
				</div>
				     
				</div>
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
				     
				</div>
				    <%   }
				       } %>
				       
				  </div>
				 
  <!-- Warning Message -->
<div class="warning">
    <h4>Warning</h4>
    <p>please write a comment to add.</p>
</div>
  
</main>
<!-- Fixed Update Form -->
<div class="update-form-wrapper" id="updateFormWrapper">
   <button class="close-form">x</button>
    <div class="form-container">
        <h2>Update Post</h2>
        <form id="updateForm" >
            <label for="title">Post Title</label>
            <input type="text" name="title" id="updateTitle" required />

            <label for="description">Description</label>
            <textarea name="description" id="updateDescription" required></textarea>

            <label for="feeling">How are you feeling?</label>
            <select name="feeling" id="updateFeeling" required>
               <option value="">--Select Feeling--</option>
                <option value="Happy">😊 Happy</option>
                <option value="Sad">😢 Sad</option>
                <option value="Angry">😠 Angry</option>
                <option value="Excited">🤩 Excited</option>
                <option value="Anxious">😟 Anxious</option>
            </select>

            <label for="visibility">Choose post visibility?</label>
            <select name="visibility" id="updateVisibility" required>
                <option value="">--Select Visibility--</option>
                <option value="public">Public</option>
                <option value="private">Private</option>
            </select>

            <button type="submit" class="update-post">Update</button>
                </form>
        </div>

</div>
<div class="warning">
    <h4 class="label">Warning</h4>
    <p class="message">please write a comment to add.</p>
</div>



<script type="text/javascript" src="${pageContext.request.contextPath}/script/userUI.js"></script>
</body>
</html>
