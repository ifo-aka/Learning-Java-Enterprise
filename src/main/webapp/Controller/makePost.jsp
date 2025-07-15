<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add a New Post</title>
    <link rel="stylesheet" href="<%= application.getContextPath() %>/style/newPost.css">
</head>
<body>

<div class="form-container">
    <h2>Create New Post</h2>
    <form action="<%= application.getContextPath() %>/makePost" method="post">
        <label for="title">Post Title</label>
        <input type="text" name="title" id="title" required />

        <label for="description">Description</label>
        <textarea name="description" id="description" required></textarea>

        <label for="feeling">How are you feeling?</label>
        <select name="feeling" id="feeling" required>
            <option value="">--Select Feeling--</option>
            <option value="Happy">😊 Happy</option>
            <option value="Sad">😢 Sad</option>
            <option value="Angry">😠 Angry</option>
            <option value="Excited">🤩 Excited</option>
            <option value="Anxious">😟 Anxious</option>
        </select>

        <button type="submit">Post</button>
    </form>
</div>

</body>
</html>
