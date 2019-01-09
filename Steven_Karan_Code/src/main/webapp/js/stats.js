window.onload=function(){
	getChartData();
	getAlert();
};

function getChartData(){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let chartData = JSON.parse(xhr.responseText);
			setChartData(chartData);
		}
	}
	xhr.open('GET', 'http://localhost:9000/ReimbursementSystem/html/statsJSON.do', true);
	xhr.send();
};

function setChartData(chartData){
	let ctx = document.getElementById('event_count_pie').getContext('2d');
	
	let event_count = [];
	for(let i = 0; i < chartData.length; i++){
		event_count.push(chartData[i]['count']);
	}
	
	let datasets = [{
		'label': 'Event Frequency',
		'data':event_count,
		"backgroundColor":["rgb(255, 99, 132)","rgb(54, 162, 235)","rgb(255, 205, 86)","green", "grey"],
		
	}];
	
	let event_labels = [];
	for(let i = 0; i < chartData.length; i++){
		event_labels.push(chartData[i]['eventType']);
	}
	
	
	
	let data = {'datasets':datasets,
				'labels':event_labels, 
				};
	
	
	
	var myPieChart = new Chart(ctx,{
	    'type': 'pie',
	    'data': data,
	    'options': {'responsive': true}
	});
};