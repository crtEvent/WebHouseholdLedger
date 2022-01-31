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

function fn_deleteMail() {
	
	$("input[name='chckMail']:checked").each(function(i) {
		checkedMail.push($(this).val());
	});
	
	var con = confirm("선택한 "+checkedMail.length+"개의 메일을 삭제하시겠 습니까?");
	
	if(con) {
		var deleteMailForm = $("<form></form>");
		
		deleteMailForm.attr("name","deleteMailForm");
		deleteMailForm.attr("method","post");
		deleteMailForm.attr("action", "/app/admin/mail/deleteMail.do");
		
		deleteMailForm.append($("<input/>", {type: "hidden", name: "checkedMail", value: checkedMail}));
		
		deleteMailForm.appendTo("body");
		deleteMailForm.submit();
	}
	
	// checkedMail 배열 초기화
	checkedMail = [];
}

function fn_linkToMailContent() {
	
}

