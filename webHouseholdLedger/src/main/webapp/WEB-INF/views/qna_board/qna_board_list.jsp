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

			<!-- Content Header -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>QnA 게시판</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">

					<!-- 게시글 리스트 -->
					<div class="row">
						<div class="col-12">
							<!-- Card -->
							<div class="card">

								<!-- Card-header -->
								<div class="card-header">
									<!-- Header button -->
									<div class="input-group-sm float-left">
										<button type="button" class="btn btn-primary"
											id="moveWritePageBtn">
											<b><i class="fas fa-pencil-alt"></i> 글쓰기</b>
										</button>
										<button type="button" class="btn btn-default"
											id="moveMyPostPageBtn">
											<b>내가 쓴 글</b>
										</button>
									</div>
									<!-- /.header button -->
									<!-- Search bar -->
									<div class="float-right">
									<div class="container">
 									<div class="row">
										<div class="">
											<select name="search_type" class="form-control">
												<option value="subject+content">제목+내용</option>
												<option value="subject">제목</option>
												<option value="content">내용</option>
												<option value="user">글쓴이</option>
											</select>
										</div>
										<div class="">
											<input type="text" name="keyword" class="form-control"
												placeholder="Search" required>
										</div>
										<div class="">
											<button type="button" class="btn btn-default"
													id="doSearchBtn">
												<i class="fas fa-search"></i><b> 검색</b>
											</button>
										</div>
									</div>
									</div>
									</div>
									<!-- /.search bar -->
								</div>
								<!-- /.card-header -->

								<!-- Card-body -->
								<div class="card-body table-responsive p-0">
									<table class="table table-hover">
										<thead>
											<tr>
												<th style="width: 10%;">번호</th>
												<th style="width: 44%;">제목</th>
												<th style="width: 18%;">글쓴이</th>
												<th style="width: 18%;">날짜</th>
												<th style="width: 10%;">조회수</th>
											</tr>
										</thead>
										<tbody>

											<c:choose>
												<c:when test="${fn:length(qnaPostList) > 0}">
													<c:forEach var="qna_list" items="${qnaPostList }">
														<tr style="cursor: pointer">
															<td>${qna_list.BOARD_IDX }</td>
															<td>${qna_list.SUBJECT }</td>
															<td>${qna_list.USER_ID }</td>
															<td>${qna_list.REG_DATE }</td>
															<td>${qna_list.HIT_CNT }</td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="5">내역이 없습니다.</td>
													</tr>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								</div>
								<!-- /.card-body -->

								<!-- Card-footer -->
								<div class="card-footer"></div>
								<!-- /.card-footer -->

							</div>
							<!-- /.card -->
						</div>
					</div>
					<!-- /.게시글 리스트 -->

				</div>
			</section>
			<!-- /.main content -->

		</div>

		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/include_main_plugins.jsp"%>
</body>
</html>