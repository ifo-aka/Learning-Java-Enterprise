
document.addEventListener("DOMContentLoaded",()=>{
const BASE_URL = "http://localhost:8080/MiniBlogAPP/";

document.addEventListener("click", function(event) {
  const dropdown = document.getElementById("dropdown");
  const icon = document.querySelector(".profile-icon");

  if (!dropdown.contains(event.target) && !icon.contains(event.target)) {
    dropdown.style.display = "none";
  }
});

window.toggleDropdown=   function () {
  const dropdown = document.getElementById("dropdown");
  dropdown.style.display = dropdown.style.display === "flex" ? "none" : "flex";
}

// Toggle post option menu
window.togglePostMenu = function(button) {
  document.querySelectorAll('.post-menu').forEach(menu => menu.classList.add('hidden'));
  const menu = button.nextElementSibling;
  menu.classList.toggle('hidden');
};

// Hide post menu when clicking outside
window.addEventListener('click', (e) => {
  if (!e.target.closest('.post-options')) {
    document.querySelectorAll('.post-menu').forEach(menu => menu.classList.add('hidden'));
  }
});

// Show update post form
window.showUpdateForm  =function(postId) {
  const postCard = document.querySelector(`.post[data-id='${postId}']`);
  const title = postCard.querySelector('.post-title').innerText;
  const description = postCard.querySelector('.post-description').innerText;
  const feeling = postCard.querySelector('.post-feeling').innerText || '';
  const visibility = 'public';

  document.getElementById("updateTitle").value = title;
  document.getElementById("updateDescription").value = description;
  document.getElementById("updateFeeling").value = feeling;
  document.getElementById("updateVisibility").value = visibility;
  document.getElementById("updateFormWrapper").style.display = "block";
   let updateBtn =document.querySelector(".update-post");
   updateBtn.setAttribute("data-stamp",postId);
   
  
  
}

let updateBtn= document.querySelector(".update-post");
updateBtn.addEventListener("click", (e)=>{
	e.preventDefault();
	let dataStamp= updateBtn.getAttribute("data-stamp");
let title =	document.getElementById("updateTitle").value ;
let  description=	document.getElementById("updateDescription").value;
 let    feeling =	document.getElementById("updateFeeling").value ;
	 let  visibility = document.getElementById("updateVisibility").value;
	let arr= [title,description,feeling,visibility];
	 if(arr.every(i=> i.trim() != "")){
	  console.log(arr)
	   fetch(`${BASE_URL}UpdatePost`,{
		method : "POST",
		headers: {
		'Content-Type': "application/json"
     	},
	 body: JSON.stringify({
		postStamp: dataStamp,
		newTitle : title,
		newDescription : description,
		newFeeling : feeling,
		newVisibility: visibility
	 })
		
	}).then(res=> res.json())
	.then(data=>{
		if (data.status === "success") {
		

		  document.getElementById("updateFormWrapper").style.display = "none";
		  window.location.reload();
		
		}
		
	})
	.catch(e=> console.log(e)) 
	}else{
		console.log("is not ok")
		alert('fill the fields ')
	}
})



let closeBtn = document.querySelector(".close-form");
closeBtn.addEventListener("click",()=>{
	document.getElementById("updateFormWrapper").style.display = "none";
})


 window.dolike = function (button) {
   const postCard = button.closest(".post");
   const likes = postCard.querySelector(".likes");

   let likeCount = parseInt(likes.innerText.split(": ")[1]) || 0;
   let liked = button.getAttribute("data-liked") === "true";
   let postId = button.getAttribute("data-id");
   let postTime = button.getAttribute("data-stamp");

   const endpoint = liked ? "Unlike" : "Like";
   const newLikedState = !liked;

   
   button.disabled = true;

   fetch(`${BASE_URL}${endpoint}`, {
     method: "POST",
     headers: {
       "Content-Type": "application/json"
     },
     body: JSON.stringify({
       postId: postId,
       postTime: postTime
     })
   })
     .then(response => response.json())
     .then(data => {
       if (data.status) {
         if (newLikedState) {
           likeCount++;
           button.textContent = "Liked ❤️";
           button.classList.add("is-liked");
         } else {
           likeCount--;
           button.textContent = "Like";
           button.classList.remove("is-liked");
         }

         // Update UI
         likes.innerText = "Likes: " + likeCount;
         button.setAttribute("data-liked", String(newLikedState));
       } else {
         console.warn("Action failed on server");
       }
     })
     .catch(error => {
       console.error("Fetch error:", error);
     })
     .finally(() => {
       button.disabled = false;
     });
 };


 window.toggleComment = function (button) {
   const postCard = button.closest(".post");
   const commentSection = postCard.querySelector(".comment-section");

   commentSection.classList.toggle("hidden");
   commentSection.classList.toggle("show");
 };


 function checkInitialLike() {
   console.log("Initializing like button states...");
   const buttons = document.querySelectorAll(".like-btn");

   buttons.forEach(btn => {
     const liked = btn.getAttribute("data-liked") === "true";
     if (liked) {
       btn.textContent = "Liked ❤️";
       btn.classList.add("is-liked");
     } else {
       btn.textContent = "Like";
       btn.classList.remove("is-liked");
     }
   });
 }
 
 
 

 checkInitialLike();
 
 
 
 window.doComment = function (button){

let postCard = button.closest(".post");
let commentBox= postCard.querySelector(".comment-box");
let postId = button.getAttribute("data-id");
let timeStamp = button.getAttribute("data-stamp");
let warning = document.querySelector(".warning");

if(commentBox.value == ""){
	warning.classList.add("show");
	setTimeout(() => {
	  warning.classList.remove("show");
	}, 3000);

	return; 
}
else{
	let comment = commentBox.value;
	fetch(`${BASE_URL}doComment`,{
		method: "POST",
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			commentText: comment,
			postId: postId,
			timeStamp: timeStamp
		})
	}).then(res=> res.json())
	.then(data=>{
		if (data.status === "success") {
				

				 
				  window.location.reload();
				
				}
		
	})
	.catch(error=> console.log(error))
}



 }
 
 
 
 window.showComment = function (button) {
   let postCard = button.closest(".post-wrapper");
   let publicComments = postCard.querySelector(".public-comments");

   if (!publicComments) {
     console.warn("No .public-comments element found in this post-card.");
     return; // Exit the function early to avoid crash
   }

   // Toggle visibility class
   publicComments.classList.toggle("show");
 };


 window.deletePost =  function(timestamp){
	
	fetch(`${BASE_URL}DeletePost`,{
			method: "POST",
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				postStamp: timestamp
			})
			
	}).then(res=> res.json())
			.then(data=>{
				if (data.status === "success") {
						window.location.reload();
						
						}
				
			})
			.catch(error=> console.log(error))
		}
	

})




	
	
	
	
	
	
	

