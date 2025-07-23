
  const BASE_URL = "http://localhost:8080/MiniBlogAPP/";


  window.dolike = function (button) {
    const postCard = button.closest(".post-card");
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
    const postCard = button.closest(".post-card");
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
	
	let postCard = button.closest(".post-card");
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
    let postCard = button.closest(".post-card");
    let publicComments = postCard.querySelector(".public-comments");

    if (!publicComments) {
      console.warn("No .public-comments element found in this post-card.");
      return; // Exit the function early to avoid crash
    }

    // Toggle visibility class
    publicComments.classList.toggle("show");
  };

