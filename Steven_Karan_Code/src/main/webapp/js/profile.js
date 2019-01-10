
 window.onload=function(){
 	getEmployee();
 	getEmployeePersonalInfo();

 	document.getElementById('edit').onclick = edit;

 	// JQuery that selets the element with 'edit' id and enables the tooltip 
 	$('#edit').tooltip();
 	 getAlert();
 }

 // Ajax call to get employee info:
 function getEmployeePersonalInfo() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {	
			let emp = JSON.parse(xhttp.responseText);
			setValues(emp);
		}
	};
	xhttp.open("GET", 'http://localhost:9000/ReimbursementSystem/html/empJSON.do',true);
	xhttp.send();
}

function setValues(emp) {
	if(emp!=null){
		// populate employee personal detail fields with the variables in the emp object
		document.getElementById('emp-user-name').value=emp.username;
		document.getElementById('emp-email').value=emp.email;
		

	}else{
		let alert_html = '<div class="alert alert-danger" role="alert">Error loading profile. Please <a href="http://localhost:9000/ReimbursementSystem/html/login.html">relog.</a></div>';
		document.getElementById("alert").innerHTML= alert_html;
	}

}


/*
 * function that allows the user to edit the values in the initially readonly fields
 */
function edit(){
	document.getElementById('emp-user-name').removeAttribute('readonly');
	document.getElementById('emp-email').removeAttribute('readonly');
	document.getElementById('password').removeAttribute('readonly');
	document.getElementById('confirm-password').removeAttribute('readonly');
	document.getElementById('new-password').removeAttribute('readonly');
	document.getElementById('new-conf-password').removeAttribute('readonly');


	// show submit button
	document.getElementById('submit').removeAttribute('disabled');
}