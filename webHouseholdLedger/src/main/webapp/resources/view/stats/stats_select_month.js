/**
 * 
 */

$(document).ready(function(){
	
	var selectMonthForm = $("#selectMonthForm");
	
	$('#submitSelectMonth').click(function() {
		fn_selectMonth();
	});
	
	function fn_selectMonth() {
		selectMonthForm.action = "/app/stats/category.do";
		selectMonthForm.submit();
	}
	
});