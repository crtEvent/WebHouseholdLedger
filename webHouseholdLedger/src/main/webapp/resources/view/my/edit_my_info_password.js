/**
 * [USER_PASSWORD 변경]
 */

let check_old_pw = false;
let check_pw = false;
let check_ch_pw = false;

/* old_user_pw 유효성 검사 */
function fn_validOldUserPassword() {
	let old_pw = $('#old_user_password').val();
	if(old_pw == '') {
		$('#msg_old_user_pw').text('기존 비밀번호를 입력해 주세요.');
		check_old_pw = false;
	}
	check_old_pw = true;
}

/* user_pw 유효성 검사 */
function fn_validUserPassword() {
	let dataToSend = {user_pw : $('#new_user_password').val()};
	$.ajax({
		url: '/app/user/checkPW.do',
		type: 'get',
		data: dataToSend,
		success: function(data){
			if(data == false){ // Password 정책 오류
				$('#msg_user_pw').text('Password는 영문, 숫자, 특수문자를 조합하여, 5자 이상 25자 이내로 적어주세요.');
				check_pw = false;
			} else if(data == true){ // Password 사용 가능
				$('#msg_user_pw').text('');
				check_pw = true;
			}
		},
		error: function(){
			alert("ajax_error.user_pw");
		}
	})
}

/* user_pw_check 유효성 검사 */
function fn_validUserPasswordCheck() {
	if($('#new_user_password').val() != $('#new_user_password_check').val()){
		// user_pw 와 user_pw_check이 다른 경우
		$('#msg_user_pw_check').text('Password가 동일하지 않습니다.');
		check_ch_pw = false;
	}else{
		let dataToSend = {user_pw : $('#new_user_password_check').val()};
		$.ajax({
			url: '/app/user/checkPW.do',
			type: 'get',
			data: dataToSend,
			success: function(data){
				if(data == false){ // Password 정책 오류
					$('#msg_user_pw_check').text('Password를 다시 입력해 주세요.');
					check_ch_pw = false;
				} else if(data == true){ // Password 사용 가능
					$('#msg_user_pw_check').text('');
					check_ch_pw = true;
				}
			},
			error: function(){
				alert("ajax_error.user_pw_check");
			}
		})
	}
}

/* old_user_pw 유효성 검사 */
$('#old_user_password').keyup(function() {
	fn_validOldUserPassword();
});

/* user_pw 유효성 검사 */
$('#new_user_password').keyup(function() {
	fn_validUserPassword();
	fn_validUserPasswordCheck();
});

/* user_pw_check 유효성 검사 */
$('#new_user_password_check').keyup(function() {
	fn_validUserPasswordCheck();
});

/* 비밀번호 수정 활성화 */
function fn_enableEditUserPassword() {
	
	let div_enableUserPassword = $('#div_enableUserPassword');
	let div_editUserPassword = $('#div_editUserPassword');
	
	// [비밀번호 수정 활성화 버튼] 삭제
	div_enableUserPassword.addClass('d-none');
	
	// [비밀번호 수정란] 생성
	div_editUserPassword.removeClass('d-none');
}

/* 비밀번호 수정 취소 */
function fn_cancelEditUserPassword() {
	
	$('#old_user_password').val('');
	$('#new_user_password').val('');
	$('#new_user_password_check').val('');
	$('#msg_old_user_pw').text('');
	$('#msg_user_pw').text('');
	$('#msg_user_pw_check').text('');
	
	let div_enableUserPassword = $('#div_enableUserPassword');
	let div_editUserPassword = $('#div_editUserPassword');
	
	// [비밀번호 수정란] 삭제
	div_editUserPassword.addClass('d-none');
	
	// [비밀번호 수정 활성화 버튼] 생성
	div_enableUserPassword.removeClass('d-none');
}

/* 비밀번호 수정 */
function fn_editUserPassword() {
	
	// old_user_pw 유효성 검사
	if(!check_old_pw) {
		fn_validOldUserPassword();
		return;
	}
	
	// new_user_pw 유효성 검사
	if(!check_pw) {
		fn_validUserPassword();
		return;
	}
	
	// new_user_pw_check 유효성 검사
	if(!check_ch_pw) {
		fn_validUserPasswordCheck();
		return;
	}
	
	// user_password update
	let dataToSend = {old_user_password : $('#old_user_password').val(), new_user_password : $('#new_user_password').val()};
	$.ajax({
		url: '/app/my/editUserPassword.do',
		type: 'post',
		data: dataToSend,
		success: function(data){
			
			if(data == true) {
				alert('비밀번호가 성공적으로 바뀌었습니다.');
				fn_cancelEditUserPassword();
			} else {
				$('#msg_old_user_pw').text('기존 비밀번호가 일치하지 않습니다. 다시 시도해 주세요.');
				check_old_pw = false;
				alert($('#msg_old_user_pw').text());
			}
			
		},
		error: function(){
			alert("아이디 변경에 실패하였습니다. 다시 시도해 주세요.");
			check_old_pw = false;
		}
	});
}
