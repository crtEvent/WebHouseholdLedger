<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 가계부 회원가입</title>
<%@include file="../include/include_main_head.jsp" %>
</head>
<body class="hold-transition register-page">
	<div class="register-box">
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<div class="h1"><b>회원가입</b></div>
			</div>
			<div class="card-body">

				<form action="<c:url value='/user/excute_signup.do'/>" name="signUpForm" id="signUpForm" method="post">
					<div class="input-group">
						<input type="text" class="form-control" name="user_id" id="user_id" placeholder="아이디(영문,숫자 조합 5-20자)" autocomplete="off">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="mb-3" id="msg_user_id">${valid_user_id }</div>
					
					<div class="input-group">
						<input type="email" class="form-control" name="user_email" id="user_email" placeholder="이메일" autocomplete="off">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="mb-3" id="msg_user_email">${valid_user_email }</div>
					
					<div class="input-group">
						<input type="password" class="form-control" name="user_password" id="user_password" placeholder="비밀번호(영문,숫자,특수문자 조합 5-25자)">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="mb-3" id="msg_user_pw">${valid_user_password }</div>
					
					<div class="input-group">
						<input type="password" class="form-control" name="user_password_check" id="user_password_check" placeholder="비밀번호 다시입력">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="mb-3" id="msg_user_pw_check">${valid_user_password_check }</div>
					
					<div class="row">
						<div class="col-12">
							<div class="icheck-primary">
								<input type="checkbox" id="remember" name="receive_mail" value="Y">
									<label for="remember">
										프로모션 정보 수신(선택)에 동의합니다.
									</label>
							</div>
						</div>
						<!-- /.col -->
					</div>
				</form>

				<div class="text-center mt-2 mb-3">
					<button type="submit" id="btn_submit" class="btn btn-block btn-danger">
						<i class="fab mr-2"></i> 정보입력 완료
					</button>
				</div>
				
				<div class="text-center mt-2 mb-3">
					<p>- OR -</p>
					<p class="mb-1">
       					<a href="<c:url value='/user/signin.do'/>">계정이 이미 있습니까?</a>
      				</p>
      			</div>
			</div>
			<!-- /.form-box -->
			
		</div>
		<!-- /.card -->
	</div>
	<!-- /.register-box -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
	
	<script type="text/javascript">
	var check_id = false;
	var check_pw = false;
	var check_ch_pw = false;
	var check_email = false;
	
	$('#btn_submit').click(function(e){
		e.preventDefault();
		if(check_id==true && check_pw==true && check_ch_pw==true && check_email==true){
			$('#signUpForm').submit();
		}else{
			alert("회원가입 Form을 올바르게 작성해 주세요.");
			return false;
		}
	})
	
	//user_id 중복체크 및 정책 검사
	$('#user_id').blur(function() {
		var user_id = {user_id : $('#user_id').val()};
		$.ajax({
			url: "<c:url value='/user/checkID.do'/>",
			type: 'get',
			data: user_id,
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
	})
	
	// user_pw 정책 검사
	$('#user_password').blur(function() {
		var user_pw = {user_pw : $('#user_password').val()};
		$.ajax({
			url: "<c:url value='/user/checkPW.do'/>",
			type: 'get',
			data: user_pw,
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
	})
	
	// user_pw_check 검사
	$('#user_password_check').blur(function() {
		if($('#user_password').val() != $('#user_password_check').val()){
			// user_pw 와 user_pw_check이 다른 경우
			$('#msg_user_pw_check').text('Password가 동일하지 않습니다..');
			check_ch_pw = false;
		}else{
			var user_pw_check = {user_pw : $('#user_password_check').val()};
			$.ajax({
				url: "<c:url value='/user/checkPW.do'/>",
				type: 'get',
				data: user_pw_check,
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
	})
	
	// user_email 정책 검사
	$('#user_email').blur(function() {
		var user_email = {user_email : $('#user_email').val()};
		$.ajax({
			url: "<c:url value='/user/checkEmail.do'/>",
			type: 'get',
			data: user_email,
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
	})
	</script>
	
</body>
</html>