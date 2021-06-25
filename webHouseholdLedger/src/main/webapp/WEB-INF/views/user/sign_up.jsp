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
				<a href="#" class="h1"><b>웹 가계부</b></a>
			</div>
			<div class="card-body">
				<p class="login-box-msg">회원가입</p>

				<form action="#" method="post">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="아이디">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="email" class="form-control" placeholder="이메일">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" placeholder="비밀번호">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control"
							placeholder="비밀번호 다시입력">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<div class="icheck-primary">
								<input type="checkbox" id="remember">
									<label for="remember">
										프로모션 정보 수신(선택)에 동의합니다.
									</label>
							</div>
						</div>
						<!-- /.col -->
					</div>
				</form>

				<div class="text-center mt-2 mb-3">
					<a href="#" class="btn btn-block btn-danger">
						<i class="fab mr-2"></i> 정보입력 완료
					</a>
				</div>
				
				<div class="text-center mt-2 mb-3">
					<p>- OR -</p>
					<p class="mb-1">
       					<a href="forgot-password.html">계정이 이미 있습니까?</a>
      				</p>
      			</div>
			</div>
			<!-- /.form-box -->
			
		</div>
		<!-- /.card -->
	</div>
	<!-- /.register-box -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
</body>
</html>