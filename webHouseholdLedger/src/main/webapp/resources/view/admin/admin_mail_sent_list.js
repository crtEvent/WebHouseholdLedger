/**
 * 
 */
var url = new URL(window.location.href);
var urlParams = url.searchParams;
var keyword = urlParams.get("keyword");
var currentPage = urlParams.get("currentPage");

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