/**
 * 2021-12-01 생성
 * 게시글 삭제 Modal을 위한 Script
 */

var url = new URL(window.location.href);
var urlParams = url.searchParams;
var board_idx = urlParams.get("board_idx");

// [Modal]: 게시글 삭제 Modal 열기
function fn_openDeletePostModal() {
	$("#modal-deletePost").modal("show");
}

// [Modal]: 창 닫기
function fn_closeModal() {
	$("#modal-deletePost").modal("hide");
}

// 게시글 삭제
function fn_deleteQnaPost() {
	
	var deleteQnaPostForm = $("<form></form>");
	deleteQnaPostForm.attr("name","deleteQnaPostForm");
	deleteQnaPostForm.attr("method","post");
	deleteQnaPostForm.attr("action","/app/qna/delete_post.do");
	
	deleteQnaPostForm.append($("<input/>", {type: "hidden", name: "board_idx", value: board_idx}));
	deleteQnaPostForm.appendTo("body");
	deleteQnaPostForm.submit();
	
}