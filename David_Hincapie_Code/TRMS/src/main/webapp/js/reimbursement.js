/**
 * 
 */

var employee;
var date;

window.onload = function() {
	getEmployeeInfo();
	getDate();
}

function getEmployeeInfo() {
	console.log("IN getEmployeeInfo()");
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			employee = JSON.parse(xhttp.responseText);
			setEmployeeValues(employee);
		}
	}

	xhttp.open("GET", 'http://localhost:8080/TRMS/html/EmployeeJSON.do', true);
	xhttp.send();
}

function setEmployeeValues(employee) {
	console.log("IN setEmployeeValues()");

	document.getElementById("employeeId").value = employee.employeeId;
	document.getElementById("firstname").value = employee.firstName;
	document.getElementById("lastname").value = employee.lastName;

}

// --------------------------------------------GOOGLE API
// JAVASCRIPT--------------------------------------------
var placeSearch, autocomplete;

var componentForm = {
	street_number : 'short_name',
	route : 'long_name',
	locality : 'long_name',
	administrative_area_level_1 : 'short_name',
	country : 'long_name',
	postal_code : 'short_name'
};

function initAutocomplete() {
	// Create the autocomplete object, restricting the search to geographical
	// location types.
	autocomplete = new google.maps.places.Autocomplete(
	/** @type {!HTMLInputElement} */
	(document.getElementById('autocomplete')), {
		types : [ 'geocode' ]
	});

	// When the user selects an address from the dropdown, populate the address
	// fields in the form.
	autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
	// Get the place details from the autocomplete object.
	var place = autocomplete.getPlace();

	for ( var component in componentForm) {
		document.getElementById(component).value = '';
		document.getElementById(component).disabled = false;

	}

	// Get each component of the address from the place details
	// and fill the corresponding field on the form.
	var fullAddress = [];
	for (var i = 0; i < place.address_components.length; i++) {
		var addressType = place.address_components[i].types[0];
		if (componentForm[addressType]) {
			var val = place.address_components[i][componentForm[addressType]];
			document.getElementById(addressType).value = val;
		}
		if (addressType == "street_number") {
			fullAddress[0] = val;
		} else if (addressType == "route") {
			fullAddress[1] = val;
		}
	}
	document.getElementById('streetAddress').value = fullAddress.join(" ");
	if (document.getElementById('streetAddress').value !== "") {
		document.getElementById('streetAddress').disabled = false;
	}
}

// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var geolocation = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};
			var circle = new google.maps.Circle({
				center : geolocation,
				radius : position.coords.accuracy
			});
			autocomplete.setBounds(circle.getBounds());
		});
	}
}

function getProjectedReimbursement() {
	let cost = document.getElementById("cost").value;
	let coverage = 0;
	let value = document.getElementById("eventType").value;
	switch (value) {
	case "1":
		coverage = .8;
		break;
	case "2":
		coverage = .6;
		break;
	case "3":
		coverage = .75;
		break;
	case "4":
		coverage = 1;
		break;
	case "5":
		coverage = .9;
		break;
	case "6":
		coverage = .3;
		break;
	}
	if (cost > 0 && coverage > 0) {
		document.getElementById("projectedReimbursement").value = cost
				* coverage;
	}

}

function getDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1; // January is 0!
	var yyyy = today.getFullYear();

	if (dd < 10) {
		dd = '0' + dd
	}

	if (mm < 10) {
		mm = '0' + mm
	}

	today = yyyy + '-' + mm + '-' + dd;
	console.log("DATE " + today);
	document.getElementById("reimbursementDate").value = today;
}

function validateForm() {
	var eventType = document.forms["reimbursementform"]["eventType"].value;
	var cost = document.forms["reimbursementform"]["cost"].value;
	var projectedReimbursement = document.forms["reimbursementform"]["projectedReimbursement"].value;
	var eventType = document.forms["reimbursementform"]["eventType"].value;
	var eventTime = document.forms["reimbursementform"]["eventTime"].value;
	var startDate = document.forms["reimbursementform"]["startDate"].value;
	var endDate = document.forms["reimbursementform"]["endDate"].value;
	var gradeFormat = document.forms["reimbursementform"]["gradeFormat"].value;
	var gradeCutoff = document.forms["reimbursementform"]["gradeCutoff"].value;
	var streetAddress = document.forms["reimbursementform"]["streetAddress"].value;
	var locality = document.forms["reimbursementform"]["locality"].value;
	var administrative_area_level_1 = document.forms["reimbursementform"]["administrative_area_level_1"].value;
	var postal_code = document.forms["reimbursementform"]["postal_code"].value;
	var country = document.forms["reimbursementform"]["country"].value;

	if (eventType == null || eventType == "" || cost == null || cost == ""
			|| projectedReimbursement == null || projectedReimbursement == ""
			|| eventType == null || eventType == "" || eventTime == null || eventTime == ""
			|| startDate == null || startDate == "" || endDate == null || endDate == ""
			|| gradeFormat == null || gradeFormat == "" || gradeCutoff == null || gradeCutoff == ""
			|| streetAddress == null || streetAddress == "" || locality == null || locality == ""
			|| administrative_area_level_1 == null || administrative_area_level_1 == "" || postal_code == null || postal_code == ""
			|| country == null || country == "") {
		alert("Please fill all required fields.");
		return false;
	} else {
		return true;
	}

}