/**
 * 
 */

//게시글 등록 함수
function fn_insertQnaPost() {
	
	if(!fn_checkPostWritingField()){
		return;
	}
	
	var writeQnaPostForm = $("<form></form>");
	
	writeQnaPostForm.attr("name","writeQnaPostForm");
	writeQnaPostForm.attr("method","post");
	writeQnaPostForm.attr("action","/app/qna/insert_post.do");
	writeQnaPostForm.attr("enctype","multipart/form-data");
	
	writeQnaPostForm.append($('input[name=subject]'));
	writeQnaPostForm.append($('textarea[name=content]'));
	
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
