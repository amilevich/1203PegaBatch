/**
 * 
 */

window.onload = function() {
	console.log('in windows.onload');
	getReimbursementPersonal();
	document.getElementById("assigned_list").addEventListener("click", assignedList, false);
	document.getElementById("personal_list").addEventListener("click", personalList, false);
	getEmployee();
	getAlert();
};

function personalList() {
	console.log('in personal list event');
	var parent = document.getElementById("accordionReimb");
	removeAllChildNode(parent);
	parent.innerHTML = '<div class="text-center">'+
							'<div class="spinner-border text-center"'+
								'style="width: 5rem; height: 5rem;" role="status">+'
								'<span class="sr-only">Loading...</span>'+
							'</div>'+
						'</div>';
	getReimbursementPersonal();
	getAlert();
}

function getReimbursementPersonal() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState === 4 && xhttp.status === 200) {
			let reimbList = JSON.parse(xhttp.responseText);
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
	console.log(reimbList);
	if (reimbList.length==0) {
		console.log("No Reimbursements found!")
		view = "No tuition reimbursements found!";
	}
	else {
		for (let row = 0; reimbList.length > row; row++) {
			view += createReimbursementView(reimbList[row], row, assigned);
		}	
	}
	document.getElementById("accordionReimb").innerHTML = view;
}

function personalListAction(reimbursement_status, id, date, format_name){
	if (!reimbursement_status) {
		
	}
	else if (reimbursement_status === "Pending Direct Supervisor Approval" ||
		reimbursement_status === "Pending Department Head Approval" ||
		reimbursement_status === "Pending Benifits Coordinator Approval" ||
		reimbursement_status === "Pending Direct Supervisor Confirmation" ||
		reimbursement_status === "Pending Benifits Coordinator Confirmation") {
		return '';
	}
		
	else if (reimbursement_status === "Pending Employee Approval") {
		return '<button type="submit" id="ApproveButton'+id+'" name="action" class="btn btn-primary btn-lg btn-block" value="accept">Approve</button>' +
		'<button type="submit" id="DenyButton'+id+'" name="action" class="btn btn-warning btn-lg btn-block" value="cancel">Deny</button>';
}
		
	else if (reimbursement_status === "Pending Additional Information" && !appeal) {
		return '<button type="submit" id="PetitionButton'+id+'" name="action" class="btn btn-danger btn-lg btn-block" value="petition">Submit Additional Information</button>';
	}
	
	else if (reimbursement_status === "Pending Additional Information"){
		return '<button type="submit" id="SubmitButton'+id+'" name="action" value="info" class="btn btn-danger btn-lg btn-block" value="submit">Submit Additional Information</button>';
	}
	
	else if (reimbursement_status === "Pending Employee Grading/Presentation"){
		let today = new Date();
		if(date.year <= today.getFullYear() && 
				date.monthValue <= today.getMonth() + 1 && 
				date.dayOfMonth < today.getDate()){
			return '<div class="form-row form-group form-inline">'+
						'<div class="col-lg-1 col-md-2 col-sm-12">'+
							'<label class="inline-label" for="grade-format">Grade '+
							'Format</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-4 col-sm-12">'+
							'<!-- type -->'+
							'<input class="form-control full-width" id="grade-format'+id+'"'+
								'value="'+format_name+'" name="'+format_name+'" readonly>'+
							'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-12 offset-lg-1 offset-md-1">'+
							'<label class="inline-label" for="passing-grade">Passing'+
								' Grade</label>'+
							'</div>'+
						'<div class="col-lg-2 col-md-2 col-sm-12">'+
							'<input class="form-control" name="passing-grade" id="passing-grade'+id+'" ">'+
						'</div>'+
					'</div>'+
					'<button type="submit" id="SendButton'+id+'" name="action" value="grade" class="btn btn-success btn-lg btn-block">Send</button>'+
					'<button type="button" class="btn btn-info btn-lg btn-block" name="action" data-toggle="modal" data-target="#requesAddtInfo" data-whatever="@mdo">'+
					'Upload Attachment</button>';
		}
		else
			return '';
	}
	else 
		return '';
}