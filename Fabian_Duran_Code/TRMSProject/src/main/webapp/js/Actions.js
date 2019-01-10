window.onload = function(){
	getAuthentication();
    printCharactersLeft();
    getAuthentication2();

}




function printCharactersLeft(){
    document.getElementById('comment').onkeyup = function () {
  document.getElementById('count').innerHTML = "Characters left: " + (250 - this.value.length) + "/"+ 250;
};
}

function getAuthentication2() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            let user = JSON.parse(xhttp.responseText);
            setName(user);
            determineUserNav(user);
            getEmpId(user);
            extraFeature(user);
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
function extraFeature(user){
	if (user.empType == "Benefits Coordinator" || user.empType == "Benefits Coordinator Direct Supervisor" || user.empType == "Benefits Coordinator Department Head"){
    document.getElementById("extra-feature").innerHTML = '<div class="form-group row"><label class="col-sm-6 col-form-label">Award Amount Changed:</label><div class="col-sm-5"><input type="number" class="form-control" id="changeAward" name="changeAward" value="Change Award" /></div></div></div>';
    
    document.getElementById("extra-option").innerHTML ='<option value="3">Approved/Changed Reward</option>';
	}
}

function getEmpId(user){
	
	document.getElementById("empID").value = user.id;
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
    //document.getElementById("firstName").value = user[r].fName;// need this
    //document.getElementById("lastName").value = user[r].lName;// need this
    document.getElementById("eventType").value = user[r].rei_type;
    //document.getElementById("eventLocation").value = user[r].eventLocation;// need this
    document.getElementById("eventDate").value = user[r].eventStartDate;
    //document.getElementById("eventTime").value = user[r].eventTime; // need this
//    document.getElementById("timeFrame").value =;
    document.getElementById("comment").value =  user[r].description;
    document.getElementById("requestedAmount").value = user[r].awardRequested;
//    document.getElementById("totalAward").value =;
}