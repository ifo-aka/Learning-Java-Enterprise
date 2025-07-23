# 🌐 MiniBlog Web App

A full-stack blog platform built with **Java Servlets, JSP**, and **MongoDB**, simulating a mini Facebook-style environment. Users can sign up, log in, post public or private blogs, comment, like, and manage their content in real-time. This project was built to practice modern web development, backend integration, and UI design from scratch.

---

## 🚀 Features

✅ User Registration & Login  
✅ Session-Based Authentication  
✅ Create Posts (Public & Private)  
✅ Personal Dashboard with Post Stats  
✅ Real-Time Comments & Like System  
✅ Prevent Duplicate Likes  
✅ Update/Delete Own Posts  
✅ View Public Posts of Other Users  
✅ Responsive UI (Dark/Light Mode)  
✅ Profile Info Modal  
✅ MongoDB Integration (Java Driver)  
✅ Clean and Modular Code Structure

---

## 🧠 Architecture Overview

- 🗂 **MVC Pattern**: Clean separation of concerns (Servlets = Controller, JavaBeans = Model, JSP = View)  
- 🧩 **MongoDB NoSQL**: Documents for users, nested posts & interactions  
- 🔒 **Session Handling**: Java `HttpSession` for secure login state  
- 📊 **Like/Comment Control**: Backend logic to avoid duplicate interactions  

---

## 🛠️ Tech Stack

| Layer       | Technology               |
|------------|---------------------------|
| Frontend   | HTML, CSS, JavaScript, JSP |
| Backend    | Java Servlets (JSP, HttpSession) |
| Database   | MongoDB (Official Java Driver) |
| Server     | Apache Tomcat 10+ |
| IDE        | IntelliJ IDEA / Eclipse |

---

## 📂 Folder Structure

📦 MiniBlogApp/
├── 📁 controller/ # Java Servlets (Login, Logout, Register, PostHandler)
├── 📁 model/ # JavaBeans (UserModel, Post, PublicPosts)
├── 📁 view/ # JSP Pages (Login.jsp, SignUp.jsp, makePost.jsp, PublicView.jsp)
├── 📁 script/ # JavaScript (UI logic, theme toggle, dropdown, like/comment)
├── 📁 style/ # CSS files (Dark Theme, Glassmorphism)
├── 📁 dao/ # MongoDB Util (DataBaseUtil.java)
└── web.xml # Servlet Config

---

## 🧪 How to Run Locally

> Prerequisites:
- Java 17+
- Apache Tomcat 10+
- MongoDB running locally on `mongodb://localhost:27017`

### 🧱 Setup Steps

```bash
# 1. Clone the Repository
git clone https://github.com/your-username/MiniBlogApp.git

# 2. Open in IntelliJ or Eclipse
# 3. Configure Tomcat Server & MongoDB
# 4. Build and Deploy WAR file (or run directly)
# 5. Open in browser: http://localhost:8080/MiniBlogApp
🚧 Upcoming Features
 Admin Dashboard (User Management)

 RESTful API Support (Phase 6)

 Notification System

 Post Sharing & Bookmarking

 Spring Boot Refactor (Phase 8–10)

 Email Verification & Reset Password

 Enhanced Security (JWT, BCrypt)

👨‍💻 About the Developer
Ifham – A passionate full-stack developer and cybersecurity enthusiast from Pakistan 🇵🇰.
Loves crafting real-world projects from scratch, working with backends, and designing secure, interactive user interfaces.

💬 “Every project is a new battlefield to sharpen my code and logic.”

⭐ Support
If you found this project helpful or inspiring:

Star the repo ⭐

Share with other devs 💬

Follow for updates 🔔
