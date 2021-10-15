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
					
					<!-- Qna Post -->
					<c:if test="${not empty qnaPostDTO }">
						<!-- Row -->
						<div class="row">
							<!-- Col -->
							<div class="col-12">
		
								<!-- Card -->
								<div class="card card-primary card-outline">

									<!-- Card-header -->
									<div class="card-header">
										<h5>${qnaPostDTO.subject }</h5>
										<h6>
											<span class="mailbox-read-time float-right">
												${qnaPostDTO.user_id }, ${qnaPostDTO.reg_date } </span>
										</h6>
									</div>
									<!-- /.card-header -->

									<!-- Card-body -->
									<div class="card-body p-0">
										<div class="mailbox-read-message">
											<p>${qnaPostDTO.content }</p>
										</div>
									</div>
									<!-- /.card-body -->

									<!-- Card-footer -->
									<div class="card-footer">
										<div class="float-right">
											<button type="button" class="btn btn-default" onclick="fn_linkToPost(${qnaPostDTO.nextBoard_idx })">
												<i class="fas fa-reply"></i> 다음글
											</button>
											<button type="button" class="btn btn-default" onclick="fn_linkToList()">
												<i class="fas fa-bars"></i> 목록으로
											</button>
											<button type="button" class="btn btn-default" onclick="fn_linkToPost(${qnaPostDTO.prevBoard_idx })">
												<i class="fas fa-share"></i> 이전글
											</button>
										</div>
										<c:if test="${userSession.getUser_idx() eq qnaPostDTO.user_idx}">
											<button type="button" class="btn btn-default">
												<i class="fas fa-pencil-alt"></i> 수정하기
											</button>
											<button type="button" class="btn btn-default">
												<i class="far fa-trash-alt"></i> 삭제하기
											</button>
										</c:if>
									</div>
									<!-- /.card-footer -->

								</div>
								<!-- /.card -->

							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->
				
					</c:if>
					<!-- /.qna post -->

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
 										<form name="searchForm">
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
										</form>
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
														<tr style="cursor: pointer" onclick="fn_linkToPost(${qna_list.BOARD_IDX })">
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
								<div class="card-footer">
									<form name="linkToPageForm"></form>
									<ul class="pagination m-0 justify-content-center">
									
										<li class="page-item">
											<a class="page-link" onclick="fn_linkToPage(1)" href="#">처음 </a>
										</li>
										
										<c:if test="${pagingDTO.hasPreviousBlock == true }">
											<li class="page-item">
												<a class="page-link" onclick="fn_linkToPage(${param.currentPage-10})" href="#">이전 </a>
											</li>
										</c:if>

										<c:forEach var="paging" begin="${pagingDTO.firstPageOfpagingBlock}" end="${pagingDTO.lastPageOfpagingBlock}" step="1" varStatus="status">
											<c:choose>
												<c:when test="${pagingDTO.currentPage ==  status.current}">
													<li class="page-item active">
														<a class="page-link" onclick="fn_linkToPage(${status.current})" href="#">
															${status.current}
														</a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="page-item">
														<a class="page-link" onclick="fn_linkToPage(${status.current})" href="#">
															${status.current}
														</a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:if test="${pagingDTO.hasNextBlock == true }">
											<li class="page-item">
												<a class="page-link" onclick="fn_linkToPage(${param.currentPage+10})" href="#">다음 </a>
											</li>
										</c:if>

										<li class="page-item">
											<a class="page-link" onclick="fn_linkToPage(${pagingDTO.lastPage})" href="#">
												끝 
											</a>
										</li>
								
									</ul>
									
								</div>
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
	
	<script type="text/javascript" src="<c:url value="/resources/view/qna_board/qna_board_list.js"/>"></script>
</body>
</html>