<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp"%>
<!-- daterange picker -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/include_main_navbar.jsp"%>
		<%@include file="../include/include_main_sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
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

					<!-- Insert ledger & Select period -->
					<div class="row">
						<div class="col-12">
							<!-- Card -->
							<div class="card">
								<!-- Card-body -->
								<div class="card-body">
									<form name="selectPeriodForm" id="selectPeriodForm">
										<input type="hidden" name="start_date" id="start_date">
										<input type="hidden" name="end_date" id="end_date">
									</form>

									<div class="row">
										<div class="btn-group col-md-5 container">
											<button type="button" class="btn btn-default"
												onclick="fn_openInsertModal()">가계부 입력</button>
										</div>
									</div>

									<div class="row">
										<div class="btn-group col-md-5 container">
											<button type="button" id="btnOneMnth" class="btn btn-default">1개월</button>
											<button type="button" id="btnThreeMnth"
												class="btn btn-default">3개월</button>
											<button type="button" id="btnSixMnth" class="btn btn-default">6개월</button>
										</div>
									</div>

									<div class="row">
										<div class="form-group col-md-5 container">
											<div class="input-group">
												<div class="input-group-prepend">
													<span class="input-group-text"> <i
														class="far fa-calendar-alt"></i>
													</span>
												</div>
												<input type="text"
													class="form-control float-right text-center"
													id="selectPeriod">
											</div>
										</div>
									</div>

								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.insert ledger & select period -->

					<!-- Ledger details -->
					<div class="row">
						<div class="col-12">

							<!-- Card -->
							<div class="card">

								<!-- Card-Header -->
								<div class="card-header">
									<div class="row">
										<div class="col-lg-4">
											<div class="input-group form-group-lg">
												<div class="input-group-prepend">
													<button type="button" class="btn btn-success">수입</button>
												</div>
												<input type="text" class="form-control"
													value="${ledgerDetailDTO.ledgerSummary.INCOME }" readonly>
											</div>
										</div>

										<div class="col-lg-4">
											<div class="input-group form-group-lg">
												<div class="input-group-prepend">
													<button type="button" class="btn btn-danger">지출</button>
												</div>
												<input type="text" class="form-control"
													value="-${ledgerDetailDTO.ledgerSummary.EXPENSES }"
													readonly>
											</div>
										</div>

										<div class="col-lg-4">
											<div class="input-group form-group-lg">
												<div class="input-group-prepend">
													<button type="button" class="btn btn-primary">합계</button>
												</div>
												<input type="text" class="form-control"
													value="${ledgerDetailDTO.ledgerSummary.TOTAL }" readonly>
											</div>
										</div>
									</div>
								</div>
								<!-- /.card-header -->

								<!-- Card-body -->
								<div class="card-body p-0">
									<c:choose>
										<c:when test="${fn:length(ledgerDetailDTO.ledgerGroup) > 0}">
											<c:forEach var="row_head"
												items="${ledgerDetailDTO.ledgerGroup }" varStatus="status">
												<table class="table">
													<thead>
														<tr class="border-top text-bold text-lg">
															<th style="width: 50%">${row_head.DATE}</th>
															<th style="width: 25%; text-align: right;">수입:
																${row_head.INCOME}</th>
															<th style="width: 25%; text-align: right;">지출:
																${row_head.EXPENSES}</th>
														</tr>
													</thead>
												</table>
												<table class="table table-valign-middle">
													<tbody>
														<c:forEach var="row_body"
															items="${ledgerDetailDTO.ledgerList[status.index] }">
															<tr class="border-bottom">
																<input type="hidden" id="ledger_idx" value="${row_body.LEDGER_IDX }">
																<input type="hidden" id="date" value="${row_body.DATE }">
																<input type="hidden" id="income_and_expenses" value="${row_body.INCOME_AND_EXPENSES }">
																<input type="hidden" id="category" value="${row_body.CATEGORY }">
																<input type="hidden" id="description" value="${row_body.DESCRIPTION }">
																<input type="hidden" id="asset" value="${row_body.ASSET }">
																<input type="hidden" id="amount" value="${row_body.AMOUNT }">
																<input type="hidden" id="former_asset" value="${row_body.FORMER_ASSET }">
																<input type="hidden" id="latter_asset" value="${row_body.LATTER_ASSET }">
																<td style="width: 10%;">
																	<div>${row_body.INCOME_AND_EXPENSES }</div>
																</td>
																<td style="width: 20%;">
																	<div>${row_body.CATEGORY}</div>
																</td>
																<td style="width: 35%;">
																	<div>${row_body.DESCRIPTION}</div>
																</td>
																<td style="width: 20%;">
																	<div>${row_body.ASSET}</div>
																	<div>${row_body.AMOUNT}</div>
																</td>
																<td class="text-center" style="width: 10%;">
																	<button class="btn btn-primary btn-sm" title="수정"
																		name="btnLedgerUpdate">
																		<i class="fas fa-pencil-alt"></i>
																	</button>
																	<button class="btn btn-danger btn-sm" title="삭제"
																		name="btnLedgerDelete">
																		<i class="fas fa-trash-alt"></i>
																	</button>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<table class="table">
												<thead>
													<tr>
														<th>데이터가 없습니다.</th>
													</tr>
												</thead>
											</table>
										</c:otherwise>
									</c:choose>
								</div>
								<!-- /.card-body -->
							</div>
							<!-- /.card -->

						</div>
						<!-- /col-12 -->
					</div>
					<!-- /.ledger details -->

				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->

		</div>
		<!-- /.content wrapper -->
		
		<%@include file="./ledger_insert_modal.jsp"%>
		<%@include file="./ledger_update_modal.jsp"%>
		<%@include file="./ledger_delete_modal.jsp"%>
		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->


	<%@include file="../include/include_main_plugins.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/view/ledger/ledger_modal.js"/>"></script>
	<!-- daterange picker -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
</body>
</html>