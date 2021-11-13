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

$("#doSearchBtn").on("click", function(){
	var searchForm = $("<form></form>");
	
	searchForm.attr("name","searchForm");
	searchForm.attr("method","get");
	searchForm.attr("action",url);
	
	fn_appendAttrAndSubmitForm(searchForm, board_idx, $("select[name=search_type]").val(), $("[name=keyword]").val(), currentPage);
})

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

// 댓글 달기
function fn_writeComment() {
	
	var writeCommentForm = $("<form></form>");
	
	writeCommentForm.attr("name","writeCommentForm");
	writeCommentForm.attr("method","post");
	writeCommentForm.attr("action","/app/qna/write_comment.do");
	
	writeCommentForm.append($("<input/>", {type: "hidden", name: "board_idx", value: board_idx}));
	writeCommentForm.append($("<input/>", {type: "hidden", name: "comment", value: $("#commentField").val()}));
	
	writeCommentForm.appendTo("body");
	writeCommentForm.submit();
}

// 답글 달기
function fn_writeReComment(parent_comment_idx) {
	
	var writeRecommentForm = $("<form></form>");
	
	writeRecommentForm.attr("name","writeReCommentForm");
	writeRecommentForm.attr("method","post");
	writeRecommentForm.attr("action","/app/qna/write_recomment.do");
	
	writeRecommentForm.append($("<input/>", {type: "hidden", name: "board_idx", value: board_idx}));
	writeRecommentForm.append($("<input/>", {type: "hidden", name: "parent_comment_idx", value: parent_comment_idx}));
	writeRecommentForm.append($("<input/>", {type: "hidden", name: "comment", value: $("#reCommentField"+parent_comment_idx).val()}));
	
	writeRecommentForm.appendTo("body");
	writeRecommentForm.submit();
}

function fn_checkCommentFieldCharLimit() {
	var content = $("#commentField").val();
	$("#charLength").html("("+content.length+" / 최대 1000자)");
	
	if(content.length > 1000){
		alert("최대 1000자까지 입력 가능합니다.");
		$("#commentField").val(content.substring(0, 1000));
		$("#charLength").html("(1000 / 최대 1000자)");
	}
}

function fn_checkReCommentFieldCharLimit(comment_idx) {
	var content = $("#reCommentField"+comment_idx).val();
	$("#charLength"+comment_idx).html("("+content.length+" / 최대 1000자)");
	
	if(content.length > 1000){
		alert("최대 1000자까지 입력 가능합니다.");
		$("#reCommentField"+comment_idx).val(content.substring(0, 1000));
		$("#charLength"+comment_idx).html("(1000 / 최대 1000자)");
	}
}

function fn_linkToWritePage() {
	$(location).attr("href", "/app/qna/writePost.do");
}

function fn_linkToEdit(board_idx) {
	
	var linkToEditForm = $("<form></form>");
	
	linkToEditForm.attr("name","linkToEditForm");
	linkToEditForm.attr("method","post");
	linkToEditForm.attr("action","/app/qna/editPost.do");
	
	linkToEditForm.append($("<input/>", {type: "hidden", name: "board_idx", value: board_idx}));
	
	linkToEditForm.appendTo("body");
	linkToEditForm.submit();
	
}