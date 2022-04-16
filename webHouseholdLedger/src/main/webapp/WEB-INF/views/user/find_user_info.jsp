<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<%@include file="../include/include_main_head.jsp"%>
</head>
<body class="hold-transition login-page">

	<div class="login-box" style="width: 60%;">
		<div class="row">

			<div class="col-sm-6">
				<div class="card card-outline card-primary h-100" id="findIdCard">
					<div class="card-header text-center">
						<p class="h2 text-bold">아이디 찾기</p>
					</div>
					<div class="card-body">
						<p class="login-box-msg">회원가입시 등록한 이메일 주소를 입력해주세요.</p>
						
						<div class="input-group mb-3 py-4">
							<input type="email" name="user_email" class="form-control" placeholder="Email">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-envelope"></span>
								</div>
							</div>
						</div>
						<div class="input-group pb-1">
						</div>
						<div class="row">
							<div class="col-12">
								<button class="btn btn-primary btn-block" onclick="fn_findID()">아이디
									찾기</button>
							</div>
							<!-- /.col -->
						</div>
						
						<p class="mt-3 mb-1">
							<a href="<c:url value='/user/signin.do'/>">로그인</a>
						</p>
					</div>
					<!-- /.login-card-body -->
				</div>
			</div>
			<!-- /.col -->

			<div class="col-sm-6">
				<div class="card card-outline card-primary" id="findPwCard">
					<div class="card-header text-center">
						<p class="h2 text-bold">비밀번호 찾기</p>
					</div>
					<div class="card-body">
						<p class="login-box-msg">비밀번호를 찾기 위해 필요한 사항을 입력해 주십시오.</p>
						
						<div class="input-group mb-3">
							<input type="text" name="user_id" class="form-control"
								placeholder="아이디">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-lock"></span>
								</div>
							</div>
						</div>
						<div class="input-group mb-3">
							<input type="email" name="user_email" class="form-control"
								placeholder="이메일">
							<div class="input-group-append">
								<div class="input-group-text">
									<span class="fas fa-envelope"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<button class="btn btn-primary btn-block" onclick="fn_findPW()">
									비밀번호 찾기
								</button>
							</div>
							<!-- /.col -->
						</div>
						
						<p class="mt-3 mb-1">
							<a href="<c:url value='/user/signin.do'/>">로그인</a>
						</p>
					</div>
					<!-- /.login-card-body -->
				</div>
			</div>
			<!-- /.col -->

		</div>
	</div>
	
	<%@include file="./find_user_id_modal.jsp"%>
	<%@include file="../include/include_main_plugins.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/view/user/find_user_info.js"/>"></script>
</body>
</html>