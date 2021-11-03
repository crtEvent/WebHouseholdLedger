/**
 * 
 */

$(document).ready(function(){
	$('#writeContentField').summernote({
		height: 450,
		lang: 'ko-KR',
		toolbar: [
			['style', ['bold', 'italic', 'underline', 'clear']],
			['fontsize', ['fontsize']],
		    ['color', ['color']],
		    ['para', ['paragraph']],
		    ['height', ['height']]
			['table', ['table']],
			['insert', ['link', 'picture', 'video']]
		],
		fontSizes: ['10', '11', '12', '14', '16', '18', '24', '36', '48']
	});
});

//게시글 등록 함수
function fn_insertQnaPost() {
	
	var writeQnaPostForm = $("<form></form>");
	
	writeQnaPostForm.attr("name","writeQnaPostForm");
	writeQnaPostForm.attr("method","post");
	writeQnaPostForm.attr("action","/app/qna/insert_post.do");
	writeQnaPostForm.attr("enctype","multipart/form-data");
	
	writeQnaPostForm.append($('input[name=subject]'));
	writeQnaPostForm.append($('textarea[name=content]'));
	
	writeQnaPostForm.append($("#fileList").find("input[type=file]"));
	
	writeQnaPostForm.appendTo("body");
	
	writeQnaPostForm.submit();
}

var fileCnt = 1;

function fn_addFile() {
	$("#fileList").append('<div class="mt-1" id="fileDiv'+fileCnt+'"></div>')
	
	var inputTag = '<input type="file" name="file'+fileCnt+'">';
	var cancelBtn = '<button class="btn btn-sm" name="cancel"><i class="fas fa-times-circle"></i></button>';

	$("#fileDiv"+fileCnt).append(inputTag);
	$("#fileDiv"+fileCnt).append(cancelBtn);
	
	$("input[name=file"+fileCnt+"]").click();
	
	fileCnt++;
	
	// 취소 버튼
	$("button[name=cancel]").on("click", function(e){ 
		e.preventDefault();
		fn_deleteFile($(this));
	});
	
	// input 클릭 금지
	$("input[type=file]").click(function (e) {
		e.preventDefault();
	});
}

function fn_deleteFile(obj){
	obj.parent().remove();
	
}
