

window.onload=function(){
 	getAlert();
 }

 document.getElementById("attach-input").onchange = function () {
 	let file_arr = this.value.split("\\");
 	console.log(file_arr);
 	let filename = file_arr[file_arr.length-1];
 	console.log(filename);
    document.getElementById("filename").innerHTML = filename;
};