console.log("hello world")
const toggle = document.getElementById('theme-toggle');
toggle.addEventListener('click', () => {
  document.documentElement.dataset.theme =
    document.documentElement.dataset.theme === 'dark' ? 'light' : 'dark';
});


setTimeout(()=>{
	console.log("inside")
	const msg= document.querySelector('.msg-valid');
	if(msg) msg.style.display='none';
},3000)

