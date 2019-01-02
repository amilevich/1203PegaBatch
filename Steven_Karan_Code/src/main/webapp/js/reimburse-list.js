/**
 * 
 *//*
	 * $('#requesAddtInfo').on('show.bs.modal', function (event) { var button =
	 * $(event.relatedTarget) // Button that triggered the modal var recipient =
	 * button.data('whatever') // Extract info from data-* attributes // If
	 * necessary, you could initiate an AJAX request here (and then do the
	 * updating in a callback). // Update the modal's content. We'll use jQuery
	 * here, but you could use a data binding library or other methods instead. })
	 */
window.onload = function(){
	console.log('in windows.onload');
	// getReimbursementForEmployee();
	setValues(JSON.parse('[{"reimb_id":1,"request_date":{"year":2018,"month":"DECEMBER","dayOfYear":364,"leapYear":false,"dayOfMonth":30,"dayOfWeek":"SUNDAY","era":"CE","chronology":{"id":"ISO","calendarType":"iso8601"},"monthValue":12},"justification":"Im too overworked, just give me some vacation time pleasees!","work_time_missed":400,"status_id":0,"status_name":null,"urgent":false,"sup_flag":false,"dept_flag":false,"benco_flag":false,"fund_awarded":0.0,"next_id":0,"emp_id":3,"event":{"event_id":1,"start_date":{"year":2018,"month":"DECEMBER","dayOfYear":364,"leapYear":false,"dayOfMonth":30,"dayOfWeek":"SUNDAY","era":"CE","chronology":{"id":"ISO","calendarType":"iso8601"},"monthValue":12},"start_time":1546223133392,"grade_received":null,"passing_grade":"C","description":"hello this a test.","cost":400.0,"type_name":"Seminars","coverage":0.0,"location":{"address_id":1,"address_text":"12007 Bruce B Downs Boulevard, Tampa, FL, USA","street_number":"12007","route":"Bruce B Downs Blvd","city":"Tampa","state":"FL","zipcode":"33612","country":"USA"},"format_name":"letter-grade","default_passing_grade":null}},{"reimb_id":45,"request_date":{"year":2019,"month":"JANUARY","dayOfYear":1,"leapYear":false,"dayOfMonth":1,"dayOfWeek":"TUESDAY","era":"CE","chronology":{"id":"ISO","calendarType":"iso8601"},"monthValue":1},"justification":"I want a job","work_time_missed":1000,"status_id":0,"status_name":null,"urgent":false,"sup_flag":false,"dept_flag":false,"benco_flag":false,"fund_awarded":0.0,"next_id":0,"emp_id":3,"event":{"event_id":24,"start_date":{"year":2019,"month":"JANUARY","dayOfYear":2,"leapYear":false,"dayOfMonth":2,"dayOfWeek":"WEDNESDAY","era":"CE","chronology":{"id":"ISO","calendarType":"iso8601"},"monthValue":1},"start_time":1546435800000,"grade_received":null,"passing_grade":"C","description":"Revature Training","cost":0.0,"type_name":"course","coverage":0.0,"location":{"address_id":45,"address_text":"3220 USF Banyan Circle, Tampa, FL, USA","street_number":"3220","route":"USF Banyan Circle","city":"Tampa","state":"FL","zipcode":"33613","country":"United States"},"format_name":"letter-grade","default_passing_grade":null}}]'));
};

function getReimbursementForEmployee(){
	console.log("In reimbursement list ajax request");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState==4 && xhttp.status==200){
			let reimbList = JSON.parse(xhttp.responseText);
			console.log(reimbList);
			setValues(reimbList);
		}
		else{
			console.log('rdy: ' + xhttp.readystate + 'status: ' + xhttp.status);
		}	
	};
	xhttp.open('GET', 'http://localhost:9000/ReimbursementSystem/html/reimburse-listJSON.do', true);
	xhttp.send();
}
function setValues(reimbList){
	if (reimbList != null) {
		// let element = document.getElementById("Employee");
		// element.parentNode.removeChild(element);
		

	}
}