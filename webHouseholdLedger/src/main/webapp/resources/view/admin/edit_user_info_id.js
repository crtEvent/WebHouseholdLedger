/**
 * 
 */
var check_id = false;
var user_id = "";

/* user_id 중복체크 및 유효성 검사 */
function fn_validUserID() {
	var dataToSend = {user_id : $('#user_id').val()};
	$.ajax({
		url: '/app/user/checkID.do',
		type: 'get',
		data: dataToSend,
		success: function(data){
			if(data == 0){ // ID 정책 오류
				$('#msg_user_id').text('ID는 영문, 숫자를 조합하여, 5자 이상 20자 이내로 적어주세요.');
				check_id = false;
			} else if(data == 1){ // ID 중복 오류
				$('#msg_user_id').text('중복된 아이디 입니다. 다시 입력해 주세요.');
				check_id = false;
			} else if(data == 2){ // 사용 가능
				$('#msg_user_id').text('');
				check_id = true;
			}
		},
		error: function(){
			alert("ajax_error.user_id");
		}
	})
}

// user_id 중복체크 및 유효성 검사
$('#user_id').keyup(function() {
	fn_validUserID();
})

/* 아이디 수정 활성화 */
function fn_enableEditUserId() {
	
	user_id =  $('#user_id').val();
	
	var div_userId = $('#div_userId');
	
	// [아이디 수정 활성화 버튼] 삭제 
	div_userId.children('div').remove();
	
	// [아이디 수정 취소 버튼] 생성
	div_userId.append('<div class="input-group-append" id="disableEditUserIdBtn" onclick="fn_cancelEditUserId()">'+
						'<span class="input-group-text">취소</span>'+
					  '</div>');
	// [아이디 수정 버튼] 생성
	div_userId.append('<div class="input-group-append" id="editUserIdBtn" onclick="fn_editUserId()">'+
						'<span class="input-group-text">변경</span>'+
					  '</div>');
	
	// readonly 해제
	$('#user_id').attr('readonly', false);
	
	// 현재 아이디 유효성 검사
	fn_validUserID();
}

/* 아이디 수정 취소 */
function fn_cancelEditUserId() {
	
	var div_userId = $('#div_userId');
	
	// [아이디 수정 취소 버튼, 아이디 수정 버튼] 삭제 
	div_userId.children('div').remove();
	
	// [아이디 수정 활성화 버튼] 생성
	div_userId.append('<div class="input-group-append" id="enableEditUserIdBtn" onclick="fn_enableEditUserId()">'+
						'<span class="input-group-text">변경</span>'+
					  '</div>');
	
	// readonly 설정, user_id 원래 값으로 변경
	$('#user_id').attr('readonly', true);
	$('#user_id').val(user_id);
	$('#msg_user_id').text('');
}

/* 아이디 수정 */
function fn_editUserId() {
	
	// user_id 유효성 검사
	fn_validUserID();
	
	if(!check_id) {
		alert($('#msg_user_id').text());
		return;
	}
	
	// user_id update
	var dataToSend = {user_id : $('#user_id').val(), user_idx: $('#user_idx').val()};
	$.ajax({
		url: '/app/admin/editUserID.do',
		type: 'post',
		data: dataToSend,
		success: function(data){
			alert('아이디가 성공적으로 바뀌었습니다.');
			// front 단에 변경된 user_id값을 넣어 준다
			$('#user_id').val(data);
			user_id = data;
			fn_cancelEditUserId();
			fn_loadJsGrid();
		},
		error: function(){
			alert("아이디 변경에 실패하였습니다. 다시 시도해 주세요.");
		}
	});
	
}