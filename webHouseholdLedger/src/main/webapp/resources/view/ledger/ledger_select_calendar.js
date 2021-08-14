/**
 * 
 */

$(document).ready(function(){
	var selectMonthForm = $("#selectMonthForm");
	
	$('#submitSelectMonth').click(function() {
		fn_selectMonth();
	});
	
	$('#btnPrevMonth').click(function() {
		fn_changeSelectedMonth(-1);
	});
	
	$('#btnNextMonth').click(function() {
		fn_changeSelectedMonth(1);
	});
	
	function fn_changeSelectedMonth(index) {
		var thisMonth = moment($('#selectedMonthTitle').text()+'-01').clone();
		var changedMonth = thisMonth.add(index, 'months').format('YYYY-MM');
		
		selectMonthForm.find('input[name=date]').val(changedMonth);
		fn_selectMonth();
	}

	function fn_selectMonth() {
		selectMonthForm.action = "/app/ledger/calendar.do";
		selectMonthForm.submit();
	}

});