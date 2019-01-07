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

function assignedList() {
	console.log('in assigned list event');
	var parent = document.getElementById("accordionReimb");
	removeAllChildNode(parent);
	getReimbursementAssigned();
	getAlert();
}

function personalList() {
	console.log('in personal list event');
	var parent = document.getElementById("accordionReimb");
	removeAllChildNode(parent);
	getReimbursementPersonal();
	getAlert();
}

function getReimbursementAssigned(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (xhttp.readyState === 4 && xhttp.status === 200){
			let reimbList = JSON.parse(xhttp.responseText);
			console.log(xhttp.responseText);
			setValues(reimbList);
		} else {
			console.log('rdy: ' + xhttp.readyState + 'status: ' + xhttp.status);
		}
	};
	xhttp.open('GET',
			'http://localhost:9000/ReimbursementSystem/html/assignment-listJSON.do',
			true);
	xhttp.send();
}

function getReimbursementPersonal() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState === 4 && xhttp.status === 200) {
			let reimbList = JSON.parse(xhttp.responseText);
			console.log(xhttp.responseText);
			setValues(reimbList);
		} else {
			console.log('rdy: ' + xhttp.readyState + 'status: ' + xhttp.status);
		}
	};
	xhttp.open('GET',
			'http://localhost:9000/ReimbursementSystem/html/personal-listJSON.do',
			true);
	xhttp.send();
}


function setValues(reimbList) {
	let view = "";
	console.log("in set values function.");
	if (reimbList) {
		for (let row = 0; reimbList.length > row; row++) {
			console.log("read rows in json");
			view += createReimbursementView(reimbList[row], row);
		}
		document.getElementById("accordionReimb").innerHTML = view;
	}
	else {
		console.log("No Reimbursements found!")
		let row = document.createElement("div");
		row.innerHTML = "No tuition reimbursements found!";
		document.getElementById("accordionReimb").appendChild(row);
	}
}
function createReimbursementView(reimb,id){
	console.log("in create reimb view method.")
	
	
	//employee info
	let firstname = reimb.employee.firstname;
	let lastname = reimb.employee.lastname;
	let email = reimb.employee.email;
	let department = reimb.employee.department;
	let position = reimb.employee.position;
	let available_funds = reimb.employee.available_funds;
	
	let reimb_id = "ID: " + reimb.reimb_id;
	let reimbursement_status = reimb.status_name;
	
	// event
	let event_start_date = reimb.event.start_date.dayOfMonth + '/'
			+ reimb.event.start_date.month + '/' + reimb.event.start_date.year;
	
	let event_start_time = new Date(reimb.event.start_time).toTimeString()
			.substr(0, 9);
	let event_type = reimb.event.type_name;
	let event_cost = reimb.event.cost.toFixed(2);
	let format_name = reimb.event.format_name;
	let passing_grade = reimb.event.passing_grade;

	// location
	let address_text = reimb.event.location.address_text;
	let street_number = reimb.event.location.street_number;
	let route = reimb.event.location.route;
	let city = reimb.event.location.city;
	let state = reimb.event.location.state;
	let zipcode = reimb.event.location.zipcode;
	let country = reimb.event.location.country;

	// details
	let justification = reimb.justification;
	let description = reimb.event.description;
	let work_time_missed = reimb.work_time_missed;
	
	return '<div class="card" id="row">'+
		'<div class="card-header" id="heading" data-toggle="collapse"'+
			'data-target="#collapse'+id+'">'+
			'<div class="navbar">'+
				'<h5>'+
					'<a class="nav-item" id="reimb-id'+id+'">'+reimb_id+'</a>'+
				'</h5>'+
				'<div class="nav-item" id="reimb-status'+id+'">'+
					'<h5>'+reimbursement_status+'</h5>'+
				'</div>'+
				'<div class="my-2 my-lg-0">'+
					'<h5 id="reimb-date'+id+'">'+event_start_date+'</h5>'+
				'</div>'+
			'</div>'+
		'</div>'+
		'<div id="collapse'+id+'" class="collapse" aria-labelledby="heading"'+
			'data-parent="#accordionReimb">'+
			'<div class="card-body">'+
				'<div class="form-group">'+
				'<hr>'+
				'<h3>Personal Information</h3>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-1 col-md-1 col-sm-12">'+
							'<label class="inline-label" for="firstname">Firstname</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-5 col-sm-12">'+
							'<!-- type -->'+
							'<input class="form-control full-width" name="firstname"'+
							'value="'+firstname+'" id="firstname'+id+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-12 offset-lg-1 offset-md-1">'+
							'<label class="inline-label" for="lastname">Lastname</label>'+
						'</div>'+
						'<div class="input-group col-lg-2 col-md-2 col-sm-12">'+
							'<div class="input-group-prepend">'+
								'<div class="input-group-text">$</div>'+
							'</div>'+
							'<input class="form-control" id="lastname'+id+'" value="'+lastname+'" readonly>'+
						'</div>'+
					'</div>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-1 col-md-1 col-sm-12">'+
							'<label class="inline-label" for="position'+id+'">Position</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-5 col-sm-12">'+
							'<input class="form-control full-width" id="position" value="'+position+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-12 offset-md-1 offset-lg-1">'+
						'<!-- time -->'+
							'<label class="inline-label" for="department">Department</label>'+
						'</div>'+
							'<div class="col-lg-2 col-md-2 col-sm-12">'+
								'<input class="form-control" id="department'+id+'" value="'+department+'" readonly>'+
						'</div>'+
					'</div>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-1 col-md-2 col-sm-12">'+
							'<label class="inline-label" for="email">Email'+
							'</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-4 col-sm-12">'+
						'<!-- type -->'+
							'<input class="form-control full-width" id="email'+id+'"'+
								'value="'+email+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-12 offset-lg-1 offset-md-1">'+
							'<label class="inline-label" for="available-funds">Available'+
								' Funds</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-2 col-sm-12">'+
							'<input class="form-control" id="available-funds'+id+'" value="'+available_funds+'" readonly>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="form-group">'+
					'<hr>'+
					'<h3>Event</h3>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-1 col-md-1 col-sm-12">'+
							'<label class="inline-label" for="event-type">Type</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-5 col-sm-12">'+
							'<!-- type -->'+
							'<input class="form-control full-width" name="event-type"'+
								'value="'+event_type+'" id="event-type'+id+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-12 offset-lg-1 offset-md-1">'+
							'<label class="inline-label" for="event-cost">Cost</label>'+
						'</div>'+
						'<div class="input-group col-lg-2 col-md-2 col-sm-12">'+
							'<div class="input-group-prepend">'+
								'<div class="input-group-text">$</div>'+
							'</div>'+
							'<input class="form-control" id="event-cost'+id+'" value="'+event_cost+'" readonly>'+
						'</div>'+
					'</div>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-1 col-md-1 col-sm-12">'+
							'<label class="inline-label" for="event-date'+id+'">Date</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-5 col-sm-12">'+
							'<input class="form-control full-width" id="event-date" value="'+event_start_date+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-12 offset-md-1 offset-lg-1">'+
							'<!-- time -->'+
							'<label class="inline-label" for="event-time">Time</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-2 col-sm-12">'+
							'<input class="form-control" id="event-time'+id+'" value="'+event_start_time+'" readonly>'+
						'</div>'+
					'</div>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-1 col-md-2 col-sm-12">'+
							'<label class="inline-label" for="grade-format">Grade '+
								'Format</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-4 col-sm-12">'+
							'<!-- type -->'+
							'<input class="form-control full-width" id="grade-format'+id+'"'+
								'value="'+format_name+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-12 offset-lg-1 offset-md-1">'+
							'<label class="inline-label" for="passing-grade">Passing'+
								' Grade</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-2 col-sm-12">'+
							'<input class="form-control" id="passing-grade'+id+'" value="'+passing_grade+'" readonly>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="form-group">'+
					'<hr>'+
					'<h3>Location</h3>'+
					'<div id="locationField" class="form-row form-group">'+
						'<input type="text" class="form-row form-group form-control"'+
							'value="'+address_text+'" id="location-text'+id+'" readonly>'+
					'</div>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-2 col-md-2 col-sm-6">'+
							'<label class="inline-label" for="street-num">Street'+
								'Number</label>'+
						'</div>'+
						'<div class="col-lg-3 col-md-4 col-sm-6">'+
							'<input type="text" id="street-num'+id+'"'+
								'value="'+street_number+'" class="form-control p full-width" readonly'+
							'>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-6">'+
							'<label class="inline-label" for="event-address-street">Street</label>'+
						'</div>'+
						'<div class="col-lg-3 col-md-3 col-sm-6">'+
							'<input type="text" id="route'+id+'" class="form-control p full-width"'+
								'value="'+route+'" readonly>'+
						'</div>'+
					'</div>'+
					'<div class="form-row form-group form-inline">'+
						'<div class="col-lg-2 col-md-2 col-sm-6">'+
							'<label class="inline-label" for="event-address-city">City</label>'+
						'</div>'+
						'<div class="col-lg-3 col-md-3 col-sm-6">'+
							'<input type="text" id="city'+id+'" class="form-control p full-width"'+
							'value="'+city+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-6">'+
							'<label class="inline-label" for="event-address-state">State</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-2 col-sm-6">'+
							'<input type="text" id="state'+id+'" class="form-control p full-width"'+
								'value="'+state+'" readonly>'+
						'</div>'+
						'<div class="col-lg-1 col-md-1 col-sm-6">'+
							'<label class="inline-label" for="event-address-zip">Zip'+
								'Code</label>'+
						'</div>'+
						'<div class="col-lg-2 col-md-2 col-sm-6">'+
							'<input type="text" id="zipcode'+id+'"'+
								'value="'+zipcode+'" class="form-control p full-width" readonly>'+
						'</div>'+
					'</div>'+
					'<div class="form-row">'+
						'<div class="col-lg-2 col-md-2 col-sm-6">'+
							'<label class="inline-label" for="event-address-country">Country</label>'+
						'</div>'+
						'<div class="col-sm-6">'+
							'<input type="text" id="country'+id+'" class="form-control p" value="'+country+'" readonly>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="form-group">'+
					'<hr>'+
					'<h3>Details</h3>'+
					'<div class="form-row">'+
						'<div class="col-lg-12 col-md-12 col-sm-12">'+
							'<!-- description -->'+
							'<label for="description">Description</label>'+
							'<textarea class="form-control" id="description'+id+'" readonly>'+
											description +'</textarea>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="form-group">'+
					'<div class="form-row">'+
						'<div class="col-lg-12 col-md-12 col-sm-12">'+
							'<!-- work related justification -->'+
							'<label for="justification">Justification</label>'+
							'<textarea class="form-control" id="justification'+id+'" readonly>'+
										justification +'</textarea>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<!-- optional fields -->'+
				'<div class="form-group">'+
					'<!-- work time missed (# of hours) -->'+
					'<div class="form-row">'+
						'<div class="col-lg-3 col-md-3 col-sm-12">'+
							'<label for="work-missed">Work Hours Missed</label>'+
						'<input value="'+ work_time_missed +'" class="form-control" id="work-missed'+id+'" readonly>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
			'<div class="card-footer">'+
				'<div class="form-group container">'+
					'<!--<button type="button" class="btn btn-danger">Cancel</button>-->'+
					button_group +
				'</div>'+
			'</div>'+
		'</div>'+
	'</div>'+
'</div>'+
'</div>';
}

function reimbursePersonalListAction(reimbursement_status, id){
	if (!reimbursement_status) {
		reimbursement_status = "Status: Saved";
		return '<button type="button" id="SaveButton'+id+'" class="btn btn-success">Save</button>'+
			'<button type="button" id="SubmitButton'+id+'" class="btn btn-primary">Submit</button>';
	}
	else if (reimbursement_status == "Pending Direct Supervisor Approval" ||
		reimbursement_status == "Pending Department Head Approval" ||
		reimbursement_status == "Pending Benifits Coordinator Approval" ||
		reimbursement_status == "Pending Direct Supervisor Confirmation" ||
		reimbursement_status == "Pending Benifits Coordinator Confirmation") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '';
	}
		
	else if (reimbursement_status == "Pending Employee Approval") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="DenyButton'+id+'" class="btn btn-warning">Deny</button>'+
			'<button type="button" id="ApproveButton'+id+'" class="btn btn-primary">Approve</button>';
	}
		
	else if (reimbursement_status == "Pending Additional Information"){
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="SubmitButton'+id+'" class="btn btn-danger">Submit Additional Information</button>';
	}
	
	else if (reimbursement_status == "Pending Employee Grading/Presentation"){																// option
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

function reimburseAssignedListAction(reimbursement_status, id){
	if (reimbursement_status == "Pending Direct Supervisor Approval" ||
		reimbursement_status == "Pending Department Head Approval") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="DenyButton'+id+'" class="btn btn-warning">Deny</button>'+
			'<button type="button" id="ApproveButton'+id+'" class="btn btn-primary">Approve</button>'+
			'<button type="button" class="btn btn-info" data-toggle="modal" data-target="#requesAddtInfo" data-whatever="@mdo">'+
			'Request Information</button>';
	}
		
	else if (reimbursement_status == "Pending Benifits Coordinator Approval") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="DenyButton'+id+'" class="btn btn-warning">Deny</button>'+
			'<button type="button" id="ApproveButton'+id+'" class="btn btn-primary">Approve</button>'+
			'<button type="button" class="btn btn-info" data-toggle="modal" data-target="#requesAddtInfo" data-whatever="@mdo">'+
			'Request Information</button>';
	}
		
	else if (reimbursement_status == "Pending Employee Approval") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '';
	}
		
	else if (reimbursement_status == "Pending Additional Information"){
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="SubmitButton'+id+'" class="btn btn-danger">Submit Additional Information</button>';
	}
	
	else if (reimbursement_status == "Pending Employee Grading/Presentation"){																// option
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
	else if (reimbursement_status == "Pending Direct Supervisor Confirmation" ||
			reimbursement_status == "Pending Benifits Coordinator Confirmation") {
		reimbursement_status = "Status: " + reimbursement_status;
		return '<button type="button" id="ConfirmButton'+id+'" class="btn btn-primary">Confirm</button>';
	}
	
	else if (reimbursement_status == "Funds Awarded"){
		reimbursement_status = "Status: " + reimbursement_status;
		return "";
	}
}

function removeAllChildNode(parent){
	parent.innerHTML = "";
}

