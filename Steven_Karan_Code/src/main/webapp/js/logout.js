function logout(){
	console.log('logging out');
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			window.location='http://localhost:9000/ReimbursementSystem/html/login.html';
		}
	}
	xhttp.open("GET", 'http://localhost:9000/ReimbursementSystem/html/logout.do', true);
	xhttp.send();
}