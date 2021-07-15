/**
 * 
 */
// #dateForInsert에 오늘 날짜 입력
$(document).ready(function(){
	$("#dateForInsert").val(new Date().toISOString().substring(0, 10));
	$("#amountForInsert").val(0);
})

// 숫자만 입력
$("#amountForInsert").keyup(function(event){
    var inputVal = $(this).val();
    $(this).val(inputVal.replace(/[^0-9]/gi,''));
});

var selIncome = ["월급", "상여금", "펀드/주식", "기타수익"];
var selExpenses = ["식비","교통비","주거/통신","생활용품","경조사비","지식/문화","의복/미용","의료/건강","여가/유흥","세금/이자","기타비용"];

$("input:radio[name=Income_and_expenses]").click(function(){
	
	if($("#radioTransfer").is(":checked")){
		$("#incomeAndExpensesDiv").hide();
		$("#transferDiv").show();
	}else if($("#radioIncome").is(":checked")){
		$("#transferDiv").hide();
		$("#incomeAndExpensesDiv").show();
		fn_changeOption(selIncome)
	}else if($("#radioExpenses").is(":checked")){
		$("#transferDiv").hide();
		$("#incomeAndExpensesDiv").show();
		fn_changeOption(selExpenses)
	}
});

//[Modal]: 가계부 내역 입력 Modal 열기
function fn_openInsertModal(){
	// Modal 열기
	$("#modal-lg-insert").modal("show");
}

// Modal창 닫기
function fn_closeInsertModal(){
	$("#modal-lg-insert").modal("hide");
}

function fn_insertLedger() {
	var form = document.getElementById("ledgerInputForm");
	form.action = "/app/ledger/insert_ledger.do";
	form.submit();
}

function fn_changeOption(list) {
	
	var html;
	var selList = list;
	
	for(var i in selList){
		html += "<option value="+"'"+selList[i]+"'>"+selList[i]+"</option>";
	}
	
	$("select[name=category]").html(html);
}
