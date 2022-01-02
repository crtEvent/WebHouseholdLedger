/**
 * [USER_EMAIL 변경]
 */

var check_email = false;
var user_email = $('#user_email').val();

/* user_email 중복체크 및 유효성 검사 */
function fn_validUserEmail() {
	var dataToSend = {user_email : $('#user_email').val()};
	$.ajax({
		url: '/app/user/checkEmail.do',
		type: 'get',
		data: dataToSend,
		success: function(data){
			if(data == false){ // Email 형식 오류
				$('#msg_user_email').text('Email형식이 올바르지 않습니다.');
				check_email = false;
			} else if(data == true){ // Email 사용 가능
				$('#msg_user_email').text('');
				check_email = true;
			}
		},
		error: function(){
			alert("ajax_error.email");
		}
	})
}

/* user_email 중복체크 및 유효성 검사 */
$('#user_email').keyup(function() {
	fn_validUserEmail();
})

/* 이메일 수정 활성화 */
function fn_enableEditUserEmail() {
	
	var div_userEmail = $('#div_userEmail');
	
	// [이메일 수정 활성화 버튼] 삭제 
	div_userEmail.children('div').remove();
	
	// [이메일 수정 취소 버튼] 생성
	div_userEmail.append('<div class="input-group-append" id="disableEditUserEmailBtn" onclick="fn_cancelEditUserEmail()">'+
						'<span class="input-group-text">취소</span>'+
					  '</div>');
	// [이메일 수정 버튼] 생성
	div_userEmail.append('<div class="input-group-append" id="editUserEmailBtn" onclick="fn_editUserEmail()">'+
						'<span class="input-group-text">변경</span>'+
					  '</div>');
	
	// readonly 해제
	$('#user_email').attr('readonly', false);
	
	// 현재 아이디 유효성 검사
	fn_validUserEmail();
	
}

/* 이메일 수정 취소 */
function fn_cancelEditUserEmail() {
	
	var div_userEmail = $('#div_userEmail');
	
	// [이메일 수정 취소 버튼, 이메일 수정 버튼] 삭제 
	div_userEmail.children('div').remove();
	
	// [이메일 수정 활성화 버튼] 생성
	div_userEmail.append('<div class="input-group-append" id="enableEditUserEmailBtn" onclick="fn_enableEditUserEmail()">'+
						'<span class="input-group-text">변경</span>'+
					  '</div>');
	
	// readonly 설정, user_email 원래 값으로 변경
	$('#user_email').attr('readonly', true);
	$('#user_email').val(user_email);
	$('#msg_user_email').text('');
}

/* 이메일 수정 */
function fn_editUserEmail() {
	
	// user_email 유효성 검사
	fn_validUserEmail();
	
	if(!check_email) {
		alert($('#msg_user_email').text());
		return;
	}
	
	// user_id update
	var dataToSend = {user_email : $('#user_email').val()};
	$.ajax({
		url: '/app/my/editUserEmail.do',
		type: 'post',
		data: dataToSend,
		success: function(data){
			alert('이메일 주소가 성공적으로 바뀌었습니다.');
			// front 단에 변경된 user_email값을 넣어 준다
			$('#user_email').val(data);
			user_email = data;
			fn_cancelEditUserEmail();
		},
		error: function(){
			alert("이메일 주소 변경에 실패하였습니다. 다시 시도해 주세요.");
		}
	});
	
}