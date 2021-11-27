/**
 * 
 */

//게시글 수정 함수
function fn_updateQnaPost(board_idx) {
	
	if(!fn_checkPostWritingField()){
		return;
	}
	
	var writeQnaPostForm = $("<form></form>");
	
	writeQnaPostForm.attr("name","editQnaPostForm");
	writeQnaPostForm.attr("method","post");
	writeQnaPostForm.attr("action","/app/qna/update_post.do");
	writeQnaPostForm.attr("enctype","multipart/form-data");
	
	writeQnaPostForm.append($("<input/>", {type: "hidden", name: "board_idx", value: board_idx}));
	writeQnaPostForm.append($('input[name=subject]'));
	writeQnaPostForm.append($('textarea[name=content]'));
	writeQnaPostForm.append($('input[name=savedFile]'));
	
	// input[type=file] 여러개 다 가져옴
	var inputFiles = $("#fileList").find("input[type=file]");
	
	// input[type=file] tag가 있는 경우
	if(inputFiles.length != 0) {
		// 파일 값이 없는 input[type=file] tag는 제거
		for(var i = 0; i < inputFiles.length; i++) {
			if(inputFiles[i].files[0] == null){
				inputFiles[i].parentElement.remove();
			}
		}
		writeQnaPostForm.append($("#fileList").find("input[type=file]"));
	}
	
	writeQnaPostForm.appendTo("body");
	
	writeQnaPostForm.submit();
}
