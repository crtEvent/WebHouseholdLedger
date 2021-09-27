<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp"%>
<!-- DataTables -->
<link rel="stylesheet" href="<c:url value="/resources/adminLTE/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css"/>">
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
							<h1 class="m-0">통계</h1>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">

						<div class="col-md-6">
							<!-- DONUT CHART -->
							<div class="card card-danger card-outline">
								<div class="card-header">
									<h3 class="card-title">지출 통계</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<div class="pb-3">
										<canvas id="expensesDonutChart"
											style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;">
										</canvas>
									</div>
									<div>
										<table id="expensesDataTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>항목</th>
													<th>비율</th>
													<th>금액</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>q</td>
													<td>w</td>
													<td>e</td>
												</tr>
											</tbody>
											<tfoot>
												<tr>
													<th>합계</th>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col-md-6 (LEFT) -->

						<div class="col-md-6">
							<!-- DONUT CHART -->
							<div class="card card-success card-outline">
								<div class="card-header">
									<h3 class="card-title">수입 통계</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<div>
										<canvas id="incomeDonutChart"
											style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;">
										</canvas>
									</div>
									<div>
										<table id="incomeDataTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>항목</th>
													<th>비율</th>
													<th>금액</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>r</td>
													<td>t</td>
													<td>y</td>
												</tr>
											</tbody>
											<tfoot>
												<tr>
													<th>합계</th>
													<th></th>
													<th></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
						<!-- /.col-md-6 (RIGHT) -->

					</div>
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->

		</div>

		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/include_main_plugins.jsp"%>
	<!-- DataTables  & Plugins -->
	<script src="<c:url value="/resources/adminLTE/plugins/datatables/jquery.dataTables.min.js"/>"></script>
</body>
</html>