/**
 * 
 */

$(document).ready(function(){
	
	var incomeDataArray = [];
	var expensesDataArray = [];
	
	for(var i = 1; i <= 12; i++){
		incomeDataArray.push($('#yearlyDataTable').find('tbody tr:nth-child('+i+') td:nth-child(2)').text());
		expensesDataArray.push($('#yearlyDataTable').find('tbody tr:nth-child('+i+') td:nth-child(3)').text());
	}
	
	console.log("incomeDataArray: "+incomeDataArray);
	console.log("expensesDataArray: "+expensesDataArray);
	
	var yearlyBarChartCanvas = $('#yearlyBarChart').get(0).getContext('2d');
	
	var yearlyBarData = {
		labels  : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		datasets: [
			{
				label               : '수입',
				backgroundColor     : 'rgba(60,141,188,0.9)',
				borderColor         : 'rgba(60,141,188,0.8)',
				pointRadius          : false,
				pointColor          : '#3b8bba',
				pointStrokeColor    : 'rgba(60,141,188,1)',
				pointHighlightFill  : '#fff',
				pointHighlightStroke: 'rgba(60,141,188,1)',
				data                : incomeDataArray
			},
			{
				label               : '지출',
				backgroundColor     : 'rgba(220, 20, 60, 1)',
				borderColor         : 'rgba(220, 214, 222, 1)',
				pointRadius         : false,
				pointColor          : 'rgba(220, 214, 222, 1)',
				pointStrokeColor    : '#c1c7d1',
				pointHighlightFill  : '#fff',
				pointHighlightStroke: 'rgba(220, 214, 222, 1)',
				data                : expensesDataArray
			},
		]
	}
	
	var barChartOptions = {
		responsive: true,
		maintainAspectRatio: false,
		datasetFill: false
	}
	
	new Chart(yearlyBarChartCanvas, {
		type: 'bar',
		data: yearlyBarData,
		options: barChartOptions
	})
	
});