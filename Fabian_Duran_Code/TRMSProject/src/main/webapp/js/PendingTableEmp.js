// for the pending
window.onload = function () {
    getAuthentication();
    getAuthentication2();
    getAuthentication3();
}

function getAuthentication() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {

            let user = JSON.parse(xhttp.responseText);
            fillTable(user);

        }
    }
    xhttp
        .open("GET", 'http://localhost:8080/TRMSProject/html/EmpJSON.do',
            true);
    xhttp.send();

}

function fillTable(user) {

    var size = user.length; // this will be the size of the array in java
    for (var r = 0; r < size; r += 1) {
        var record = document.getElementById("insertPendingRei").insertRow(r);

        for (var c = 0; c < 7; c += 1) {
            var y = record.insertCell(c);
        }
        var table = document.getElementById('insertPendingRei'),
            reID = user[r].rei_id,
            eventType = user[r].rei_type,
            eventDate = user[r].eventStartDate,
            eventSubmission = user[r].submissionDate,
            eventStatus = user[r].rei_state,
            eventAward = user[r].awardRequested;


        table.rows[r].cells[0].innerHTML = reID;
        table.rows[r].cells[1].innerHTML = eventType;
        table.rows[r].cells[2].innerHTML = eventDate;
        table.rows[r].cells[3].innerHTML = eventSubmission;
        table.rows[r].cells[4].innerHTML = eventStatus;
        table.rows[r].cells[6].innerHTML = eventAward;
        if (eventStatus == "Waiting on Response") {
            table.rows[r].cells[5].innerHTML = '<form action="AdditionalForm.do" post="GET"><input type="hidden" id="name" name="name" value="' + reID + '"><input class="table-button-only" type="submit" id="' +
                r + '" name="Rev' + reID + '" value="Additional Info"></form>';

        } else if (eventStatus == "Waiting Event Completion") {
            table.rows[r].cells[5].innerHTML = '<form action="Completion.html" ><input type="hidden" id="name" name="name" value="' + reID + '"><input class="table-button-only" type="submit" id="' +
                r + '" name="Rev' + reID + '" value="Completion Form"></form>';

        } else if (eventStatus == "Change In Reward") {
            table.rows[r].cells[5].innerHTML = '<form action="AcceptChange.do"><input type="hidden" id="name" name="name" value="' + reID + '"><input class="table-button-only" type="submit" id="' +
                r + '" name="Rev' + reID + '" value="Accept"></form> <form action="CancelRei.do"><input type="hidden" id="name" name="name" value="' + reID + '"><input class="table-button-only" type="submit" id="' +
                r + '" name="Rev' + reID + '" value="Cancel"></form>';
        } else {
            table.rows[r].cells[5].innerHTML = '<form action="CancelRei.do" post="POST" ><input type="hidden" id="name" name="name" value="' + reID + '"><input class="table-button-only" type="submit" id="' +
                r + '" name="Rev' + reID + '" value="Cancel"></form>';
        }

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
            
            getNotificationsSize(notify);

        }
    }
    xhttp.open("GET", 'http://localhost:8080/TRMSProject/html/Notification.do', true);
    xhttp.send();
}



function getNotificationsSize(notify) {
    var size = notify.length;
    if (size > 0){
                document.getElementById("addNoti").innerHTML = '<div class="alert alert-success alert-dismissible"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>You got <a class="nav-link" href="../html/Notification.html" id="notifications">Notifications</a>.</div>';
    }
}
