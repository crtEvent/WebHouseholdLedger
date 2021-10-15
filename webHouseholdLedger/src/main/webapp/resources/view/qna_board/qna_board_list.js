/**
 * 
 */

var user = window.location.search;
var url = new URL(window.location.href);
var urlParams = url.searchParams;
var board_idx = urlParams.get("board_idx");
var search_type = urlParams.get("search_type");
var keyword = urlParams.get("keyword");
var currentPage = urlParams.get("currentPage");

function fn_linkToPage(currentPage){
	var linkToPageForm = $("<form></form>");
	
	linkToPageForm.attr("name","linkToPageForm");
	linkToPageForm.attr("method","get");
	linkToPageForm.attr("action",url);
	
	fn_appendAttrAndSubmitForm(linkToPageForm, board_idx, search_type, keyword, currentPage);
}

function fn_linkToPost(board_idx){
	var linkToPostForm = $("<form></form>");
	
	linkToPostForm.attr("name","linkToPostForm");
	linkToPostForm.attr("method","get");
	linkToPostForm.attr("action","/app/qna/post.do");
	
	fn_appendAttrAndSubmitForm(linkToPostForm, board_idx, search_type, keyword, currentPage);
}

function fn_linkToList() {
	$(location).attr("href","/app/qna/list.do");
}

function fn_appendAttrAndSubmitForm(form, board_idx, search_type, keyword, currentPage) {
	if(board_idx != '' && board_idx != null){
		form.append($("<input/>", {type: "hidden", name: "board_idx", value: board_idx}));
	}
	if(search_type != '' && search_type != null) {
		form.append($("<input/>", {type: "hidden", name: "search_type", value: search_type}));
	}
	if(keyword != '' && keyword != null){
		form.append($("<input/>", {type: "hidden", name: "keyword", value: keyword}));
	}
	if(currentPage != '' && currentPage != null){
		form.append($("<input/>", {type: "hidden", name: "currentPage", value: currentPage}));
	}
	
	form.appendTo("body");
	form.submit();
}