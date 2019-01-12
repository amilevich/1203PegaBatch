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
//			setValues(login);
		}
	}
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/LoginJSON.do', true);
	xhttp.send();
}

function gotoform(){
	document.getElementById("applicationForm").submit();
}