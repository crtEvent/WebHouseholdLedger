<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp"%>
<!-- fullCalendar -->
 <link rel="stylesheet" href="<c:url value="/resources/adminLTE/plugins/fullcalendar/main.css"/>">
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/include_main_navbar.jsp"%>
		<%@include file="../include/include_main_sidebar.jsp"%>

		<!-- Content Wrapper -->
		<div class="content-wrapper">

			<!-- Content Header -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>달력</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content header -->

			<!-- Main content -->
			<section class="content">

				<!-- Row -->
				<div class="row">
					<div class="col-12">
						<!-- Card -->
						<div class="card">
							<!-- Card-body -->
							<div class="card-body">
								<div class="row">
									<div class="btn-group col-md-5" style="margin: 0 auto">
										<button type="button" class="btn btn-default"
											onclick="fn_openInsertModal()">가계부 입력</button>
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
						<!-- Card -->
						<div class="card">
							<!-- Card-body -->
							<div class="card-body p-0">

								<!-- Calendar div-->
								<div id="calendar" class="fc fc-ltr fc-unthemed">
									
									<!-- Insert ledger & Select period -->
									<div class="fc-toolbar fc-header-toolbar">
										<div class="fc-left">
											<div class="fc-button-group">
												<button type="button"
													class="fc-prev-button fc-button fc-button-primary"
													id="btnPrevMonth">
													<span class="fc-icon fc-icon-chevron-left"></span>
												</button>
												<button type="button"
													class="fc-next-button fc-button fc-button-primary"
													id="btnNextMonth">
													<span class="fc-icon fc-icon-chevron-right"></span>
												</button>
											</div>
										</div>
										<div class="fc-center">
											<h2 id="todayTitle">${fn:substring(ledgerCalendarDTO.date,0,7) }</h2>
										</div>
										<div class="fc-right">
											<div class="input-group">
												<form name="selectMonth" id="selectMonth">
													<input type="month" name="date" class="form-control"
														value="${fn:substring(ledgerCalendarDTO.date,0,7) }">
												</form>
												<div class="input-group-append" id="submitSelectMonth">
													<span class="input-group-text">이동</span>
												</div>
											</div>
										</div>
									</div>
									<!-- /.insert ledger & select period -->
									
									<!-- Calendar table-->
									<table>
										<thead class="fc-head">
											<tr>
												<th class="fc-day-header fc-widget-header fc-sun"><span>일</span></th>
												<th class="fc-day-header fc-widget-header fc-mon"><span>월</span></th>
												<th class="fc-day-header fc-widget-header fc-tue"><span>화</span></th>
												<th class="fc-day-header fc-widget-header fc-wed"><span>수</span></th>
												<th class="fc-day-header fc-widget-header fc-thu"><span>목</span></th>
												<th class="fc-day-header fc-widget-header fc-fri"><span>금</span></th>
												<th class="fc-day-header fc-widget-header fc-sat"><span>토</span></th>
											</tr>
										</thead>
										<tbody class="fc-body">
											<c:set var="i" value="0" />
											<c:set var="j" value="7" />
											<c:forEach var="row" items="${ledgerCalendarDTO.calendarDateGroup }">

												<c:if test="${i%j == 0 }">
													<tr style="height: 86px;">
												</c:if>

												<c:choose>
													<c:when test="${empty row.COLOR }">
														<td>
													</c:when>
													<c:when test="${row.COLOR eq 'GRAY'}">
														<td style="background: #EDEDED;">
													</c:when>
												</c:choose>

												<div class="fc-day-number">${fn:substring(row.DATE,8,10) }</div>

												<c:if test="${not empty row.CNT }">
													<a
														class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"
														style="background-color: rgba(0, 0, 0, 0.0); border-color: rgba(0, 0, 0, 0.0); color: green; text-align: right">
														<span class="fc-time">+</span> <span class="fc-title">${row.INCOME }</span>
													</a>
													<a
														class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable"
														style="background-color: rgba(0, 0, 0, 0.0); border-color: rgba(0, 0, 0, 0.0); color: red; text-align: right">
														<span class="fc-time">-</span> <span class="fc-title">${row.EXPENSES }</span>
													</a>
												</c:if>

												</td>

												<c:if test="${i%j == j-1 }">
													</tr>
												</c:if>

												<c:set var="i" value="${i+1 }" />

											</c:forEach>

										</tbody>
									</table>
									<!-- /.calendar table-->

								</div>
								<!-- /.calendar div-->

							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
				</div>
				<!-- /.row -->

			</section>
			<!-- /.main content -->

		</div>
		<!-- /.content wrapper -->
		
		<%@include file="./ledger_insert_modal.jsp"%>
		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/include_main_plugins.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/view/ledger/ledger_modal.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/ledger/ledger_select_calendar.js"/>"></script>
	
	<!-- daterange picker -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
</body>
</html>