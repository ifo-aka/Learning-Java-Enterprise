

  // Hide dropdown if user clicks outside
  document.addEventListener("click", function(event) {
    const dropdown = document.getElementById("dropdown");
    const icon = document.querySelector(".profile-icon");

    if (!dropdown.contains(event.target) && !icon.contains(event.target)) {
      dropdown.style.display = "none";
    }
  });
  function toggleDropdown() {
    const dropdown = document.getElementById("dropdown");
    dropdown.style.display = dropdown.style.display === "flex" ? "none" : "flex";
  }

