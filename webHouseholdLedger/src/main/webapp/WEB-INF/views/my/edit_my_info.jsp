<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp" %>
<link rel="stylesheet" href="<c:url value="/resources/view/my/css/edit_my_info.css" />">
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/include_main_navbar.jsp" %>
		<%@include file="../include/include_main_sidebar.jsp" %>
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-lg-6">
							<h1 class="m-0">가계부</h1>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">

					<!--  -->
					<div class="row">
						<div class="col-12">
							<!-- Card -->
							<div class="card card-primary card-outline">
								
								<div class="card-header">
										<h3 class="card-title">회원정보 수정</h3>
								</div>
									
								<!-- Card-body -->
								<div class="card-body">
								
									<!-- 유저 이미지 -->
									<div class="row">
										<div class="col-lg-12">
											<div class="col-lg-4 container">
												<div class="my-frame">
													<div class="my-img-wrapper" onclick="fn_openUserImageModal()">
														<img class="img-circle" id="user_image" src="${userSession.getUser_image() }"/>
														<div class="my-darkness img-circle"></div>
														<div class="my-btn-plus"><span draggable="false"><i class="fas fa-camera"></i></span></div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<!-- 아이디 -->
									<div class="row">
										<div class="col-lg-12">
											<div class="col-lg-4 container">
												<label class="col-form-label" for="user_id">
													 아이디
												</label>
											</div>
											<div class="input-group col-lg-4 container mb-1" id="div_userId">
												<input type="text" class="form-control" 
													name="user_id" id="user_id" 
													value="${userSession.getUser_id() }" 
													readonly="readonly" autocomplete="off">
												<div class="input-group-append" id="enableEditUserIdBtn" onclick="fn_enableEditUserId()">
													<span class="input-group-text">변경</span>
												</div>
											</div>
											<div class="col-lg-4 container mb-3" id="msg_user_id">${valid_user_id }</div>
										</div>
									</div>
									
									<!-- 이메일 -->
									<div class="row">
										<div class="col-lg-12">
											<div class="col-lg-4 container">
												<label class="col-form-label" for="user_email">
													 이메일
												</label>
											</div>
											<div class="input-group col-lg-4 container mb-1" id="div_userEmail">
												<input type="text" class="form-control" 
													name="user_email" id="user_email" 
													value="${userSession.getUser_email() }" 
													readonly="readonly" autocomplete="off">
												<div class="input-group-append" id="enableEditUserEmailBtn" onclick="fn_enableEditUserEmail()">
													<span class="input-group-text">변경</span>
												</div>
											</div>
											<div class="col-lg-4 container mb-3" id="msg_user_email">${valid_user_email }</div>
										</div>
									</div>
									
									<!-- 비밀번호 -->
									<div class="row">
										<div class="col-lg-12 mb-3">
											<div class="col-lg-4 container">
												<label class="col-form-label" for="user_password">
													 비밀번호
												</label>
											</div>
											<div class="col-lg-4 container" id="div_enableUserPassword">
												<button class="btn btn-default btn-block" onclick="fn_enableEditUserPassword()">비밀번호 변경</button>
											</div>
											<div class="col-lg-4 container d-none" id="div_editUserPassword">
												<input type="password" class="form-control mb-2" id="old_user_password" placeholder="현재 비밀번호">
												<div class="container mb-2" id="msg_old_user_pw"></div>
												<input type="password" class="form-control mb-1" id="new_user_password" placeholder="새 비밀번호(영문,숫자,특수문자 조합 5-25자)">
												<div class="container mb-2" id="msg_user_pw"></div>
												<input type="password" class="form-control mb-1" id="new_user_password_check" placeholder="새 비밀번호 다시입력">
												<div class="container mb-2" id="msg_user_pw_check"></div>
												<div class="btn-group container">
													<button class="btn btn-default" onclick="fn_editUserPassword()"><i class="fas fa-check-circle"></i> 변경</button>
													<button class="btn btn-default" onclick="fn_cancelEditUserPassword()"><i class="fas fa-times-circle"></i> 취소</button>
												</div>
											</div>
										</div>
									</div>
									
									<!-- 이메일 수신 동의 -->
									<div class="row">
										<div class="col-12">
											<div class="col-lg-4 container">
												<label class="col-form-label">
													 이메일 수신 동의
												</label>
											</div>
											<div class="col-lg-4 container icheck-primary">
												<input type="checkbox" id="receive_mail" value="Y" ${userSession.getReceive_mail() == "Y" ? "checked" : ""}>
													<label for="receive_mail">
														프로모션 정보 수신(선택)에 동의합니다.
													</label>
											</div>
										</div>
									</div>
									
								</div><!-- /.card-body -->
							</div><!-- /.card -->
						</div><!-- /.col-12 -->
					</div><!-- /.row -->
					
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content wrapper -->
		<%@include file="./edit_user_image_modal.jsp"%>
		<%@include file="../include/include_main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
	<script type="text/javascript" src="<c:url value="/resources/view/my/edit_my_info_id.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/my/edit_my_info_email.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/my/edit_my_info_password.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/my/edit_my_info_receive_mail.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/my/edit_my_info_user_image.js"/>"></script>
</body>
</html>