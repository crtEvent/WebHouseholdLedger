/**
 * 
 */

function fn_findID() {
	
	// 이메일 유효성 검사
	let emailInput = $('#findIdCard').find('input[name=user_email]');
	if(emailInput.val().length == 0) {
		alert("이메일을 입력해 주세요");
		emailInput.focus();
		return;
	}
	
	// 아이디 List 조회
	$.ajax({
		url: '/app/user/findUserID.do',
		type: 'post',
		data: {user_email : emailInput.val()},
		success: function(data){
			if(!data) {
				alert("입력하신 이메일과 일치하는 계정이 없습니다. 이메일을 다시 한번 확인해 주세요.");
			} else {
				// 이전 데이터 삭제
				let $tbody = $('#find-user-id-modal').find('tbody');
				$tbody.empty();
				
				// Modal창 열기
				$("#find-user-id-modal").modal("show");
				
				// List 뿌려주기
				$(data).each(function(){
					$tr = '<tr><td class="text-center">'+this.USER_ID+'</td></tr>';
					$tbody.append($tr);
				}); // each 끝
			}
		},
		error: function(){
			alert("서버에서 응답이 없습니다. 잠시 후 다시 시도해 주세요.");
		}
	}); // ajax 끝
}

function fn_findPW() {
	
	// 아이디 유효성 검사
	let idInput = $('#findPwCard').find('input[name=user_id]');
	if(idInput.val().length == 0) {
		alert("아이디를 입력해 주세요");
		idInput.focus();
		return;
	}
	
	// 이메일 유효성 검사
	let emailInput = $('#findPwCard').find('input[name=user_email]');
	if(emailInput.val().length == 0) {
		alert("이메일을 입력해 주세요");
		emailInput.focus();
		return;
	}
	
	// 아이디,이메일 확인
	$.ajax({
		url: '/app/user/findUserPW.do',
		type: 'post',
		data: {user_id : idInput.val(), user_email : emailInput.val()},
		success: function(result){
			if(result) {
				alert("입력하신 이메일로 임시 비밀번호를 발송하였습니다.");
			} else {
				alert("입력하신 정보와 일치하는 계정이 없습니다. 입력하신 정보를 다시 한번 확인해 주세요.");
			}
			
		},
		error: function(){
			alert("서버에서 응답이 없습니다. 잠시 후 다시 시도해 주세요.");
		}
	}); // ajax 끝
}

/* Modal창 닫기 */
function fn_closeModal(){
	$("#find-user-id-modal").modal("hide");
}