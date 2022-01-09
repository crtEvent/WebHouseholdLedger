<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<%@include file="../include/include_main_head.jsp" %>
<!-- jsGrid -->
<link rel="stylesheet" href="<c:url value="/resources/plugins/jsGrid/jsgrid.min.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/plugins/jsGrid/jsgrid-theme.min.css"/>">
<!-- Date Range Picker -->
<link rel="stylesheet" href="<c:url value="/resources/plugins/daterangepicker/daterangepicker.css"/>">
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
						<div class="col-sm-6">
							<h1 class="m-0">회원 관리</h1>
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
					
					<!-- Search -->
					<div class="row">
						<div class="col-12">
							<!-- Card -->
							<div class="card">
								<!-- Card-body -->
								<div class="card-body">
									
									<!-- Search Bar -->
									<div class="row mb-3">
										<div class="col-sm-12">
											<div class="d-flex justify-content-center border-bottom">
												<div class="d-flex mb-3">
													<div class="">
														<select name="search_type" class="form-control">
															<option value="user_id">아이디</option>
															<option value="user_idx">인덱스</option>
														</select>
													</div>
													<div class="">
														<input type="text" name="keyword" class="form-control"
															placeholder="Search" required>
													</div>
													<div class="">
														<button type="button" class="btn btn-default"
																id="doSearchBtn" onclick="fn_searchUserList('normal')">
															<i class="fas fa-search"></i><b> 검색</b>
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- /.search bar -->
									
									<!-- Advanced Search -->
									<div class="row">
										<div class="col-sm-12">
											<div class="input-group col-sm-5 container">
												<div class="input-group-prepend">
													<span class="input-group-text">
														가&nbsp;&nbsp;입&nbsp;&nbsp;일&nbsp;&nbsp;
													</span>
												</div>
												<input type="text" class="form-control float-right text-center"
													id="selectPeriod">
												<input type="hidden" id="start_date" value="${start_date }">
												<input type="hidden" id="end_date" value="${end_date }">
											</div>
										</div>
									</div>
									<!-- /.advanced search -->
									
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.search -->
					
					<!-- Member Table -->
					<div class="row">
						<div class="col-12">
							<!-- Card -->
							<div class="card">
								<!-- Card-Header -->
								<div class="card-header">
								</div>
								<!-- /.card-header -->
								<!-- Card-body -->
								<div class="card-body">
								
									<div id="userTable"></div>
									
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.member table -->
					
				</div>
			</section>
			<!-- /.main content -->

		</div>
		<!-- /.content wrapper -->
		
		<%@include file="./admin_update_user_info_modal.jsp"%>
		<%@include file="../include/include_main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/admin_user_management.js"/>"></script>
	<!-- jsGrid -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jsGrid/jsgrid.min.js"/>"></script>
	<!-- Date Range Picker -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/daterangepicker/moment.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/daterangepicker/daterangepicker.js"/>"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/view/admin/edit_user_info_id.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/edit_user_info_email.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/edit_user_info_password.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/edit_user_info_receive_mail.js"/>"></script>
</body>
</html>