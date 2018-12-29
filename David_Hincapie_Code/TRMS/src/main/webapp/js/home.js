/**
 * 
 */

window.onload = function(){
	getEmployeeInfo();
}

function getEmployeeInfo() {

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let employee = JSON.parse(xhttp.responseText);
			setValues(employee);
		}
	}
	
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/EmployeeJSON.do', true);
	xhttp.send();
}
function setValues(employee){
	document.getElementById("email").innerHTML = "User's email: " + employee.email;
	document.getElementById("password").innerHTML = "User's password: " + employee.password;
	
	}