<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp" %>
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/include_main_navbar.jsp" %>
		<%@include file="../include/include_main_sidebar.jsp" %>
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>메일 읽기</h1>
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

								<div class="card-body p-0" id="printArea">
								
									<div class="mailbox-read-info">
										<h5>${mailDTO.mail_subject}</h5>
										<h6>받는사람: ${mailDTO.mail_to}
										<span class="mailbox-read-time float-right">${malDTO.reg_date}</span></h6>
									</div>
									<!-- /.mailbox-read-info -->
									
									<div class="mailbox-controls with-border text-center">
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm" 
												data-container="body" title="Delete">
												<i class="far fa-trash-alt"></i>
											</button>
											<button type="button" class="btn btn-default btn-sm" 
												data-container="body" title="Reply" onclick="fn_linkToMail(${mailDTO.nextMail_idx})">
												<i class="fas fa-reply"></i>
											</button>
											<button type="button" class="btn btn-default btn-sm" 
												data-container="body" title="Forward" onclick="fn_linkToMail(${mailDTO.prevMail_idx})">
												<i class="fas fa-share"></i>
											</button>
										</div>
										<!-- /.btn-group -->
										<button type="button" class="btn btn-default btn-sm"
											 title="Print" onclick="printMail()">
											<i class="fas fa-print"></i>
										</button>
									</div>
									<!-- /.mailbox-controls -->
									
									<div class="mailbox-read-message">
										${mailDTO.mail_content}
									</div>
									<!-- /.mailbox-read-message -->
              
								</div>
								<!-- /.card-body -->

								<div class="card-footer">
									<div class="float-right">
										<button type="button" class="btn btn-default" onclick="fn_linkToMail(${mailDTO.nextMail_idx})">
											<i class="fas fa-reply"></i> 이전
										</button>
										<button type="button" class="btn btn-default" onclick="fn_linkToMail(${mailDTO.prevMail_idx})">
											<i class="fas fa-share"></i> 다음
										</button>
 									</div>
									<button type="button" class="btn btn-default"><i class="far fa-trash-alt"></i> 삭제</button>
									<button type="button" class="btn btn-default" onclick="printMail()">
										<i class="fas fa-print"></i> 인쇄
									</button>
								</div>
								<!-- /.card-footer -->
								
							</div>
						</div>
						
						
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.main content -->

		</div>
		<!-- /.content-wrapper -->
				
		
		<%@include file="../include/include_main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/admin_mail_read.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/print_mail.js"/>"></script>
</body>
</html>