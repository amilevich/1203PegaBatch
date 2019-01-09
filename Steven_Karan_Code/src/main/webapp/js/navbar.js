/**
 * 
 */
function getEmployee() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {	
			let emp = JSON.parse(xhttp.responseText);
			setNav(emp);
		}
	};
	xhttp.open("GET",
			'http://localhost:9000/ReimbursementSystem/html/empJSON.do',
			true);
	xhttp.send();
}

function setNav(emp) {
	if (emp != null) {
		document.getElementById("logout").removeAttribute('hidden');

		document.getElementById("reimb-nav").removeAttribute('hidden');

		document.getElementById("reimb-list-nav").removeAttribute('hidden');
	}
	else{
		document.getElementById("login").removeAttribute('hidden');
		document.getElementById("register").removeAttribute('hidden');
	}
}
