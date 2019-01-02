/**
 * 
 */
window.onload = function(){
	getLoginInfo();
}

function getLoginInfo() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let login = JSON.parse(xhttp.responseText);
			setValues(login);
		}
	}
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/LoginJSON.do', true);
	xhttp.send();
}
	function setValues(login) {
		document.getElementById("name").innerHTML = "Login Name: " + login.name;
		document.getElementById("type").innerHTML = "Login Password: " + login.type;
	}
