window.onload = function () {
    getAuthentication();

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
    document.getElementById("reID").value = user[r].rei_id;
    document.getElementById("empID").value = user[r].emp_id;
    document.getElementById("firstName").value = user[r].fName;// need this
    document.getElementById("lastName").value = user[r].lName;// need this
    document.getElementById("eventType").value = user[r].rei_type;
    document.getElementById("eventLocation").value = user[r].eventLocation;// need this
    document.getElementById("eventDate").value = user[r].eventStartDate;
    document.getElementById("eventTime").value = user[r].eventTime; // need this
//    document.getElementById("timeFrame").value =;
    document.getElementById("comment").value =  user[r].description;
    document.getElementById("requestedAmount").value = user[r].awardRequested;
//    document.getElementById("totalAward").value =;
}
