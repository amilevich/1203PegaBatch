/**
 * 
 */

var employee;

window.onload = function() {
	getEmployeeInfo();
	getSupervisorInfo();
	getPendingRequests();
}

function getEmployeeInfo() {
	console.log("IN getEmployeeInfo()");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			employee = JSON.parse(xhttp.responseText);
			setEmployeeValues(employee);
		}
	}

	xhttp.open("GET", 'http://localhost:8080/TRMS/html/EmployeeJSON.do', true);
	xhttp.send();
}

function getSupervisorInfo() {
	console.log("IN getSupervisorInfo()");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let supervisor = JSON.parse(xhttp.responseText);
			setSupervisorValues(supervisor);
		}
	}

	xhttp
			.open("GET", 'http://localhost:8080/TRMS/html/SupervisorJSON.do',
					true);
	xhttp.send();
}

function setEmployeeValues(employee) {
	console.log("IN setEmployeeValues()");
	document.getElementById("greeting").innerHTML = "Hello, "
			+ employee.firstName + "!";
	document.getElementById("name").innerHTML = employee.firstName + " "
			+ employee.lastName;
	document.getElementById("email").innerHTML = employee.email;
	document.getElementById("phoneNumber").innerHTML = employee.phoneNumber;
	document.getElementById("employeeId").innerHTML = "Employee ID: "
			+ employee.employeeId;
	document.getElementById("jobTitle").innerHTML = "Position: "
			+ employee.jobTitle;
	document.getElementById("departmentName").innerHTML = "Department: "
			+ employee.departmentName;
	document.getElementById("reimbursementFunds").innerHTML = "Available funds: $"
			+ employee.availbleFunds;
}

function setSupervisorValues(supervisor) {
	console.log("IN setSupervisorValues()");
	if (supervisor) {
		document.getElementById("reportTo").innerHTML = "Supervisor: "
				+ supervisor.firstName + " " + supervisor.lastName;
	}
}

function onClick(this_) {
	console.log("in onClick()");
	console.log("id=" + this_.parentNode.getAttribute("id") + " "
			+ this_.parentNode.id.value);
}
function getPendingRequests() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let requests = JSON.parse(xhttp.responseText);
			console.log("responseText=" + xhttp.responseText);
			buildHTMLtable(requests);
		}
	}
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/PendingRequestsJSON.do',
			true);
	xhttp.send();
}

function buildHTMLtable(requests) {

	console.log("buildHTMLtable");

	var table = document.createElement("table");
	var tr = table.insertRow(-1);

	for (var i = 0; i < 3; i++) {
		var th = document.createElement("th"); // TABLE HEADER.
		if (i == 0) {
			th.innerHTML = "Event Name";
		} else if (i == 1) {
			th.innerHTML = "Completion Date";
		} else {
			th.innerHTML = "";
		}
		tr.appendChild(th);
	}

	for (var i = 0; i < requests.length; i++) {
		tr = table.insertRow(-1);
		tr.setAttribute("id", requests[i].requestId);
		for (var j = 0; j < 3; j++) {
			var tabCell = tr.insertCell(-1);
			if (j == 0) {
				tabCell.innerHTML = requests[i].eventName;
			} else if (j == 1) {
				tabCell.innerHTML = requests[i].dateCompleted;
			} else {
				tabCell.innerHTML = '<button onclick="onClick(this);" class="btn  btn-dark btn-block registerBtn text-uppercase" type="submit">View</button>';
			}
		}

	}

	console.log("table2=" + table);

	var divContainer = document.getElementById("requests");
	divContainer.innerHTML = "";
	divContainer.appendChild(table);

}
