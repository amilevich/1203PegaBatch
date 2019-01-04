/**
 * 
 */
/*
 * $('#requesAddtInfo').on('show.bs.modal', function (event) { var button =
 * $(event.relatedTarget) // Button that triggered the modal var recipient =
 * button.data('whatever') // Extract info from data-* attributes // If
 * necessary, you could initiate an AJAX request here (and then do the updating
 * in a callback). // Update the modal's content. We'll use jQuery here, but you
 * could use a data binding library or other methods instead. })
 */
window.onload = function() {
	console.log('in windows.onload');
	getReimbursementForEmployee();
};

function getReimbursementForEmployee() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState === 4 && xhttp.status === 200) {
			let reimbList = JSON.parse(xhttp.responseText);
			setValues(reimbList);
		} else {
			console.log('rdy: ' + xhttp.readyState + 'status: ' + xhttp.status);
		}
	};
	xhttp.open('GET',
			'http://localhost:9000/ReimbursementSystem/html/emp-listJSON.do',
			true);
	xhttp.send();
}
function setValues(reimbList) {
	if (reimbList !== null) {
		for (let x = 0; reimbList.length > x; x++) {
			let new_id = "";
			// let element = document.getElementById("Employee");
			// element.parentNode.removeChild(element);
			// Reimbursement header
			document.getElementById("reimb-id" + new_id).innerText = "ID: "
					+ reimbList[x].reimb_id;
			let rStatus = reimbList[x].status_name;
			if (!rStatus) {
				document.getElementById("reimb-status" + new_id).innerText = "Status: Saved";
			} else {
				document.getElementById("reimb-status" + new_id).innerText = "Status: "
						+ rStatus;
			}
			document.getElementById("reimb-date" + new_id).innerText = " Date: "
					+ reimbList[x].event.start_date.dayOfMonth
					+ '/'
					+ reimbList[x].event.start_date.month
					+ '/'
					+ reimbList[x].event.start_date.year;
			// Event

			document.getElementById("event-type" + new_id).value = reimbList[x].event.type_name;
			document.getElementById("event-cost" + new_id).value = reimbList[x].event.cost
					.toFixed(2);
			document.getElementById("event-date" + new_id).value = reimbList[x].event.start_date.dayOfMonth
					+ '/'
					+ reimbList[x].event.start_date.month
					+ '/'
					+ reimbList[x].event.start_date.year;
			document.getElementById("event-time" + new_id).value = new Date(
					reimbList[x].event.start_time).toTimeString().substr(0, 9);
			document.getElementById("grade-format" + new_id).value = reimbList[x].event.format_name;
			document.getElementById("passing-grade" + new_id).value = reimbList[x].event.passing_grade;
			// Event Location
			document.getElementById("location-text" + new_id).value = reimbList[x].event.location.address_text;
			document.getElementById("street-num" + new_id).value = reimbList[x].event.location.street_number;
			document.getElementById("route" + new_id).value = reimbList[x].event.location.route;
			document.getElementById("city" + new_id).value = reimbList[x].event.location.city;
			document.getElementById("country" + new_id).value = reimbList[x].event.location.country;
			document.getElementById("state" + new_id).value = reimbList[x].event.location.state;
			document.getElementById("zipcode" + new_id).value = reimbList[x].event.location.state;
			// Reimbursement Details
			document.getElementById("justification" + new_id).value = reimbList[x].justification;
			document.getElementById("description" + new_id).value = reimbList[x].event.description;
			document.getElementById("work-missed" + new_id).value = reimbList[x].work_time_missed;
			
			new_id = x + 1;	
			cloneAccordion(new_id, reimbList.length);
		}
	}
}

function cloneAccordion(id, len){
	if(id!==len){
		let reimb_col = document.getElementById("accordionReimb");
		let reimb_next_col = reimb_col.cloneNode(true);
		let children = reimb_next_col.children;
		reimb_next_col.id = reimb_col.id + id;
		reimb_col.parentNode.append(reimb_next_col);
		for (let y = 0; y < children.length; y++) {
			children[y].id += id
		}
	}
}