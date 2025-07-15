# 🌐 Mini Blog Web App

A full-stack **Java Servlet + JSP** application with **MongoDB** backend — built as a practice project to simulate a mini Facebook-style blog platform.

---

## 📌 Features Implemented

✅ User Registration & Login  
✅ Session-based Authentication  
✅ Secure MongoDB Integration  
✅ Create Posts (Public or Private)  
✅ View Own Posts (Private Dashboard)  
✅ Toggle Light/Dark Theme  
✅ Responsive Glassmorphism UI  

---

## 🛠️ Tech Stack

- **Frontend**: HTML, CSS, JSP  
- **Backend**: Java Servlets (JSP, HttpSession)  
- **Database**: MongoDB (Java Driver)  
- **Build Tool**: Apache Tomcat  
- **IDE**: IntelliJ / Eclipse  

---

## 📂 Project Structure

📦 BlogApp
├── 📁 Controller (Servlets)
│ └── Login, Logout, Register, PostHandler
├── 📁 Model (JavaBeans)
│ └── UserModel, Post
├── 📁 View (JSP Pages)
│ └── Login.jsp, SignUp.jsp, UserUi.jsp, makePost.jsp
├── 📁 style/
│ └── CSS files (Login.css, userUi.css)
├── 📁 script/
│ └── userUI.js (theme toggle, dropdown)
├── 📁 dao/
│ └── DataBaseUtil.java
└── web.xml

---

## 🚧 Upcoming Features (WIP 🚀)

- [ ] Public Feed (view posts from all users)  
- [ ] Like & Comment System  
- [ ] Edit / Delete Post  
- [ ] Profile Page with Total Post Count  
- [ ] Admin Dashboard  
- [ ] REST API integration (Phase 6)  
- [ ] Spring Boot refactor (Phase 8–10)

---

## ⚙️ Run Locally

> Prerequisites:
- Java 17+
- Apache Tomcat 10+
- MongoDB running on `localhost:27017`

1. Clone the repo  
   ```bash
   git clone https://github.com/your-username/blog-webapp.git
🧠 About the Developer
👨‍💻 Ifham
A passionate full-stack & cybersecurity enthusiast, building real-world Java web apps from scratch while mastering backend systems, scalable architecture, and professional dev patterns.
⭐ Support the Journey
If you find this useful, drop a ⭐ on the repo and follow the journey as the app evolves from scratch to full-stack production level!

