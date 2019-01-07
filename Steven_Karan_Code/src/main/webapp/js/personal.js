/**
 * 
 */

window.onload = function() {
	console.log('in windows.onload');
	getReimbursementPersonal();
	document.getElementById("assigned_list").addEventListener("click", assignedList, false);
	document.getElementById("personal_list").addEventListener("click", personalList, false);
	getAlert();
};

function personalList() {
	console.log('in personal list event');
	var parent = document.getElementById("accordionReimb");
	removeAllChildNode(parent);
	getReimbursementPersonal();
	getAlert();
}

function getReimbursementPersonal() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState === 4 && xhttp.status === 200) {
			let reimbList = JSON.parse(xhttp.responseText);
			console.log(xhttp.responseText);
			setPersonalList(reimbList);
		} else {
			console.log('rdy: ' + xhttp.readyState + 'status: ' + xhttp.status);
		}
	};
	xhttp.open('GET',
			'http://localhost:9000/ReimbursementSystem/html/personal-listJSON.do',
			true);
	xhttp.send();
}

function setPersonalList(reimbList) {
	let view = "";
	let assigned = false;
	if (reimbList) {
		for (let row = 0; reimbList.length > row; row++) {
			console.log("read rows in json");
			view += createReimbursementView(reimbList[row], row, assigned);
		}	
	}
	else {
		console.log("No Reimbursements found!")
		view = "No tuition reimbursements found!";
	}
	
	document.getElementById("accordionReimb").innerHTML = view;
}

function personalListAction(reimbursement_status, id){
	if (!reimbursement_status) {
		return '<button type="button" id="SaveButton'+id+'" class="btn btn-success">Save</button>'+
			'<button type="button" id="SubmitButton'+id+'" class="btn btn-primary">Submit</button>';
	}
	else if (reimbursement_status === "Pending Direct Supervisor Approval" ||
		reimbursement_status === "Pending Department Head Approval" ||
		reimbursement_status === "Pending Benifits Coordinator Approval" ||
		reimbursement_status === "Pending Direct Supervisor Confirmation" ||
		reimbursement_status === "Pending Benifits Coordinator Confirmation") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '';
	}
		
	else if (reimbursement_status === "Pending Employee Approval") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="DenyButton'+id+'" class="btn btn-warning">Deny</button>'+
			'<button type="button" id="ApproveButton'+id+'" class="btn btn-primary">Approve</button>';
	}
		
	else if (reimbursement_status === "Pending Additional Information"){
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="SubmitButton'+id+'" class="btn btn-danger">Submit Additional Information</button>';
	}
	
	else if (reimbursement_status === "Pending Employee Grading/Presentation"){																// option
		let today = new Date().toLocaleDateString("en-US");
		if(reimb.event.start_date.year <= today.getFullYear() && 
				reimb.event.start_date.monthValue <= today.getMonth && 
				reimb.event.start_date.dayOfMonth < today.getDate()){
			reimbursement_status = "Status: " + reimbursement_status;
			return '<button type="button" id="SendButton'+id+'" class="btn btn-success">Send</button>'+
				'<button type="button" class="btn btn-info" data-toggle="modal" data-target="#requesAddtInfo" data-whatever="@mdo">'+
				'Upload Attachment</button>';
		}
	}	
}
