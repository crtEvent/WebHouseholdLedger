<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="../include/include_main_head.jsp" %>
<!-- bootstrap-datepicker -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.css"/>" />
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
							<h1 class="m-0">연간 통계</h1>
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
											<form name="selectYearForm" id="selectYearForm">
												<input type="text" name="date" id="selectYear" class="form-control text-center"
													autocomplete="off" value="${fn:substring(statsYearlyDTO.date,0,5) }">
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
						<div class="col-12">
							<!-- Card - YEARLY BAR CHART -->
							<div class="card card-info">
								<!-- Card-header -->
								<div class="card-header">
									<h3 class="card-title">연간 통계</h3>
								</div>
								<!-- /.card-header -->
								<!-- Card-body -->
								<div class="card-body">
									<div class="pb-3">
										<canvas id="yearlyBarChart"
											style="min-height: 150px; height: 600px; max-height: 600px; max-width: 100%;">
										</canvas>
										<div>
											<c:set var="statsYearlyByIncome" value="${statsYearlyDTO.statsYearlyByIncome }"/>
											<c:set var="statsYearlyByExpenses" value="${statsYearlyDTO.statsYearlyByExpenses }"/>
											
											<table id="yearlyDataTable" class="table table-bordered table-hover">
												<thead>
													<tr>
														<th class="text-center">월</th>
														<th class="text-center">수입(&#8361;)</th>
														<th class="text-center">지출(&#8361;)</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="i" begin="1" end="12" step="1">
														<c:set var="key">${i }</c:set>
														<tr>
															<td class="text-center">${i }월</td>
															<td class="text-right">
																<c:choose>
																	<c:when test="${not empty statsYearlyByIncome[key] }">
																		${statsYearlyByIncome[key] }
																	</c:when>
																	<c:otherwise>0</c:otherwise>
																</c:choose>
															</td>
															<td class="text-right">
																<c:choose>
																	<c:when test="${not empty statsYearlyByExpenses[key] }">
																		${statsYearlyByExpenses[key] }
																	</c:when>
																	<c:otherwise>0</c:otherwise>
																</c:choose>
															</td>
														</tr>
													</c:forEach>
												</tbody>
												<tfoot>
													<tr>
														<td class="text-center">합계</td>
														<td class="text-right">
															<c:choose>
																<c:when test="${not empty statsYearlyByIncome['TOTAL_YEARLY'] }">
																	${statsYearlyByIncome['TOTAL_YEARLY'] }
																</c:when>
																<c:otherwise>0</c:otherwise>
															</c:choose>
														</td>
														<td class="text-right">
															<c:choose>
																<c:when test="${not empty statsYearlyByExpenses['TOTAL_YEARLY'] }">
																	${statsYearlyByExpenses['TOTAL_YEARLY'] }
																</c:when>
																<c:otherwise>0</c:otherwise>
															</c:choose>
														</td>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card - yearly bar chart -->
						
						</div>
					</div>
					<!-- /.row -->
					
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.main content -->

		</div>
		
		<%@include file="../include/include_main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
	<script type="text/javascript" src="<c:url value="/resources/view/stats/stats_select_year.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/stats/stats_yearly.js"/>"></script>
	<!-- bootstrap-datepicker -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.ko.js"/>"></script>
	<!-- Chart.js -->
	<script src="<c:url value="/resources/adminLTE/plugins/chart.js/Chart.min.js"/>"></script>
	<script src="<c:url value="/resources/adminLTE/plugins/chart.js/chartjs-plugin-colorschemes.min.js"/>"></script>
</body>
</html>