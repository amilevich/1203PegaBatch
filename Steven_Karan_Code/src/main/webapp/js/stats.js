window.onload=function(){
	getEmployee();
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
	let ctx2 = document.getElementById('event_cost_pie')
	
	let event_count = [];
	for(let i = 0; i < chartData.length; i++){
		event_count.push(chartData[i]['count']);
	}
	let datasets = [{
		'label': 'Event Frequency',
		'data':event_count,
		"backgroundColor":["rgb(255, 99, 132)","rgb(54, 162, 235)","rgb(255, 205, 86)","#1A807D", "grey"]
		
	}];
	
	let event_labels = [];
	for(let i = 0; i < chartData.length; i++){
		event_labels.push(chartData[i]['eventType']);
	}
	
	
	let data = {'datasets':datasets,
				'labels':event_labels, 
				};
	
	let event_cost = [];
	for(let i = 0; i < chartData.length; i++){
		event_cost.push(chartData[i]['cost']);
	}
	
	let datasets2 = [{
		'label':'Event Cost',
		'data': event_cost,
		"backgroundColor":["rgb(255, 99, 132)","rgb(54, 162, 235)","rgb(255, 205, 86)","#1A807D", "grey"]
	}];
	
	let data2 = {'datasets': datasets2, 'labels':event_labels};
	
	document.getElementById('spinner').innerHTML = '';
	var myPieChart = new Chart(ctx,{
	    'type': 'pie',
	    'data': data,
	    'options': {
	    	maintainAspectRatio:false,
	    	legend: {
            display: true,
	    	}
        }
	});
	
};