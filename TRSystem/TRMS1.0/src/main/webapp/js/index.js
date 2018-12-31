window.onload = function(){
	getEmployee2Info();
}

function getEmployee2Info() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let employee2 = JSON.parse(xhttp.responseText);
			setValues(employee2);
		}
	}
	xhttp.open("GET", 'http://localhost:9005/TRMS/html/Employee2JSON.do', true);
	xhttp.send();
}

function setValues(employee2) {
	console.log("here");
	document.getElementById("screenname").innerHTML = "Screenname: " + employee2.screenname;
	document.getElementById("passwd").innerHTML = "Password: " + employee2.passwd;
	
}