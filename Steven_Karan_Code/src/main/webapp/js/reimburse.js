var event_types = {
	'Course':0.8,
	'Seminar':0.6,
	'Certification':1.0,
	'Certification Prep':0.75,
	'Technical-Training':0.90,
	'Other':0.3
};
var passing_grade = {
	'letter-grade':'C',
	'number-grade':65,
	'pass-fail':'Pass'
};

window.onload=function(){
	getEmployee();
 	getAlert();
 }

document.getElementById("attach-input").onchange = function () {
 	if(this.files.length == 0){
 		document.getElementById("filenames").innerHTML = "";
 		return;
 	}
 	let filenames = '<h5> Files Attached </h5> <hr> <ul class="list-group">';
 	for(let i = 0; i < this.files.length; i++){
 		console.log(i.name);
 		filenames += '<li class="list-group-item">' +this.files[i].name + '</li>'
 	}
 	filenames += "</ul>";
    document.getElementById("filenames").innerHTML = filenames;
};


// Get event types and reimbursement precentage
/*function getEventTypes(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readystate == 4 && xhr.status == 200){
			event_types = JSON.parse(xhr.responseText);
		}
	};
};*/

// Sets the real-time updating of the event-cost / estimated reimbursement field
document.getElementById("event-cost").onkeyup = function(){
	if(event_types == undefined || this.value == null){
		return;
	}
	// get event coverage based on event-type lookup
	let curr_event_type = document.getElementById("event-type").value;
	let coverage = event_types[curr_event_type];
	console.log(curr_event_type);
	console.log(coverage);
	document.getElementById("reimbursement").value = (this.value * coverage).toFixed(2);
};




document.getElementById('description').onkeyup = function(){
	let curr_len = 1000 - this.value.length;
	document.getElementById('desc-limit').innerHTML = 1000- this.value.length;
	if(curr_len < 0){
		document.getElementById('desc-limit').classList.add('text-danger');
	}else{
		document.getElementById('desc-limit').classList.remove('text-danger');
	}
}


document.getElementById('justification').onkeyup = function(){
	let curr_len = 1000 - this.value.length;
	document.getElementById('just-limit').innerHTML = 1000- this.value.length;
	if(curr_len < 0){
		document.getElementById('just-limit').classList.add('text-danger');
	}else{
		document.getElementById('just-limit').classList.remove('text-danger');
	}
}

