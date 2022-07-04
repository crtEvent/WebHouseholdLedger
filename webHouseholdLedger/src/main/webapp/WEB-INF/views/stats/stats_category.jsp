<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../include/include_main_head.jsp"%>
<!-- DataTables -->
<link rel="stylesheet" href="<c:url value="/resources/adminLTE/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css"/>">
<!-- bootstrap-datepicker -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.css"/>" />
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
							<h1 class="m-0">분류별 통계</h1>
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
				
					<!-- Row -->
					<div class="row">
						<div class="col-12">
							<!-- Card -->
							<div class="card">
								<!-- Card-body -->
								<div class="card-body">
									<div class="row">
									
										<div class="input-group w-25 mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text">
													<i class="far fa-calendar-alt"></i>
												</span>
											</div>
											<form name="selectMonthForm" id="selectMonthForm">
												<input type="text" name="date" id="selectMonth" class="form-control text-center"
													autocomplete="off" value="${fn:substring(statsByCategoryDTO.date,0,8) }">
											</form>
										</div>
										
									</div>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>	
					</div>
					<!-- /.row -->
					
					<!-- Row -->
					<div class="row">
						<div class="col-md-6">
							<!-- Card - INCOME PIE CHART -->
							<div class="card card-success card-outline">
								<!-- Card-header -->
								<div class="card-header">
									<h3 class="card-title">수입 통계</h3>
								</div>
								<!-- /.card-header -->
								<!-- Card-body -->
								<div class="card-body">
									<div class="pb-3">
										<canvas id="incomePieChart"
											style="min-height: 300px; height: 300px; max-height: 300px; max-width: 100%;">
										</canvas>
									</div>
									<div>
										<c:set var="totalIncomeAmount" value="0" />
										<c:set var="totalIncomeRatio" value="0" />
										<input type="hidden" name="incomeListSize" value="${fn:length(statsByCategoryDTO.statsByIncomeCategoryList) }">
										<table id="incomeDataTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th class="text-center">항목</th>
													<th class="text-center">금액(&#8361;)</th>
													<th class="text-center">비율(%)</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="statsIncome" 
													items="${statsByCategoryDTO.statsByIncomeCategoryList }">
													<tr>
														<td class="text-center">${statsIncome.CATEGORY_NAME }</td>
														<td class="text-right">${statsIncome.AMOUNT }</td>
														<td class="text-right">${statsIncome.RATIO }</td>
													</tr>
													<c:set var="totalIncomeAmount" value="${totalIncomeAmount + fn:replace(statsIncome.AMOUNT, ',', '') }" />
													<c:set var="totalIncomeRatio" value="${totalIncomeRatio + statsIncome.RATIO }" />
												</c:forEach>
											</tbody>
											<tfoot>
												<tr>
													<th class="text-center">합계</th>
													<th class="text-right"><c:out value="${totalIncomeAmount }"/></th>
													<th class="text-right">
														<fmt:formatNumber 
															value="${totalIncomeRatio+(1-(totalIncomeRatio%1))%1}" 
															pattern=".00" type="number" />
													</th>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card - income pie chart -->
						</div>
						<!-- /.col-md-6 (LEFT) -->

						<div class="col-md-6">
							<!-- Card - EXPENSES PIE CHART -->
							<div class="card card-danger card-outline">
								<div class="card-header">
									<h3 class="card-title">지출 통계</h3>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
									<div class="pb-3">
										<canvas id="expensesPieChart"
											style="min-height: 300px; height: 300px; max-height: 300px; max-width: 100%;">
										</canvas>
									</div>
									<div>
										<c:set var="totalExpensesAmount" value="0" />
										<c:set var="totalExpensesRatio" value="0" />
										<input type="hidden" name="expensesListSize" value="${fn:length(statsByCategoryDTO.statsByExpensesCategoryList) }">
										<table id="expensesDataTable" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th class="text-center">항목</th>
													<th class="text-center">금액(&#8361;)</th>
													<th class="text-center">비율(%)</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="statsExpenses" 
													items="${statsByCategoryDTO.statsByExpensesCategoryList }">
													<tr>
														<td class="text-center">${statsExpenses.CATEGORY_NAME }</td>
														<td class="text-right">${statsExpenses.AMOUNT }</td>
														<td class="text-right">${statsExpenses.RATIO }</td>
													</tr>
													<c:set var="totalExpensesAmount" value="${totalExpensesAmount + fn:replace(statsExpenses.AMOUNT, ',', '') }" />
													<c:set var="totalExpensesRatio" value="${totalExpensesRatio + statsExpenses.RATIO }" />
												</c:forEach>
											</tbody>
											<tfoot>
												<tr>
													<th class="text-center">합계</th>
													<th class="text-right"><c:out value="${totalExpensesAmount }"/></th>
													<th class="text-right">
														<fmt:formatNumber 
															value="${totalExpensesRatio+(1-(totalExpensesRatio%1))%1}" 
															pattern=".00" type="number" />
													</th>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card - expenses pie chart -->
						</div>
						<!-- /.col-md-6 (RIGHT) -->

					</div>
					<!-- /.row -->
					
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->

		</div>

		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/include_main_plugins.jsp"%>
	
	<script type="text/javascript" src="<c:url value="/resources/view/stats/stats_category.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/stats/stats_select_month.js"/>"></script>
	<!-- bootstrap-datepicker -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.ko.js"/>"></script>
	<!-- Chart.js -->
	<script src="<c:url value="/resources/adminLTE/plugins/chart.js/Chart.min.js"/>"></script>
	<script src="<c:url value="/resources/adminLTE/plugins/chart.js/chartjs-plugin-colorschemes.min.js"/>"></script>
	<!-- DataTables  & Plugins -->
	<script src="<c:url value="/resources/adminLTE/plugins/datatables/jquery.dataTables.min.js"/>"></script>
</body>
</html>