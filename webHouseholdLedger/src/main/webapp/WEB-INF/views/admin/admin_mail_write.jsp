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
              		  				<h3 class="card-title">새 메일 작성</h3>
              					</div><!-- /.card-header -->
              					
              					<div class="card-body">
              						<div class="input-group mb-2">
              							<div class="input-group-prepend">
              								<button type="button" class="btn btn-default">받는사람</button>
              							</div>
              							<input class="form-control" name="mail_to" placeholder="To:">
              						</div>
              						<div class="input-group mb-2">
              							<div class="input-group-prepend">
              								<button type="button" class="btn btn-default">제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</button>
              							</div>
              							<input class="form-control" name="mail_subject" placeholder="Subject:">
              						</div>
              						
                					<div class="form-group">
                    					<textarea id="writeContentField" name="mail_content" 
                    						class="form-control" style="height: 300px"></textarea>
                					</div>
                					
                					<div class="form-group">
                						<div class="float-right" id="maxContentLength">(0 / 최대 2,000자)</div>
                					</div>
              					</div> <!-- /.card-body -->
              					
              					<div class="card-footer">
                					<div class="float-right">
                  						<button type="button" class="btn btn-default"><i class="far fa-file-alt"></i> 양식 저장</button>
                  						<button type="submit" class="btn btn-primary" onclick="fn_sendMail('SENT')"><i class="far fa-envelope"></i> 보내기</button>
                					</div>
                					<button type="reset" class="btn btn-default"><i class="fas fa-times"></i> 리셋</button>
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