/**
 * 
 */

window.onload = function() {
	getEmployeeInfo();
	getSupervisorInfo();
}

function getEmployeeInfo() {
	console.log("IN getEmployeeInfo()");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let employee = JSON.parse(xhttp.responseText);
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

	xhttp.open("GET", 'http://localhost:8080/TRMS/html/SupervisorJSON.do', true);
	xhttp.send();
}

function setEmployeeValues(employee) {
console.log("IN setEmployeeValues()");
	document.getElementById("firstname").innerHTML = "Hello, "
			+ employee.firstName + "!";
	document.getElementById("name").innerHTML = employee.firstName + " "
			+ employee.lastName;
	document.getElementById("email").innerHTML = employee.email;
	document.getElementById("phoneNumber").innerHTML = employee.phoneNumber;
	document.getElementById("employeeId").innerHTML = "Employee ID: " + employee.employeeId;
	document.getElementById("jobTitle").innerHTML = "Position: " + employee.jobTitle;
	document.getElementById("departmentName").innerHTML = "Department: " + employee.departmentName;
	document.getElementById("reimbursementFunds").innerHTML = "Available funds: $" + employee.availbleFunds;

}

function setSupervisorValues(supervisor) {
	console.log("IN setSupervisorValues()");
if (supervisor) {
	document.getElementById("reportTo").innerHTML = "Supervisor: " + supervisor.firstName + " " + supervisor.lastName;
}
}
