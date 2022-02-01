/**
 * 
 */
const url = new URL(window.location.href);
const urlParams = url.searchParams;
const keyword = urlParams.get("keyword");
const currentPage = urlParams.get("currentPage");
let checkedMail = new Array(); // 체크박스 체크를 위한 배열

function fn_linkToPage(page) {
	var linkToPageForm = $("<form></form>");
	
	linkToPageForm.attr("name","linkToPageForm");
	linkToPageForm.attr("method","get");
	linkToPageForm.attr("action",url);
	
	if(keyword != '' && keyword != null){
		linkToPageForm.append($("<input/>", {type: "hidden", name: "keyword", value: keyword}));
	}
	if(page != '' && page != null){
		linkToPageForm.append($("<input/>", {type: "hidden", name: "currentPage", value: page}));
	} else {
		linkToPageForm.append($("<input/>", {type: "hidden", name: "currentPage", value: 1}));
	}
	
	linkToPageForm.appendTo("body");
	linkToPageForm.submit();
}

function fn_doSearch() {
	var doSearchForm = $("<form></form>");
	
	doSearchForm.attr("name","searchForm");
	doSearchForm.attr("method","get");
	doSearchForm.attr("action",url);
	
	doSearchForm.append($("<input/>", {type: "hidden", name: "currentPage", value: 1}));
	doSearchForm.append($("<input/>", {type: "hidden", name: "keyword", value: $("input[name=keyword]").val()}));
	
	doSearchForm.appendTo("body");
	doSearchForm.submit();
	
}

// 게시글 삭제 기능
//  - mail_state는 'SENT' or 'STORED'값 허용. 예외는 controller에서 처리함
function fn_deleteMail(mail_state) {
	
	$("input[name=chckMail]:checked").each(function(i) {
		checkedMail.push($(this).val());
	});
	
	if(checkedMail.length == 0) {
		alert("선택한 메일이 없습니다.");
		return;
	}
	
	var con = confirm("선택한 "+checkedMail.length+"개의 메일을 삭제하시겠 습니까?");
	
	if(con) {
		var deleteMailForm = $("<form></form>");
		
		deleteMailForm.attr("name","deleteMailForm");
		deleteMailForm.attr("method","post");
		deleteMailForm.attr("action", "/app/admin/mail/deleteMail.do");
		
		deleteMailForm.append($("<input/>", {type: "hidden", name: "checkedMail", value: checkedMail}));
		deleteMailForm.append($("<input/>", {type: "hidden", name: "mail_state", value: mail_state}));
		
		deleteMailForm.appendTo("body");
		deleteMailForm.submit();
	}
	
	// checkedMail 배열 초기화
	checkedMail = [];
}

// 체크박스전체 선택
function fn_checkAll() {
	
	// 이미 전체 선택 버튼 없이 15개 모두 선택되어 있는 경우
	if($("input[name=chckMail]:checked").length == 15) {
		$("input[name=chckMail]").prop("checked", false);
		$("input[name=checkAll]").prop("checked", false);
		return;
	}
	
	if($("#checkAll").is(":checked")) {
		$("input[name=chckMail]").prop("checked", true);
	} else {
		$("input[name=chckMail]").prop("checked", false);
	}
}

function fn_linkToMailContent() {
	
}

