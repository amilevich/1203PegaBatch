window.onload = function () {
    getAuthentication2();
    getAuthentication3();
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

function getAuthentication3() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let notify = JSON.parse(xhttp.responseText);
            getNotifications(notify);

        }
    }
    xhttp.open("GET", 'http://localhost:8080/TRMSProject/html/Notification.do', true);
    xhttp.send();
}



function getNotifications(notify) {
    var size = notify.length;
    for (var r = 0; r < size; r += 1) {
        var record = document.getElementById("insertNoti").insertRow(r);

        for (var c = 0; c < 2; c += 1) {
            var y = record.insertCell(c);
        }
        var table = document.getElementById("insertNoti"),
            reID = notify[r].rei_id,
            msg = notify[r].request; // tell what the correct one is

        table.rows[r].cells[0].innerHTML = reID;
        table.rows[r].cells[1].innerHTML = msg;
    }
}
