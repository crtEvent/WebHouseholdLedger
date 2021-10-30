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
	
	//var subject = $('input[name=subject]').val();
	//var content = $('textarea[name=content]').val();
	
	var writeQnaPostForm = $("<form></form>");
	
	writeQnaPostForm.attr("name","writeQnaPostForm");
	writeQnaPostForm.attr("method","post");
	writeQnaPostForm.attr("action","/app/qna/insert_post.do");
	
	writeQnaPostForm.append($('input[name=subject]'));
	writeQnaPostForm.append($('textarea[name=content]'));

	writeQnaPostForm.appendTo("body");
	writeQnaPostForm.submit();
	
	
}