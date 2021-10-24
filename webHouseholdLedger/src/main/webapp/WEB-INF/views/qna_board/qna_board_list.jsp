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
									
									<!-- Card-body -->
									<div class="card-body p-0 mb-2">
										<div class="mailbox-read-message">
											<hr>
											<div class="float-left">
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
											<div class="float-right">
												<c:if test="${userSession.getUser_idx() eq qnaPostDTO.user_idx}">
													<button type="button" class="btn btn-default">
														<i class="fas fa-pencil-alt"></i> 수정하기
													</button>
													<button type="button" class="btn btn-default">
														<i class="far fa-trash-alt"></i> 삭제하기
													</button>
												</c:if>
											</div>
										</div>
									</div>
									<!-- /.card-body -->
									
									<!-- Card-body - Write-comment -->
									<div class="card-body p-0 mb-2">
										<div class="mailbox-read-message">
											<div class="form-group mb-1">
           										<label>댓글 쓰기</label>
           										<textarea id="commentField" 
           											maxlength="1000" class="form-control" rows="3" placeholder="내용을 입력해 주세요." 
           											onkeyup="fn_checkCommentFieldCharLimit()"></textarea>
           									</div>
           									<div class="text-right">
           										<span id="charLength">(0 / 최대 1000자)</span>
           										<button type="button" class="btn btn-default" onclick="fn_writeComment()">
													댓글등록
												</button>
           									</div>
           								</div>
									</div>
									<!-- /.card-body - write-comment-->

									<!-- Card-footer - Comment -->
									<div class="card-footer card-comments">
									
										<c:choose>
											<c:when test="${fn:length(qnaCommentList) > 0 }">
												<c:forEach var="row_comment"
													items="${qnaCommentList}" varStatus="status">
													<c:if test="${row_comment.DELETE_YN eq 'N'}">
														<c:if test="${row_comment.DEPTH eq 1}">
														
															<!-- Card-comment -->
															<div class="card-comment">
																<!-- User image -->
																<img class="img-circle img-sm" src="<c:url value="/resources/adminLTE/dist/img/user2-160x160.jpg"/>" alt="User Image">
																<div class="comment-text">
																	<span class="username">
																		${row_comment.USER_ID}
																		<span class="text-muted float-right">${row_comment.REG_DATE}</span>
																	</span>
																	<div>${row_comment.COMMENT}</div>
																	<span>
																		<span class="float-right text-primary" >
																			<button class="btn btn-link" data-toggle="collapse" data-target="#reCommentDiv${row_comment.COMMENT_IDX}">
																				답글 달기
																			</button>
																		</span>
																	</span>
																</div>
															</div>
															<div id="reCommentDiv${row_comment.COMMENT_IDX}" class="card-comment collapse">
																<div class="mailbox-read-message">
																	<div class="form-group mb-1">
           																<label>댓글 쓰기</label>
           																	<textarea id="reCommentField${row_comment.COMMENT_IDX}" 
           																		maxlength="1000" class="form-control" rows="3" placeholder="내용을 입력해 주세요."
           																		 onkeyup="fn_checkReCommentFieldCharLimit(${row_comment.COMMENT_IDX})"></textarea>
           															</div>
           															<div class="text-right">
           																<span id="charLength${row_comment.COMMENT_IDX}">(0 / 최대 1000자)</span>
           																<button type="button" class="btn btn-default" onclick="fn_writeReComment(${row_comment.COMMENT_IDX})">
																			댓글등록
																		</button>
           															</div>
           														</div>
															</div>
															<!-- /card-comment -->
															
														</c:if>
														<c:if test="${row_comment.DEPTH eq 2}">
														
															<!-- Card-comment -->
															<div class="card-comment">
																<!-- User image -->
																<img class="img-circle img-sm ml-5" src="<c:url value="/resources/adminLTE/dist/img/user2-160x160.jpg"/>" alt="User Image">
																<div class="comment-text">
																	<span class="username ml-5">
																		${row_comment.USER_ID}
																		<span class="text-muted float-right">${row_comment.REG_DATE}</span>
																	</span>
																	<div class="ml-5">
																		<span class="text-primary">
																			@${row_comment.PARENT_USER_ID} 
																		</span>
																		${row_comment.COMMENT}
																	</div>
																	<span class="ml-5">
																		<span class="float-right text-primary" >
																			<button class="btn btn-link" data-toggle="collapse" data-target="#reCommentDiv${row_comment.COMMENT_IDX}">
																				답글 달기
																			</button>
																		</span>
																	</span>
																</div>
															</div>
															<div id="reCommentDiv${row_comment.COMMENT_IDX}" class="card-comment collapse">
																<div class="mailbox-read-message">
																	<div class="form-group mb-1">
           																<label>댓글 쓰기</label>
           																	<textarea id="reCommentField${row_comment.COMMENT_IDX}" 
           																		maxlength="1000" class="form-control" rows="3" placeholder="내용을 입력해 주세요."
           																		 onkeyup="fn_checkReCommentFieldCharLimit(${row_comment.COMMENT_IDX})"></textarea>
           															</div>
           															<div class="text-right">
           																<span id="charLength${row_comment.COMMENT_IDX}">(0 / 최대 1000자)</span>
           																<button type="button" class="btn btn-default" onclick="fn_writeReComment(${row_comment.COMMENT_IDX})">
																			댓글등록
																		</button>
           															</div>
           														</div>
															</div>
															<!-- /card-comment -->
															
														</c:if>
													</c:if>
													<c:if test="${row_comment.DELETE_YN eq 'Y'}">
														<c:if test="${row_comment.DEPTH eq 1}">
														
															<!-- Card-comment -->
															<div class="card-comment">
																<!-- User image -->
																<img class="img-circle img-sm" src="<c:url value="/resources/adminLTE/dist/img/user2-160x160.jpg"/>" alt="User Image">
																<div class="comment-text">
																	<span class="username">
																		${row_comment.USER_ID}
																		<span class="text-muted float-right">${row_comment.REG_DATE}</span>
																	</span>
																	<div>
																		<span>
																			<i class="fas fa-exclamation-circle"></i>
																		</span>
																		<span class="text-center">
																			삭제된 댓글 입니다.
																		</span>
																	</div>
																	<span>
																		<span class="float-right text-secondary" >
																			답글 달기
																		</span>
																	</span>
																</div>
															</div>
															<!-- /card-comment -->
															
														</c:if>
														<c:if test="${row_comment.DEPTH eq 2}">
															
															<!-- Card-comment -->
															<div class="card-comment">
																<!-- User image -->
																<img class="img-circle img-sm ml-5" src="<c:url value="/resources/adminLTE/dist/img/user2-160x160.jpg"/>" alt="User Image">
																<div class="comment-text">
																	<span class="username ml-5">
																		<del>${row_comment.USER_ID}</del>
																		<span class="text-muted float-right">${row_comment.REG_DATE}</span>
																	</span>
																	<div class="ml-5">
																		<span class="text-primary">
																			@${row_comment.PARENT_USER_ID} 
																		</span>
																		<span class="text-center">
																			<span>
																				<i class="fas fa-exclamation-circle"></i>
																			</span>
																			삭제된 댓글 입니다.
																		</span>
																	</div>
																	<span class="ml-5">
																		<span class="float-right text-secondary" >
																			답글 달기
																		</span>
																	</span>
																</div>
															</div>
															<!-- /card-comment -->
															
														</c:if>
													</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<div class="text-center">등록된 댓글이 없습니다.</div>
											</c:otherwise>
										</c:choose>

									</div>
									<!-- /.card-footer - comment -->

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