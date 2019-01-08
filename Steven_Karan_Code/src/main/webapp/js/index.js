/**
 * 
 */
window.onload = function() {
	console.log("in onload function");
	getEmployeePersonalInfo();
	getAlert();
}

function getEmployeePersonalInfo() {
	console.log("hello in getEmployee")
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {	
			let emp = JSON.parse(xhttp.responseText);
			setValues(emp);
		}
	};
	xhttp.open("GET",
			'http://localhost:9000/ReimbursementSystem/html/empJSON.do',
			true);
	xhttp.send();
}

function setValues(emp) {
	if (emp != null) {
		document.getElementById("welcome").innerHTML = "<h1>Welcome, "
				+ emp.firstname + " " + emp.lastname + "</h1>";
	}
	else
		document.getElementById("welcome").innerHTML = "<h1>Welcome!</h1>";
}
