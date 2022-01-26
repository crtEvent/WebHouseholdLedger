<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp"%>
<link rel="stylesheet" href="<c:url value="/resources/plugins/summernote/summernote-bs4.css"/>">
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/include_main_navbar.jsp"%>
		<%@include file="../include/include_main_sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>메일 쓰기</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<!-- Col: Left-Card -->
						<%@include file="./admin_mail_menu.jsp"%>
						<!-- /.col: left-card -->
						
						<!-- Col: Right-Card -->
						<div class="col-md-9">
							<div class="card card-primary card-outline">
							
              					<div class="card-header">
              		  				<h3 class="card-title">메세지</h3>
              					</div><!-- /.card-header -->
              					
              					<div class="card-body">
              						<div class="col-sm-6 text-center container">
              							<c:choose>
              								<c:when test="${mail_message eq 'success'}">
												<div class="info-box center-block">
													<span class="info-box-icon bg-info">
														<i class="far fa-paper-plane"></i>
													</span>
													<div class="info-box-content">
														<span class="info-box-number">메일을 성공적으로 보냈습니다.</span>
													</div>
												<!-- /.info-box-content -->
												</div>
              								</c:when>
              								<c:when test="${mail_message eq 'fail'}">
              									<div class="info-box center-block">
													<span class="info-box-icon bg-warning">
														<i class="far fa-envelope"></i>
													</span>
													<div class="info-box-content">
														<span class="info-box-number">메일 전송이 실패하였습니다.</span>
														<span class="info-box-number">다시시도해 주세요.</span>
													</div>
												<!-- /.info-box-content -->
												</div>
              								</c:when>
              							</c:choose>
              						</div>
              					</div> <!-- /.card-body -->
              					
              					<div class="card-footer">
                				</div><!-- /.card-footer -->
              					
            				</div>
            				<!-- /.card -->
						</div><!-- /.col: right-card -->
						
					</div><!-- /.row -->
      			</div><!-- /.container-fluid -->
			</section>
			<!-- /.main content -->

		</div>
		<!-- /.content-wrapper -->

		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/include_main_plugins.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/plugins/summernote/summernote-bs4.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/summernote/summernote-ko-KR.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/admin_mail_write.js"/>"></script>
</body>
</html>