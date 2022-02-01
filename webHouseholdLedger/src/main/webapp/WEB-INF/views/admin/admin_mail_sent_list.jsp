<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
									<h3 class="card-title">보낸 메일 목록</h3>
									<!-- Search Bar -->
									<div class="card-tools">
										<div class="input-group input-group-sm">
											<input type="text" name="keyword" class="form-control"
												autocomplete="off" placeholder="Search Mail" value="${param.keyword}">
											<div class="input-group-append">
												<div class="btn btn-primary" onclick="fn_doSearch()">
													<i class="fas fa-search"></i>
												</div>
											</div>
										</div>
									</div>
									<!-- /.search-bar -->
								</div>
								<!-- /.card-header -->

								<div class="card-body p-0">


									<!-- Mailbox-controls -->
									<div class="mailbox-controls">
										<!-- Check all button -->
										<button type="button"
											class="btn btn-default btn-sm checkbox-toggle">
											<!-- <i class="far fa-square"></i> -->
											<input type="checkbox" name="checkAll" id="checkAll" onclick="fn_checkAll()">
										</button>
										<button type="button" class="btn btn-default btn-sm" onclick="fn_deleteMail()">
											<i class="far fa-trash-alt"></i>
										</button>
										
										<div class="float-right">
											<c:choose>
												<c:when test="${pagingDTO.startPositionOnCurrentPage + pagingDTO.numberPostsPerPage <  pagingDTO.totalNumberPosts}">
													${pagingDTO.startPositionOnCurrentPage + 1} - ${pagingDTO.startPositionOnCurrentPage + pagingDTO.numberPostsPerPage}/${pagingDTO.totalNumberPosts}
												</c:when>
												<c:otherwise>
													${pagingDTO.startPositionOnCurrentPage + 1} - ${pagingDTO.totalNumberPosts}/${pagingDTO.totalNumberPosts}
												</c:otherwise>
											</c:choose>
											<div class="btn-group">
												<c:choose>
													<c:when test="${pagingDTO.hasPreviousBlock == true }">
														<button type="button" class="btn btn-default btn-sm" onclick="fn_linkToPage(${param.currentPage-pagingDTO.pagingBlockSize})">
															<i class="fas fa-chevron-left"></i>
														</button>
													</c:when>
													<c:otherwise>
														<button type="button" class="btn btn-default btn-sm" disabled="disabled">
															<i class="fas fa-chevron-left"></i>
														</button>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${pagingDTO.hasNextBlock == true }">
														<button type="button" class="btn btn-default btn-sm" onclick="fn_linkToPage(${param.currentPage+pagingDTO.pagingBlockSize})">
															<i class="fas fa-chevron-right"></i>
														</button>
													</c:when>
													<c:otherwise>
														<button type="button" class="btn btn-default btn-sm" disabled="disabled">
															<i class="fas fa-chevron-right"></i>
														</button>
													</c:otherwise>
												</c:choose>
											</div>
											<!-- /.btn-group -->
										</div>
										<!-- /.float-right -->
									</div>
									<!-- /.mailbox-controls -->

									<!-- MailBox: Mail List -->
									<div class="table-responsive mailbox-messages">
										<table class="table table-hover table-striped">
											<tbody>

												<c:choose>
													<c:when test="${fn:length(sentMailList) > 0}">
														<c:forEach var="mail_list" items="${sentMailList }"
															step="1" varStatus="status">
															
															<tr style="cursor: pointer">
																<td style="width: 10%;">
																	<div class="icheck-primary">
																		<input type="checkbox" name="chckMail" value="${mail_list.MAIL_IDX }"
																			id="check${status.count}">
																		<label for="check${status.count}"></label>
																	</div>
																</td>
																<td class="mailbox-name" style="width: 20%;">
																	${mail_list.MAIL_TO }
																</td>
																<td class="mailbox-subject" style="width: 55%;">
																	${mail_list.MAIL_SUBJECT }
																</td>
																<td class="mailbox-date" style="width: 15%;">
																	${mail_list.REG_DATE }
																</td>
															</tr>
															
														</c:forEach>
													</c:when>
												</c:choose>

											</tbody>
										</table>
									</div>
									<!-- /.mailbox: mail list -->


								</div>
								<!-- /.card-body -->

								<div class="card-footer"></div>
								<!-- /.card-footer -->

							</div>
							<!-- /.card -->
						</div>
						<!-- /.col: right-card -->

					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</section>
			<!-- /.main content -->

		</div>
		<!-- /.content-wrapper -->

		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/include_main_plugins.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/view/admin/admin_mail_sent_list.js"/>"></script>
</body>
</html>