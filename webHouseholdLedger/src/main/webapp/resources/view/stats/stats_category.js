/**
 * 
 */

window.onload = function() {
	
	// Pie Chars - Income Category
	var incomeListSize = $('input[name=incomeListSize]').val();
	var strIncomeLabels = [];
	var strIncomeData = [];
	
	for(var i = 1; i <= incomeListSize; i++){
		strIncomeLabels.push($('#incomeDataTable').find('tbody tr:nth-child('+i+') td:nth-child(1)').text());
		strIncomeData.push($('#incomeDataTable').find('tbody tr:nth-child('+i+') td:nth-child(2)').text());
	}
	
	var incomePieChartCanvas = $('#incomePieChart').get(0).getContext('2d');
	
	var incomePieData = {
			labels: strIncomeLabels,
			datasets: [{
				data: strIncomeData,
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
	var strExpensesLabels = [];
	var strExpensesData = [];
	
	for(var i = 1; i <= expensesListSize; i++){
		strExpensesLabels.push($('#expensesDataTable').find('tbody tr:nth-child('+i+') td:nth-child(1)').text());
		strExpensesData.push($('#expensesDataTable').find('tbody tr:nth-child('+i+') td:nth-child(2)').text());
	}
	
	var expensesPieChartCanvas = $('#expensesPieChart').get(0).getContext('2d');
	
	var expensesPieData = {
			labels: strExpensesLabels,
			datasets: [{
				data: strExpensesData,
				backgroundColor : Chart.colorschemes.brewer['Spectral11']
			}]
	};
	
	new Chart(expensesPieChartCanvas, {
	      type: 'pie',
	      data: expensesPieData,
	      options: pieOptions
	})
	
};