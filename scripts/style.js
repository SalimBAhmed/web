function makeCurrentPage(elt){
	var current = document.getElementsByClassName("current-page");
	current[0].className = current[0].className.replace(" current-page","");

	elt.className += " current-page";
}