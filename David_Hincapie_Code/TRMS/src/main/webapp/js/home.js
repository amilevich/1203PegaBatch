/**
 * 
 */

window.onload = function() {
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
function setValues(employee) {

	document.getElementById("firstname").innerHTML = "Hello, "
			+ employee.firstName + "!";
	document.getElementById("name").innerHTML = employee.firstName + " "
			+ employee.lastName;
	document.getElementById("email").innerHTML = employee.email;
	document.getElementById("phoneNumber").innerHTML = employee.phoneNumber;
	document.getElementById("employeeId").innerHTML = "Employee ID: " + employee.employeeId;
	document.getElementById("departmentId").innerHTML = "Department ID: " + employee.departmentId;
	document.getElementById("reportTo").innerHTML = employee.reportTp;
	document.getElementById("reimbursementFunds").innerHTML = "Availible funds: $" + employee.availbleFunds;

}
