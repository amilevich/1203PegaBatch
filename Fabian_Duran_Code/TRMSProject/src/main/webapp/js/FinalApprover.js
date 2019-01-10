window.onload = function () {
    getAuthentication();
    getAuthentication2();
}

function getAuthentication() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let user = JSON.parse(xhttp.responseText);
            fillForm(user);

        }
    }
    xhttp.open("GET", 'http://localhost:8080/TRMSProject/html/EmpJSON.do', true);
    xhttp.send();
}


function fillForm(user) {
    var r = 0;
    var r = user[r].gradeID;
    
    document.getElementById("reID").value = user[r].rei_id;
    document.getElementById("empID").value = user[r].emp_id;
    document.getElementById("firstName").value = user[r].fName; // need this
    document.getElementById("lastName").value = user[r].lName; // need this
    switch (r){
        case 0:
            document.getElementById("eventGrade").value = "Letter Grade";
            break;
        case 1:
            document.getElementById("eventGrade").value = "Pass / Fail";
            break;
        case 2:
            document.getElementById("eventGrade").value = "Presentation";
            break;
        case 3:
            document.getElementById("eventGrade").value = "Other";
            break;
    }

//    document.getElementById("eventFile").innerHTML = user[r].eventLocation; // need this

}
function getAuthentication2() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let user = JSON.parse(xhttp.responseText);
            setName(user);
            determineUserNav(user);
            
        }
    }
    xhttp.open("GET", 'http://localhost:8080/TRMSProject/html/NavJSON.do', true);
    xhttp.send();
}

function setName(user) {
    document.getElementById("userName").innerHTML = user.fName;
}

function determineUserNav(user) {
	
    if (user.empType == "Employee") {
        document.getElementById("navbar-determined").innerHTML = '<a class="navbar-brand" href="../html/EmpHome.html">Fabian West Co.</a>';
        document.getElementById("navbar-determined0").innerHTML = '<li class="nav-item active"><a class="nav-link" href="../html/EmpHome.html">Home <span class="sr-only">(current)</span></a></li>';
        document.getElementById("navbar-determined1").innerHTML = '<li class="nav-item"><a class="nav-link" href="../html/History.html">History</a></li>';
    } else {

        document.getElementById("navbar-determined").innerHTML = '<a class="navbar-brand" href="../html/ApproversHome.html">Fabian West Co.</a>';
        document.getElementById("navbar-determined0").innerHTML = '<li class="nav-item active"><a class="nav-link" href="../html/ApproversHome.html">Home <span class="sr-only">(current)</span></a></li>';
        document.getElementById("navbar-determined2").innerHTML = '<li class="nav-item"><a class="nav-link" href="../html/Pending.html">Pending</a></li>';
        document.getElementById("navbar-determined3").innerHTML = '<li class="nav-item"><a class="nav-link" href="../html/History.html">History</a></li>';
    }
}