<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 가계부 로그인</title>
<%@include file="../include/include_main_head.jsp" %>
</head>
<body class="hold-transition login-page">

	<div class="login-box">
		<!-- /.login-logo -->
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<a href="#" class="h1"><b>웹 가계부</b></a>
			</div>
			<div class="card-body">
				<p class="login-box-msg">로그인 하세요.</p>

				<form action="<c:url value='/user/excute_signin.do'/>" name="signInForm" id="signInForm" method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="user_id" id="user_id" placeholder="아이디">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" name="user_password" id="user_password" placeholder="비밀번호">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" id="remember"> <label
									for="remember"> 아이디/비밀번호 저장 </label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-4">
							<button type="submit" id="btn_submit" class="btn btn-primary btn-block">로그인</button>
						</div>
						<!-- /.col -->
						<div class="col-12 text-center text-danger">${signInError }</div>
					</div>
				</form>

				<div class="text-center mt-2 mb-3">
					<p>- OR -</p>
					<a href="#" class="btn btn-block btn-primary">
						<i class="fab mr-2"></i> 아이디/비밀번호 찾기
					</a>
					<a href="<c:url value='/user/signup.do'/>" class="btn btn-block btn-danger">
						<i class="fab mr-2"></i> 회원가입 하기
					</a>
				</div>

			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.login-box -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
	<script type="text/javascript">
	
	$('#btn_submit').click(function(e){
		e.preventDefault();
		
		var check_id = $('#user_id').val();
		var check_pw = $('#user_password').val();
		
	 	if(check_id=='' || check_id==null){
			alert("아이디를 입력해 주세요.");
			return false;
		}else if(check_pw=='' || check_pw==null){
			alert("비밀번호를 입력해 주세요.");
			return false;
		}else{
			$('#signInForm').submit();
		}
	})
	
	</script>
</body>
</html>