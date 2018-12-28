/**
 * 
 */
	window.onload = function() {
		console.log("in onload function");
		getEmployeePersonalInfo();
	};

function getEmployeePersonalInfo() {
	let xhttp = XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readystate == 4 && xhttp.status == 200) {
			console.log("emp: "+emp);
			let emp = JSON.parse(xhttp.responseText);
			setValues(emp);
		}
	};
	xhttp.open("PUT",
			'http://localhost:9000/ReimbursementSystem/html/employeeJSON.do',
			false);
	xhttp.send();
}

function setValues(emp) {
	if (emp != null) {
		document.getElementById("welcome").innerHTML = "Welcome, "
				+ emp.firstname + " " + emp.lastname;
	}
}
