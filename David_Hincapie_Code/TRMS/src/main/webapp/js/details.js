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
	document.getElementById("firstName").innerHTML = employee.firstName;
	document.getElementById("lastName").innerHTML = employee.lastName;
}

function onClick(id) {
	document.getElementById("myForm").submit();
}

function onClickCancel() {
	var x;
    if (confirm("Are you sure you want to 'Cancel' this reimbursement request?") == true) {
//    	document.getElementById("requestId").value = request.requestId;
    
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
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/RequestJSON.do',true);
	xhttp.send();
}

function setRequestValues(request) {
	document.getElementById("employeeId").innerHTML = request.employeeId;
	document.getElementById("eventType").innerHTML = request.eventType;
	document.getElementById("eventCost").innerHTML = request.eventCost;
	document.getElementById("reimbCoverage").innerHTML = request.reimbCoverage;
	document.getElementById("eventStart").innerHTML = request.eventStart;
	document.getElementById("eventEnd").innerHTML = request.eventEnd;
	document.getElementById("eventDescription").innerHTML = request.eventDescription;
	document.getElementById("justification").innerHTML = request.justification;
	document.getElementById("streetAddress").innerHTML = request.streetAddress;
	document.getElementById("city").innerHTML = request.city;
	document.getElementById("state").innerHTML = request.state;
	document.getElementById("zipCode").innerHTML = request.zipCode;
	document.getElementById("country").innerHTML = request.country;
	
	var cancelBtn = document.getElementById("cancelBtn");
	if ( (request.status.substring(0,7) == "Cancel") || (request.status == "Approved") ) {
		cancelBtn.style.display = "none";
	}
}