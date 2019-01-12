window.onload = function(){
	console.log("Pending.js onload");
    getApplicationInfo();
}

function getApplicationInfo() {
	console.log("Pending.js getAppInfo");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let session = JSON.parse(xhttp.responseText);
            
            //alert(session.management.firstName);
            document.getElementById("firstname").value = session.employee.firstName;
            document.getElementById("managerfirstname").value = session.manager.firstName;
            document.getElementById("lastname").value = session.employee.lastName;
            document.getElementById("managerlastname").value = session.manager.lastName;
            
            console.log("session=" + xhttp.responseText);
        
        }
    }
    xhttp.open("GET", 'http://localhost:8080/TRMS/html/ApplicationJSON.do', true);
    xhttp.send();
}

