

window.onload=function(){
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