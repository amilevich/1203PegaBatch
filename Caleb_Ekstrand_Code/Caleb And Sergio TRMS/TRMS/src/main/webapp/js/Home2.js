/**
 * 
 */
window.onload = function(){
		//console.log("Hey what up? you crazy yet?");
		getLoginInfo();
}
function getLoginInfo() {
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			let user = JSON.parse(xhttp.responseText);
			setValues(user);
			console.log(user);
		}
	}
	
	xhttp.open("GET", 'http://localhost:8080/TRMS/html/Home.do', true);
	xhttp.send();
	
}
function setValues(user) {
	document.getElementById("myHeader").innerHTML = "Welcome " + user.firstName;
	document.getElementById("myHeader").innerHTML += " " + user.lastName;
	for (i in user.tickets) {
		let current = user.tickets[i];
		let tickets = document.getElementById("tickets");
		let row = document.createElement("TR");
		for (j in current) {
			if (j != "userId" && j != "pictures") {
				let td = document.createElement("TD");
				td.innerHTML = current[j];
				row.append(td);
			}
		}
		let td = document.createElement("TD");
		let a = document.createElement("A");
		let q = document.createAttribute("id");
		let h = document.createAttribute("href");
		q.value = "pic";
		if(current.pictures[0]){
			h.value = current.pictures[0].aBase64;
			a.setAttributeNode(h);
			a.innerHTML = "Preview In Browser";
		} else {
			a.innerHTML = "No Preview Available";
		}
		a.setAttributeNode(q);
		td.append(a);
		row.append(td);
		td = document.createElement("TD");
		let button = document.createElement("BUTTON");
		let c = document.createAttribute("class");
		c.value = "style";
		button.setAttributeNode(c);
		let n = document.createAttribute("id");
		n.value = current["ticketId"] + " cancel";
		button.setAttributeNode(n);
		let func = document.createAttribute("onClick");
		func.value = "cancel(" + current["ticketId"]+ ")";
		button.setAttributeNode(func);
		button.innerHTML = "Cancel";
		td.append(button);
		row.append(td);
		tickets.append(row);
		/*
		document.getElementById("tickets").innerHTML += "<br>";
		let b = document.createElement("BUTTON");
		let c = document.createAttribute("class");
		c.value = "btn btn-primary btn-lg";
		b.setAttributeNode(c);
		c= document.createAttribute("id");
		c.value = current["ticketId"];
		b.setAttributeNode(c);
		document.getElementById("tickets").append(b);
		document.getElementById(current["ticketId"]).innerHTML = "Ticket Actions";
		document.getElementById("tickets").innerHTML += "<br>";
		*/
	}
	for (i in user.ticketsToApprove) {
		let current = user.ticketsToApprove[i];
		let tickets = document.getElementById("ticketsToApprove");
		let row = document.createElement("TR");
		for (j in current) {
			if (j != "userId") {
				let td = document.createElement("TD");
				td.innerHTML = current[j];
				row.append(td);
			}
		}
		let td = document.createElement("TD");
		let a = document.createElement("A");
		let q = document.createAttribute("id");
		let h = document.createAttribute("href");
		q.value = "pic";
		h.value = "";
		a.innerHTML = "Preview In Browser";
		a.setAttributeNode(q);
		a.setAttributeNode(h);
		td.setAttributeNode(a);
		row.append(td);
		td = document.createElement("TD");
		let button = document.createElement("BUTTON");
		let c = document.createAttribute("class");
		c.value = "style";
		button.setAttributeNode(c);
		let n = document.createAttribute("id");
		n.value = current["ticketId"] + " approve";
		button.setAttributeNode(n);
		let func = document.createAttribute("onClick");
		func.value = "approve(" + current["ticketId"]+ ", '" + current["stage"] + "')";
		button.setAttributeNode(func);
		button.innerHTML = "Approve";
		td.append(button);
		
		button = document.createElement("BUTTON");
		c = document.createAttribute("class");
		c.value = "style";
		button.setAttributeNode(c);
		n = document.createAttribute("id");
		n.value = current["ticketId"] + " deny";
		button.setAttributeNode(n);
		func = document.createAttribute("onClick");
		func.value = "deny(" + current["ticketId"]+")";
		button.setAttributeNode(func);
		button.innerHTML = "Deny";
		td.append(button);
		
		row.append(td);
		tickets.append(row);
		/*
		let current = user.ticketsToApprove[i];
		for (x in current) {
			document.getElementById("ticketsToApprove").innerHTML += x + ": " + current[x] + ", ";
		}
		document.getElementById("ticketsToApprove").innerHTML += "<br>";
		let b = document.createElement("BUTTON");
		let c = document.createAttribute("class");
		c.value = "btn btn-primary btn-lg";
		b.setAttributeNode(c);
		c = document.createAttribute("id");
		c.value = current["ticketId"] + " approve";
		b.setAttributeNode(c);
		c = document.createAttribute("onClick");
		c.value = "approve(" + current["ticketId"]+ ", '" + current["stage"] + "')";
		b.setAttributeNode(c);
		document.getElementById("ticketsToApprove").append(b);
		document.getElementById(current["ticketId"] + " approve").innerHTML = "Approve";
		document.getElementById("ticketsToApprove").innerHTML += "<br>";
		*/
	}
}
function approve(id, stage) {
	console.log("In approve");
	let data = "id=" + id + "&stage=" + stage;
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			let elem = document.getElementById(id+ " approve").parentNode.parentNode;
			elem.parentNode.removeChild(elem);
		}
	}
	xhr.open("POST", 'http://localhost:8080/TRMS/html/Approve.do', true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.send(data);
	
}
function deny(id) {
	let data = "id=" + id;
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			let elem = document.getElementById(id+ " deny").parentNode.parentNode;
			elem.parentNode.removeChild(elem);
		}
	}
	xhr.open("POST", 'http://localhost:8080/TRMS/html/Deny.do', true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.send(data);
}
function cancel(id){
	let data = "id=" + id;
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			let elem = document.getElementById(id+ " cancel").parentNode.parentNode;
			elem.parentNode.removeChild(elem);
		}
	}
	xhr.open("POST", 'http://localhost:8080/TRMS/html/Cancel.do', true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.send(data);
}