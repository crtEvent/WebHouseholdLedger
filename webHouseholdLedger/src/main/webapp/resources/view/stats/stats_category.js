/**
 * 
 */

$(document).ready(function(){
	
	// Pie Chars - Income Category
	var incomeListSize = $('input[name=incomeListSize]').val();
	var incomeLabelArray = [];
	var incomeDataArray = [];
	
	for(var i = 1; i <= incomeListSize; i++){
		incomeLabelArray.push($('#incomeDataTable').find('tbody tr:nth-child('+i+') td:nth-child(1)').text());
		incomeDataArray.push($('#incomeDataTable').find('tbody tr:nth-child('+i+') td:nth-child(2)').text());
	}
	
	var incomePieChartCanvas = $('#incomePieChart').get(0).getContext('2d');
	
	var incomePieData = {
			labels: incomeLabelArray,
			datasets: [{
				data: incomeDataArray,
				backgroundColor : Chart.colorschemes.brewer['Spectral11']
			}]
	};
	
	var pieOptions = {
			maintainAspectRatio : false,
			responsive : false,
			legend: {
	            display: true,
	            position: 'right'
	         }
	};
	
	new Chart(incomePieChartCanvas, {
	      type: 'pie',
	      data: incomePieData,
	      options: pieOptions
	})
	
	// Pie Chars - Expenses Category
	var expensesListSize = $('input[name=expensesListSize]').val();
	var expensesLabelsArray = [];
	var expensesDataArray = [];
	
	for(var i = 1; i <= expensesListSize; i++){
		expensesLabelsArray.push($('#expensesDataTable').find('tbody tr:nth-child('+i+') td:nth-child(1)').text());
		expensesDataArray.push($('#expensesDataTable').find('tbody tr:nth-child('+i+') td:nth-child(2)').text());
	}
	
	var expensesPieChartCanvas = $('#expensesPieChart').get(0).getContext('2d');
	
	var expensesPieData = {
			labels: expensesLabelsArray,
			datasets: [{
				data: expensesDataArray,
				backgroundColor : Chart.colorschemes.brewer['Spectral11']
			}]
	};
	
	new Chart(expensesPieChartCanvas, {
	      type: 'pie',
	      data: expensesPieData,
	      options: pieOptions
	})
	
});