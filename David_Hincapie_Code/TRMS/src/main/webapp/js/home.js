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
			+ employee.refund;
}

function setSupervisorValues(supervisor) {
	console.log("IN setSupervisorValues()");
	if (supervisor) {
		document.getElementById("reportTo").innerHTML = "Supervisor: "
				+ supervisor.firstName + " " + supervisor.lastName;
	}
}

function onClick(id) {
	
	document.getElementById("reqId").value = id;
	document.getElementById("myForm").submit();
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
	
//	var dirMgrApproval = "";
//	var deptHeadApproval = "";
//	var bencoApproval = "";
	var htmlString = '<table class="table table-hover">';
	htmlString = htmlString
			+ '<input type="hidden" id="reqId" name="reqId" value=""></input>';
	htmlString = htmlString + '<thead class="thead-dark">';
	htmlString = htmlString + '<th scope="col">Request ID</th>';
	htmlString = htmlString + '<th scope="col">Event Type</th>';
	htmlString = htmlString + '<th scope="col">Completed</th>';
	htmlString = htmlString + '<th scope="col">Amount</th>';
	htmlString = htmlString + '<th scope="col">Status</th>';
	htmlString = htmlString + '</thead>';
	htmlString = htmlString + '<tbody>';

	for (var i = 0; i < requests.length; i++) {

		if (requests[i].directMgrApproval != 0) {
			dirMgrApproval = "Approved";
		} else {
			dirMgrApproval = "Pending";
		}

		if (requests[i].deptHeadApproval != 0) {
			deptHeadApproval = "Approved";
		} else {
			deptHeadApproval = "Pending";
		}

		if (requests[i].bencoApproval != 0) {
			bencoApproval = "Approved";
		} else {
			bencoApproval = "Pending";
		}

		htmlString = htmlString
				+ '<tr data-toggle="collapse" data-target="#moreinfo'
				+ requests[i].requestId + '" '
				+ 'aria-expanded="false" aria-controls="moreinfo'
				+ requests[i].requestId + '" >';
		htmlString = htmlString + '<td>' + requests[i].requestId + '</td>';
		htmlString = htmlString + '<td>' + requests[i].eventType + '</td>';
		
		let day = requests[i].reimbursementDate.dayOfMonth;
		let month = requests[i].reimbursementDate.monthValue; // Month is 0-indexed
		let year = requests[i].reimbursementDate.year;

		htmlString = htmlString + '<td>' + month +'/' + day + '/'+ year
				+ '</td>';
		htmlString = htmlString + '<td>' + requests[i].projectedReimbursement
				+ '</td>';
		htmlString = htmlString + '<td>' + requests[i].status + '</td>';
		htmlString = htmlString + '</tr>';

		htmlString = htmlString + '<tr class="collapse" id="moreinfo'
				+ requests[i].requestId + '">';
		htmlString = htmlString + '<td colspan="6">';
		htmlString = htmlString + '<div class="container">Description:</div>';
		htmlString = htmlString + '<div class="container">'
				+ requests[i].eventDescription + '</div>';
		htmlString = htmlString + '<br/>';
		htmlString = htmlString + '<div class="row">';
		

		 day = requests[i].eventStart.dayOfMonth;
		 month = requests[i].eventStart.monthValue; // Month is 0-indexed
		 year = requests[i].eventStart.year;
		htmlString = htmlString + '<div class="col-4">Event Start: '
				+ month +'/' + day + '/'+ year + '</div>';
		
		 day = requests[i].eventEnd.dayOfMonth;
		 month = requests[i].eventEnd.monthValue; // Month is 0-indexed
		 year = requests[i].eventEnd.year;
		htmlString = htmlString + '<div class="col-4">Event End: '
				+ month +'/' + day + '/'+ year + '</div>';
		htmlString = htmlString + '</div>';
		htmlString = htmlString + '<br/>';
		htmlString = htmlString + '<div class="row">';
		htmlString = htmlString + '<div class="col-4">Direct Manager: '
				+ dirMgrApproval + '</div>';
		htmlString = htmlString + '<div class="col-6">Department Head: '
				+ deptHeadApproval + '</div>';
		htmlString = htmlString + '<div class="col-4">BenCo: ' + bencoApproval
				+ '</div>';
		htmlString = htmlString + '</div>';
		htmlString = htmlString + '<br/>';
		htmlString = htmlString + '<div class="row">';
		htmlString = htmlString + '<div class="col-8"></div>';
		htmlString = htmlString + '<div class="col-4" align="center">';
		htmlString = htmlString + '<input type="button" onClick="onClick('
				+ requests[i].requestId
				+ ');" class="btn btn-outline-dark" value="View More"></input>';
		htmlString = htmlString + '</div>';
		htmlString = htmlString + '</div>';
		htmlString = htmlString + '</td>';
		htmlString = htmlString + '</tr>';

	}

	htmlString = htmlString + '</table>';

	var divContainer = document.getElementById("requests");
	divContainer.innerHTML = "";
	divContainer.innerHTML = htmlString;

}
