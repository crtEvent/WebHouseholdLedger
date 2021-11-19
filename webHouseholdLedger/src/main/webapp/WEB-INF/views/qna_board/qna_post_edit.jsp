<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판: 게시글 수정</title>
<%@include file="../include/include_main_head.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<c:url value="/resources/plugins/summernote/summernote-bs4.css"/>">
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
							<h1>QnA 게시글 수정</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- /.content header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card card-primary card-outline">

								<div class="card-header">
									<h3 class="card-title">Q&A 게시글 수정</h3>
								</div>
								<!-- /.card-header -->
								<!-- Card-body -->
								<div class="card-body">
									<div class="form-group">
										<input name="subject" class="form-control" placeholder="제목:"
											minlength="1" maxlength="100" autocomplete="off"
											value="${qnaPostDTO.subject }">
									</div>
									<div class="form-group">
										<textarea name="content" id="writeContentField"
											class="form-control" minlength="1" maxlength="1000">${qnaPostDTO.content}</textarea>
									</div>
									<div class="form-group">
										<div class="btn btn-default btn-file" onclick="fn_addFile()">
											<i class="fas fa-paperclip"></i> 파일첨부
										</div>
										<div class="float-right" id="maxContentLength"></div>
										<p class="help-block">
											업로드 가능한 파일 확장자 : hwp, doc, docx, ppt, pptx, xls, xlsx, txt, csv, jpg, jpeg, gif, png, bmp, pdf<br>
											업로드 파일크기 및 개수 : 5MB 이하, 1~5개
										</p>
									</div>
									<div id="fileList" class="float-left">
										
											<c:choose>
												<c:when test="${fn:length(qnaFileList) > 0 }">
													<c:forEach var="row_file" items="${qnaFileList}" varStatus="status">
													
														<div class="input-group mb-1">
															<div class="input-group-prepend">
																<span class="input-group-text"><i class="fas fa-paperclip"></i></span>
															</div>
															<input type="text" class="form-control bg-white" value="${row_file.ORIGINAL_FILE_NAME}" readonly>
															<button class="btn btn-sm" name="cancel"><i class="fas fa-times-circle"></i></button>
															<input type="text" name="savedFile" value="${row_file.QNA_FILE_IDX}" style="display:none"/>
														</div>
													
													</c:forEach>
												</c:when>
											</c:choose>
										
									</div>
								</div>
								<!-- /.card-body -->

								<div class="card-footer">
									<div class="float-right">
										<button type="button" class="btn btn-primary"
											onclick="fn_updateQnaPost(${qnaPostDTO.board_idx})">
											<i class="fas fa-pencil-alt"></i> 수정하기
										</button>
									</div>
								</div>
								<!-- /.card-footer -->
								
							</div>
							<!-- /.card -->
						</div>
					</div>
				</div>
			</section>
			<!-- /.main content -->
		
		</div>
		<!-- ./content wrapper -->

		<%@include file="../include/include_main_footer.jsp"%>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/include_main_plugins.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/plugins/summernote/summernote-bs4.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/summernote/summernote-ko-KR.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/qna_board/qna_post_common.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/view/qna_board/qna_post_edit.js"/>"></script>
</body>
</html>