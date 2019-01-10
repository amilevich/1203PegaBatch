/**
 * 
 */

window.onload = function() {
	getEmployeeInfo();
	getRequest();

}

function getEmployeeInfo() {
	console.log("IN getEmployeeInfo()");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			employee = JSON.parse(xhttp.responseText);
			console.log("employee=" + xhttp.responseText);
			setEmployeeValues(employee);
		}
	}
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/EmployeeJSON.do', true);
	xhttp.send();
}

function setEmployeeValues(employee) {
	document.getElementById("empId").value = employee.employeeId;
	// document.getElementById("firstName").innerHTML = employee.firstName;
	// document.getElementById("lastName").innerHTML = employee.lastName;
}

function onClick(id) {
	document.getElementById("myForm").submit();
}

function onClickCancel() {
	var x;
	if (confirm("Are you sure you want to 'Cancel' this reimbursement request?") == true) {
		// document.getElementById("requestId").value = request.requestId;

		document.getElementById("details-form").action = "CancelRequest.do";
		document.getElementById("details-form").submit();
	} else {
		x = "You pressed Cancel!";
	}
	return x;

}

function onClickBack() {
	document.getElementById("details-form").action = "home.do";
	document.getElementById("details-form").submit();
}

function onClickHome() {
	console.log("onClickHome");
	document.getElementById("details-form").action = "home.do";
	document.getElementById("details-form").submit();
}

function onClickApprove() {
	console.log(" reqId=" + document.getElementById("requestId").value);
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			// let x = JSON.parse(xhttp.responseText);
			console.log("Mgr responseText=" + xhttp.responseText);
			alert("Reimbursement Request has been Approved.");
		}
	}
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/Approval.do?requestId='
			+ document.getElementById("requestId").value, true);
	xhttp.send();
}

function onClickDeny() {
	console.log(" reqId=" + document.getElementById("requestId").value);
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			// let x = JSON.parse(xhttp.responseText);
			console.log("Mgr responseText=" + xhttp.responseText);
			alert("Reimbursement Request has been Denied.");
		}
	}
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/Deny.do?requestId='
			+ document.getElementById("requestId").value, true);
	xhttp.send();
}

function getRequest() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let request = JSON.parse(xhttp.responseText);
			document.getElementById("requestId").value = request.requestId;
			console.log("request=" + xhttp.responseText);
			setRequestValues(request);
		}
	}
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/RequestJSON.do', true);
	xhttp.send();
}

function setRequestValues(request) {

	var day;
	var month;
	var year;

	document.getElementById("employeeId").innerHTML = request.employeeId;

	day = request.reimbursementDate.dayOfMonth;
	month = request.reimbursementDate.monthValue; // Month is 0-indexed
	year = request.reimbursementDate.year;
	document.getElementById("dateCompleted").innerHTML = month + "/" + day
			+ "/" + year;

	document.getElementById("firstName").innerHTML = request.employeeFirstName;
	document.getElementById("lastName").innerHTML = request.employeeLastName;
	document.getElementById("eventType").innerHTML = request.eventType;
	document.getElementById("eventCost").innerHTML = request.eventCost;
	document.getElementById("reimbCoverage").innerHTML = request.reimbCoverage;

	day = request.eventStart.dayOfMonth;
	month = request.eventStart.monthValue; // Month is 0-indexed
	year = request.eventStart.year;
	document.getElementById("eventStart").innerHTML = month + '/' + day + '/'
			+ year;

	day = request.eventEnd.dayOfMonth;
	month = request.eventEnd.monthValue; // Month is 0-indexed
	year = request.eventEnd.year;
	document.getElementById("eventEnd").innerHTML = month + '/' + day + '/'
			+ year;
	
	

	document.getElementById("eventTime").innerHTML = request.eventTime;
	document.getElementById("eventDescription").innerHTML = request.eventDescription;
	document.getElementById("justification").innerHTML = request.justification;
	document.getElementById("gradeFormat").innerHTML = request.gradeType;
	document.getElementById("gradeCutoff").innerHTML = request.passingGrade;
	document.getElementById("streetAddress").innerHTML = request.streetAddress;
	// document.getElementById("city").innerHTML = request.city;
	// document.getElementById("state").innerHTML = request.state;
	// document.getElementById("zipCode").innerHTML = request.zipCode;
	document.getElementById("cityStateZip").innerHTML = request.city + ", "
			+ request.state + "   " + request.zipCode;
	document.getElementById("country").innerHTML = request.country;

	var cancelBtn = document.getElementById("cancelBtn");
	if ((request.status == "Cancelled") || (request.status == "Approved")) {
		cancelBtn.style.display = "none";
	}

	var empEmpId = document.getElementById("empId").value;
	var reqEmpId = document.getElementById("employeeId").innerHTML;

	console.log("emp=" + empEmpId + "req=" + reqEmpId);

	var approveBtn = document.getElementById("approveBtn");
	var denyBtn = document.getElementById("denyBtn");
	if (empEmpId == reqEmpId) {
		approveBtn.style.display = "none";
		denyBtn.style.display = "none";
	}

}

function viewPreApprovalFile() {
	if (request.approvalAttachmentname != null) {
		document.getElementById("viewPreApprovalFile").href = request.approvalAttachmentname;
	}

}

function viewEventFile() {
	if (request.approvalAttachmentname != null) {
		document.getElementById("viewEventFile").href = request.eventFilename;
	}

}
