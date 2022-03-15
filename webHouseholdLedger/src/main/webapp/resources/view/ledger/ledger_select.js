/**
 * 
 */

window.onload = function() {
	
	var selectForm = $("#ledgerSelectForm");
	
	//Date range picker
	$("#selectPeriod").daterangepicker({
		locale:{
			format: "YYYY-MM-DD",
			applyLabel: "확인",
			cancelLabel: "취소",
			daysOfWeek: [
				"일", "월", "화", "수", "목", "금", "토"
			],
			monthNames: [
				"1월", "2월", "3월", "4월",
				"5월", "6월", "7월", "8월",
				"9월", "10월", "11월", "12월"
			]
		},
		"maxSpan": {
	        "days": 180
	    },
	    opens: "center",
		startDate: selectForm.find("#start_date").val(),
		endDate: selectForm.find("#end_date").val()
	}, function(start, end, label){
		fn_selectLedgerPeriod(start.format("YYYY-MM-DD"),end.format("YYYY-MM-DD"));
	});
	
	// Month Button
	$("#btnOneMnth").click(function() {
		fn_selectLedgerPeriod(moment().subtract(30, "days").format("YYYY-MM-DD"), moment().format("YYYY-MM-DD"));
	});
	
	$("#btnThreeMnth").click(function() {
		fn_selectLedgerPeriod(moment().subtract(60, "days").format("YYYY-MM-DD"), moment().format("YYYY-MM-DD"));
	});
	
	$("#btnSixMnth").click(function() {
		fn_selectLedgerPeriod(moment().subtract(180, "days").format("YYYY-MM-DD"), moment().format("YYYY-MM-DD"));
	});

	// 가계부 기간 검색
	function fn_selectLedgerPeriod(start_date, end_date) {
		selectForm.find("#start_date").val(start_date);
		selectForm.find("#end_date").val(end_date);
		selectForm.action = "/app/ledger/details.do";
		selectForm.method = "get";
		selectForm.submit();
	}
	
}

//엑셀 다운로드
function fn_downloadExcel() {
	
	var form = $("<form></form>");
	
	form.attr("name","downloadExcelForm");
	form.attr("method","get");
	form.attr("action","/app/ledger/download_excel.do");
	
	form.append($("<input/>", {type: "hidden", name: "start_date", value: $("#ledgerSelectForm").find("#start_date").val()}));
	form.append($("<input/>", {type: "hidden", name: "end_date", value: $("#ledgerSelectForm").find("#end_date").val()}));
	
	form.appendTo("body");
	form.submit();
	
	$('form[name=downloadExcelForm]').remove();
	
}