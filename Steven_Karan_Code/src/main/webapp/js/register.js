/**
 * 
 */

window.onload=function(){
	getEmployee();
 	getAlert();
 	let positions = {
 		'Sales':['Salesperson', 'Department Head'], 
 		'Accounting':['Accountant', 'Department Head'], 
 		'Human Resources':['HR Person', 'Department Head'], 
 		'Administration':['Regional Manager', 'Assistant To The Regional Manager', 'Department Head'],
 		'Benefits Coordinator':['Benefits Coordinator', 'Department Head'],
 		'Other':['Other']
 	};
 	document.getElementById('department').onchange=function(){
 		console.log('changin');
 		let inner = "";
 		for(let i = 0; i < positions[this.value].length; i++){
 			inner+='<option value="'+positions[this.value][i]+'">'+positions[this.value][i]+'</option>'
 		}
 		document.getElementById('position').innerHTML = inner;
 	};
 }



