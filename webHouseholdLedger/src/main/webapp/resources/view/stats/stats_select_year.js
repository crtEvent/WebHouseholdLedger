/**
 * 
 */

$(document).ready(function(){
	
	$("#selectYear").datepicker({
		autoclose: true,
		format: " yyyy",
		viewMode: "years",
		minViewMode: "years",
		disableTouchKeyboard: true,
		language: "ko"
	}).on("changeDate", function(e) {
		fn_selectYear();
	});
	
	var selectYearForm = $("#selectYearForm");
	
	function fn_selectYear() {
		selectYearForm.action = "/app/stats/yearly.do";
		selectYearForm.submit();
	}
	
});