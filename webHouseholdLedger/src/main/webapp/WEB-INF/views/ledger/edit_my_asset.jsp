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
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-lg-6">
							<h1 class="m-0">자산 목록 수정</h1>
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
					
					<!-- row -->
					<div class="row">
						<div class="col-12">
							<!-- Card -->
							<div class="card card-primary card-outline">
								
								<div class="card-header">
									<h3 class="card-title">자산 목록 수정</h3>
								</div>
									
								<!-- Card-body -->
								<div class="card-body">
								
									<div class="row">
										<div class="btn-group col-lg-6 container">
											<button type="button" class="btn btn-default"
												onclick="fn_openInsertModal()">자산 추가</button>
										</div>
									</div>
									
									<div class="row">
											<div class="col-lg-6 container">
												<table class="table table-hover border table-valign-middle">
													<thead>
														<tr>
															<th class="text-center" style="width: 10%;">분류</th>
															<th class="text-center" style="width: 25%;">자산이름</th>
															<th class="text-center" style="width: 35%;">잔액</th>
															<th style="width: 30%;"></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="row_body" items="${assetDetailedList }">
															<tr class="text-break">
																<td class="text-center" style="width: 10%;">${row_body.ASSET_TYPE }</td>
																<td class="text-center" style="width: 25%;">${row_body.ASSET_NAME }</td>
																<td class="text-right" style="width: 35%;">${row_body.BALANCE }</td>
																<td class="text-right" style="width: 30%;">
																<button class="btn btn-info btn-sm" title="위로"
																	name="btnUpOrder" onclick="fn_upAssetOrder(${row_body.ASSET_IDX }, this)">
																	<i class="fas fa-arrow-alt-circle-up"></i>
																</button>
																<button class="btn btn-info btn-sm" title="아래로"
																	name="btnDownOrder" onclick="fn_downAssetOrder(${row_body.ASSET_IDX }, this)">
																	<i class="fas fa-arrow-alt-circle-down"></i>
																</button>
																<button class="btn btn-primary btn-sm" title="수정"
																	name="btnUpdateAsset" onclick="openUpdateModal(${row_body.ASSET_IDX })">
																	<i class="fas fa-pencil-alt"></i>
																</button>
																<button class="btn btn-danger btn-sm" title="삭제"
																	name="btnDeleteAsset">
																	<i class="fas fa-trash-alt"></i>
																</button>
															</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
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
		
		<%@include file="./asset_insert_modal.jsp"%>
		<%@include file="./asset_update_modal.jsp"%>
		<%@include file="../include/include_main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	
	<%@include file="../include/include_main_plugins.jsp" %>
	<script type="text/javascript" src="<c:url value="/resources/view/ledger/asset_modal.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/ledger/edit_my_asset.js"/>"></script>
</body>
</html>