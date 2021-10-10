/**
 * 
 */

var user = window.location.search;
var url = new URL(window.location.href);
var urlParams = url.searchParams;
var search_type = urlParams.get("search_type");
var keyword = urlParams.get("keyword");

function fn_linkToPage(page){
	
	alert("search_type: "+search_type+", keyword: "+keyword+", page: "+page);
	var linkToPageForm = $("<form></form>");
	
	linkToPageForm.attr("name","linkToPageForm");
	linkToPageForm.attr("method","get");
	linkToPageForm.attr("action","/app/qna/list.do");
	
	linkToPageForm.append($("<input/>", {type: "hidden", name: "search_type", value: search_type}));
	linkToPageForm.append($("<input/>", {type: "hidden", name: "keyword", value: keyword}));
	linkToPageForm.append($("<input/>", {type: "hidden", name: "currentPage", value: page}));
	
	linkToPageForm.appendTo("body");
	linkToPageForm.submit();
}