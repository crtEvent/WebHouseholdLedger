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

var fileCnt = 1;

//게시글 수정 함수
function fn_updateQnaPost(board_idx) {
	
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

function fn_addFile() {
	
	var inputGroups = $("#fileList").find(".input-group");
	console.log("inputGroups.length: "+inputGroups.length);
	if(inputGroups.length >= 5) {
		alert("파일 업로드는 한 게시글 당 5개까지입니다.");
		return;
	}
	
	var inputGroupTag = 
		'<div class="input-group mb-1">'+
			'<div class="input-group-prepend">'+
				'<span class="input-group-text"><i class="fas fa-paperclip"></i></span>'+
			'</div>'+
			'<input type="text" name="fileName'+fileCnt+'"class="form-control bg-white" readonly>'+
			'<button class="btn btn-sm" name="cancel"><i class="fas fa-times-circle"></i></button>'+
			'<input type="file" name="file'+fileCnt+'" onchange="checkFile(this,'+fileCnt+')" style="display:none"/>'+
		'</div>';
	
	$("#fileList").append(inputGroupTag);
	
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


// 입력한 파일 삭제
function fn_deleteFile(obj){
	obj.parent().remove();
	
}

//취소 버튼
$("button[name=cancel]").on("click", function(e){ 
	e.preventDefault();
	fn_deleteFile($(this));
});


// 파일 유효성 체크
function checkFile(file, fileCnt){
	var fileInfo = file.files;
	var message = "해당 파일은 업로드 할 수 없습니다."
				+"\n - 업로드 가능한 파일 확장자 : hwp, doc, docx, ppt, pptx, xls, xlsx, txt, csv, jpg, jpeg, gif, png, bmp, pdf"
				+"\n - 업로드 파일크기 및 개수 : 5MB 이하, 1~5개";
	
	// 파일 유효성 검사 - 1. 파일크기는 5MB 이하
	if(fileInfo[0].size > 5242880){
		// 파일 크기가 5MB보다 큰 경우 - alert 후 파일 삭제
		alert(message);
		$("#fileDiv"+fileCnt).remove();
	} else{
		// 파일 크기가 5MB보다 작은 경우 - 2. 확장자 유효성 검사 진행
		var fileName = fileInfo[0].name;
		var fileLength = fileName.length;
		var fileDotIndex = fileName.lastIndexOf(".");
		var fileExtension = fileName.substring(fileDotIndex+1, fileLength).toLowerCase();
		
		// 업로드 가능한 확장자
		var allowedExtensions = 
			['hwp', 'doc', 'docx', 'ppt', 'pptx', 
			'xls', 'xlsx', 'txt', 'csv', 'jpg', 
			'jpeg', 'gif', 'png', 'bmp', 'pdf'];
		
		for(var i = 0; i < allowedExtensions.length; i++) {
			if(fileExtension == allowedExtensions[i]){
				// 유효한 확장자인 경우 - 함수 끝
				$("input[name=fileName"+fileCnt+"]").val(fileName);
				return;
			}
		}
		
		// 유효한 확장자가 아닌 경우 - alert 후 파일 삭제
		alert(message);
		$("#fileDiv"+fileCnt).remove();
	}
}