/**
 * 
 */

var insertForm = $("#ledgerInsertForm");
var updateForm = $("#ledgerUpdateForm");
var deleteForm = $("#ledgerDeleteForm");
var selIncome = ["월급", "상여금", "펀드/주식", "기타수익"];
var selExpenses = ["식비","교통비","주거/통신","생활용품","경조사비","지식/문화","의복/미용","의료/건강","여가/유흥","세금/이자","기타비용"];

// #dateForInsert에 오늘 날짜 입력
$(document).ready(function(){
	insertForm.find("[name=date]").val(new Date().toISOString().substring(0, 10));
	insertForm.find("[name=amount]").val(0);
})

// amount 유효성 검사: 숫자만 입력 가능
$("[name=amount]").keyup(function(event){
    var inputVal = $(this).val();
    $(this).val(inputVal.replace(/[^0-9]/gi,''));
});

insertForm.find("input:radio[name=income_and_expenses]").click(function(){
	
	fn_changeModalForm(insertForm);
	
});

updateForm.find("input:radio[name=income_and_expenses]").click(function(){
	
	fn_changeModalForm(updateForm);
	
});

// input:radio[name=Income_and_expenses]에 따라 Modal form변화
function fn_changeModalForm(form) {
	
	if(form.find("input:radio[name='income_and_expenses'][value='이동']").is(":checked")){console.log("이동 변화");
		form.find(".incomeAndExpensesDiv").hide();
		form.find(".transferDiv").show();
	}else if(form.find("input:radio[name='income_and_expenses'][value='수입']").is(":checked")){console.log("수입 변화");
		form.find(".transferDiv").hide();
		form.find(".incomeAndExpensesDiv").show();
		fn_changeCategoryOption(selIncome, form)
	}else if(form.find("input:radio[name='income_and_expenses'][value='지출']").is(":checked")){console.log("지출 변화");
		form.find(".transferDiv").hide();
		form.find(".incomeAndExpensesDiv").show();
		fn_changeCategoryOption(selExpenses, form)
	}
	
}

//category에 option 추가
function fn_changeCategoryOption(list, form) {
	console.log("fn_changeCategoryOption");
	console.log(list);
	var html;
	var selList = list;
	
	for(var i in selList){
		html += "<option value="+"'"+selList[i]+"'>"+selList[i]+"</option>";
	}
	
	form.find("select[name=category]").html(html);
}

// [Modal]: 가계부 내역 입력 Modal 열기
function fn_openInsertModal(){
	$("#modal-lg-insert").modal("show");
}

// [Modal]: 창 닫기
function fn_closeModal(){
	$("#modal-lg-insert").modal("hide");
	$("#modal-lg-update").modal("hide");
	$("#modal-lg-delete").modal("hide");
}

// [Modal]: 가계부 내역 수정 Modal 열기
$("[name=btnLedgerUpdate]").click(function(){
	
	var tuple = $(this).closest("tr");
	var ledger_idx = tuple.find("#ledger_idx").val();
	var date = tuple.find("#date").val();
	var income_and_expenses = tuple.find("#income_and_expenses").val();
	var category = tuple.find("#category").val();
	var description = tuple.find("#description").val();
	var amount = tuple.find("#amount").val();
	var asset = tuple.find("#asset").val();
	var former_asset = tuple.find("#former_asset").val();
	var latter_asset = tuple.find("#latter_asset").val();
	
	updateForm.find("[name=ledger_idx]").val(ledger_idx);
	updateForm.find("[name=date]").val(date);
	
	switch(income_and_expenses){
	case "수입":
		// income_and_expenses 이동 비활성
		updateForm.find("input:radio[name='income_and_expenses'][value='수입']").prop("disabled", false);
		updateForm.find("input:radio[name='income_and_expenses'][value='지출']").prop("disabled", false);
		updateForm.find("input:radio[name='income_and_expenses'][value='이동']").prop("disabled", true);
		// income_and_expenses 수입 체크
		updateForm.find("input:radio[name='income_and_expenses'][value='수입']").prop("checked", true);
		fn_changeModalForm(updateForm);
		
		updateForm.find("[name=category]").val(category);
		updateForm.find("[name=description]").val(description);
		updateForm.find("[name=amount]").val(amount);
		updateForm.find("[name=asset]").val(asset);
		break;
	case "지출":
		// income_and_expenses 이동 비활성
		updateForm.find("input:radio[name='income_and_expenses'][value='수입']").prop("disabled", false);
		updateForm.find("input:radio[name='income_and_expenses'][value='지출']").prop("disabled", false);
		updateForm.find("input:radio[name='income_and_expenses'][value='이동']").prop("disabled", true);
		// income_and_expenses 지출 체크
		updateForm.find("input:radio[name='income_and_expenses'][value='지출']").prop("checked", true);
		fn_changeModalForm(updateForm);
		
		updateForm.find("[name=category]").val(category);
		updateForm.find("[name=description]").val(description);
		updateForm.find("[name=amount]").val(amount);
		updateForm.find("[name=asset]").val(asset);
		break;
	case "이동":
		// income_and_expenses 수입/지출 비활성
		updateForm.find("input:radio[name='income_and_expenses'][value='수입']").prop("disabled", true);
		updateForm.find("input:radio[name='income_and_expenses'][value='지출']").prop("disabled", true);
		updateForm.find("input:radio[name='income_and_expenses'][value='이동']").prop("disabled", false);
		// income_and_expenses 이동 체크
		updateForm.find("input:radio[name='income_and_expenses'][value='이동']").prop("checked", true);
		fn_changeModalForm(updateForm);
		
		updateForm.find("[name=former_asset]").val(former_asset);
		updateForm.find("[name=latter_asset]").val(latter_asset);
		updateForm.find("[name=amount]").val(amount);
		break;
	}
	
	$("#modal-lg-update").modal("show");
})

// [DELETE] 가계부 내역 삭제
$("[name=btnLedgerDelete]").click(function(){
	
	var ledger_idx = $(this).closest("tr").find("#ledger_idx").val();
	deleteForm.find("[name=ledger_idx]").val(ledger_idx);
	
	$("#modal-lg-delete").modal("show");
})

// [INSERT] 가계부 내역 입력
function fn_insertLedger() {
	//var _context = "<c:url value='/'/>";
	//insertForm[0].action = _context + "ledger/insert_ledger.do";
	insertForm[0].submit();
}

// [UPDATE] 가계부 내역 수정
function fn_updateLedger() {
	updateForm[0].action = "/app/ledger/update_ledger.do";
	updateForm[0].submit();
}

// [DELETE] 가계부 내역 삭제
function fn_deleteLedger() {
	deleteForm[0].action = "/app/ledger/delete_ledger.do";
	deleteForm[0].submit();
}
	
