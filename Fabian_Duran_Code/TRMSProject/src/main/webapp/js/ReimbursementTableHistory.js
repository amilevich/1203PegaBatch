window.onload = function () {
    getAuthentication();
    
    getAuthentication2();
}

function getAuthentication() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let user = JSON.parse(xhttp.responseText);
            if(user.length != null)
            fillTable(user);

        }
    }
    xhttp.open("GET", 'http://localhost:8080/TRMSProject/html/EmpJSON.do', true);
    xhttp.send();
}

function fillTable(user) {

    var size = user.length; //this will be the size of the array in java
    for (var r = 0; r < size; r += 1) {
        var record = document.getElementById("insertHistoryRei").insertRow(r);

        for (var c = 0; c < 5; c += 1) {
            var y = record.insertCell(c);
        }
        var table = document.getElementById("insertHistoryRei"),
            reID = user[r].rei_id,
            awardRequested = user[r].awardRequested,
            awardGranted = user[r].awardGranted,
            submissionDate = user[r].submissionDate,
            completionDate = user[r].completeDate;

        
        table.rows[r].cells[0].innerHTML = reID;
        table.rows[r].cells[1].innerHTML = awardRequested;
        table.rows[r].cells[2].innerHTML = awardGranted;
        table.rows[r].cells[3].innerHTML = submissionDate;
        table.rows[r].cells[4].innerHTML = completionDate;
    }
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
	
    if (user.empType == "employee") {
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