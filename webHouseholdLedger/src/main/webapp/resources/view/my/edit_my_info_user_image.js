/**
 * [유저 이미지 변경]
 */

// 변경된 유저 이미지 저장 - DB에 저장
function fn_editUserImage() {
	
	let changedImage = document.getElementById("preview_user_image").src;
	
	$.ajax({
		url: "/app/my/editUserImage.do",
		type: 'post',
		data: {user_image : changedImage},
		success: function(result){
			alert('유저 이미지가 성공적으로 바뀌었습니다.');
			// front 단에 변경된 user_image값을 넣어 준다
			document.getElementById('sidebar_user_image_field').src = result;
			document.getElementById('user_image').src = result;
		},
		error: function(){
			alert("이미지 변경에 실패하였습니다. 다시 시도해 주세요.");
		}
	})
	
}

// 유저 이미지 변경 - 프론트 단에서 사진만 변경 / DB에 저장 X
function fn_changeUserImage() {
	
	let input = document.createElement('input');
	input.type = 'file';
	input.accept = 'image/*';
	input.name = "iput_user_image";
	input.click();
	
	input.onchange = function(event) {
		if(!fn_checkFile(event)){
			return;
		} else {
			fn_encodeImageToBase64(event.target.files[0]);
		}
	};
	
}

// 이미지 파일 -> base64 인코딩
function fn_encodeImageToBase64(file) {
	let reader = new FileReader(file);
	console.log("reader: "+reader);
	
	reader.onload = function() {
	  let result = reader.result;
	  console.log("result: "+result);
	  document.getElementById("preview_user_image").src = result;
	}
	
	if (file) {
	    reader.readAsDataURL(file);
	}
}

// 이미지 파일 유효성 체크
function fn_checkFile(file) {
	let fileInfo = file.target.files[0];
	const message = '해당 파일은 업로드 할 수 없습니다.' +
					'\n ※ 업로드 가능한 파일 크기: 1MB이하.' +
					'\n ※ 업로드 가능한 파일 확장자 : jpg, jpeg, png, bmp';
	
	// 파일 유효성 검사 - 1. 파일크기는 1MB 이하
	if(fileInfo.size > 1048576){
		// 파일 크기가 5MB보다 큰 경우 - alert 후 false 반환
		alert(message);
		return false;
	} else {
		// 파일 크기가 5MB보다 작은 경우 - 2. 확장자 유효성 검사 진행
		let fileName = fileInfo.name;
		let fileLength = fileName.length;
		let fileDotIndex = fileName.lastIndexOf(".");
		let fileExtension = fileName.substring(fileDotIndex+1, fileLength).toLowerCase();
		
		// 업로드 가능한 확장자
		const allowedExtensions = ['jpg', 'jpeg', 'png', 'bmp'];
		
		for(var i = 0; i < allowedExtensions.length; i++) {
			if(fileExtension == allowedExtensions[i]){
				// 유효한 확장자인 경우 - true 반환
				return true;
			}
		}
		
		// 일치하는 확장자가 없는 경우 - false 반환
		alert(message);
		return false;
	}
}

// [Modal]: Modal 열기
function fn_openUserImageModal() {
	$("#edit_user_image_modal").modal("show");
}

// [Modal]: Modal 닫기
function fn_closeModal() {
	$("#edit_user_image_modal").modal("hide");
}