/**
 * 
 */

$(document).ready(function(){
	
	$("#selectMonth").datepicker({
		autoclose: true,
		format: " yyyy-mm",
		viewMode: "months",
		minViewMode: "months",
		disableTouchKeyboard: true,
		language: "ko"
	}).on("changeDate", function(e) {
		fn_selectMonth();
	});
	
	var selectMonthForm = $("#selectMonthForm");
	
	function fn_selectMonth() {
		selectMonthForm.action = "/app/stats/category.do";
		selectMonthForm.submit();
	}
	
});