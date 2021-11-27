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
			['insert', ['link', 'picture']]
		],
		fontSizes: ['10', '11', '12', '14', '16', '18', '24', '36', '48'],
		callbacks: {
            onKeyup: function (e) {
            	fn_viewContentFieldLength();
            },
            onPaste: function (e) {
            	// 이미지 이중첨부 방지
            	var clipboardData = e.originalEvent.clipboardData;
				if (clipboardData && clipboardData.items && clipboardData.items.length) {
					var item = clipboardData.items[0];
					if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
						e.preventDefault();
					}
				}
            	// 글자수 표시
				fn_viewContentFieldLength();
            },
            onImageUpload : function(files) {
            	fn_uploadImageFile(files[0], this);
			},
			onImageLinkInsert: function(url) {
				if(!fn_checkImageNum()) {
					alert("이미지는 5개까지만 첨부 가능합니다.");
					return;
				}
				$img = $('<img>').attr({ src: url });
			    $('#writeContentField').summernote('insertNode', $img[0]);
			}
        }
	});
	
	fn_viewContentFieldLength();
});

// 페이지에 글자 수 보여주는 함수
function fn_viewContentFieldLength() {
	
	var contentFieldLength = $('#writeContentField').summernote('code')
	.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")
	.replace(/&([a-z0-9]+|#[0-9a-zA-Z]+);/gi, "0").length;
	
	if(contentFieldLength > 2000){
    	$('#maxContentLength').html('(<span class="text-danger">'+contentFieldLength+'자</span> / 최대 2,000자)');
    	return;
    }
    $('#maxContentLength').html('(<span>'+contentFieldLength+'자</span> / 최대 2,000자)');
}

// 게시글 유효성 검사(제목, 내용 글자수 제한)
function fn_checkPostWritingField() {
	var subject = $("input[name=subject]");
	var subjectLength = subject.val().replace(/s/g,"").length;
	var content = $("textarea[name=content]");
	
	if(subjectLength < 5 || subjectLength > 100){
		alert("게시글 제목은 5글자 이상 100글자 이하로 작성해 주세요.");
		subject.focus();
		return false;
	}
	if(content.val().length == 0){
		alert("게시글 내용을 작성해 주세요.");
		content.focus();
		return false;
	}

	var contentFieldLength = $('#writeContentField').summernote('code')
	.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")
	.replace(/&([a-z0-9]+|#[0-9a-zA-Z]+);/gi, "0").length;
	
	if(contentFieldLength > 2000){
		alert("게시글 내용은 2,000자를 초과할 수 없습니다.");
		content.focus();
		return false;
	}
	
	return true;
}

var fileCnt = 1;

// 첨부파일 추가
function fn_addFile() {
	
	var inputGroups = $("#fileList").find(".input-group");
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
			'<input type="file" name="file'+fileCnt+'" onchange="fn_checkFile(this,'+fileCnt+')" style="display:none"/>'+
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

// 첨부한 파일 삭제
function fn_deleteFile(obj){
	obj.parent().remove();
	
}

// 파일 취소 버튼
$("button[name=cancel]").on("click", function(e){ 
	e.preventDefault();
	fn_deleteFile($(this));
});

//파일 유효성 체크
function fn_checkFile(file, fileCnt){
	var fileInfo = file.files;
	var message = "해당 파일은 업로드 할 수 없습니다."
				+"\n - 업로드 가능한 파일 확장자 : hwp, doc, docx, ppt, pptx, xls, xlsx, txt, csv, jpg, jpeg, gif, png, bmp, pdf"
				+"\n - 업로드 파일크기 및 개수 : 5MB 이하, 1~5개";
	
	// 파일 유효성 검사 - 1. 파일크기는 5MB 이하
	if(fileInfo[0].size > 5242880){
		// 파일 크기가 5MB보다 큰 경우 - alert 후 파일 삭제
		alert(message);
		$("#fileDiv"+fileCnt).remove();
	} else {
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

var imageCnt = 1;

//이미지 업로드 함수
function fn_uploadImageFile(file, editor) {
	
	if(!fn_checkImageNum()) {
		alert("이미지는 5개까지만 첨부 가능합니다.");
		return;
	}
	
	// 이미지 파일 유효성 검사
	var fileInfo = file.files;
	var message = "해당 이미지 파일은 업로드 할 수 없습니다."
				+"\n - 업로드 가능한 이미지 파일 확장자 : jpg, jpeg, gif, png, bmp, pdf"
				+"\n - 업로드 이미지 파일 크기 및 개수 : 5MB 이하, 1~5개";
	
	// 파일 유효성 검사 - 1. 파일크기는 5MB 이하
	if(file.size > 5242880){
		// 파일 크기가 5MB보다 큰 경우 - alert 후 함수 종료
		alert(message);
		return;
	} else {
		// 파일 크기가 5MB보다 작은 경우 - 2. 확장자 유효성 검사 진행
		var fileName = file.name;
		var fileLength = fileName.length;
		var fileDotIndex = fileName.lastIndexOf(".");
		var fileExtension = fileName.substring(fileDotIndex+1, fileLength).toLowerCase();
		
		// 업로드 가능한 확장자
		var allowedExtensions = 
			['jpg', 'jpeg', 'gif', 'png', 'bmp', 'pdf'];
		
		for(var i = 0; i < allowedExtensions.length; i++) {
			if(fileExtension == allowedExtensions[i]) {
				// 유효한 확장자인 경우 - 이미지 파일 업로드 후 함수 종료
				data = new FormData();
				data.append("file", file);

				$.ajax({
					data : data,
					type : "post",
					url : "/app/qna/uploadImageFile.do",
					contentType : false,
					processData : false,
					success : function(data) {
						$(editor).summernote('insertImage', "/uploaded/qna_img/"+data.stored_file_name+data.extension);
					}
				});
				return;
			}
		}
		
		// 유효한 확장자가 아닌 경우 - alert 후 함수 종료
		alert(message);
		return;
	}
}// /.fn_uploadImageFile(file, editor) 이미지 업로드 함수 - 끝

// 미디어(이미지, 비디오) 개수 검사
function fn_checkMediaNum(tag) {
	
	var content = $('#writeContentField').summernote('code');
	var searchTag = tag;
	var countPosition = 0;
	var countMedia = 1;
	
	while(true) {
		var foundPosition = content.indexOf(searchTag, countPosition);
		if(foundPosition == -1) {
			break;
		}
		countPosition = foundPosition + 1;
		countMedia++;
	}
	if(countMedia > 5) {
		return false; // 미디어 5개 초과 flase
	} else{
		return true; // 미디어 5개 이하 true
	}
}

// 이미지 개수 검사
function fn_checkImageNum() {
	return fn_checkMediaNum("<img");
}

// 비디오 개수 검사
function fn_checkVideoNum() {
	return fn_checkMediaNum("<iframe");
}