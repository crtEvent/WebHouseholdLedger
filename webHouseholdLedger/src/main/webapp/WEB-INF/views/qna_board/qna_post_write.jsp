<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가계부</title>
<%@include file="../include/include_main_head.jsp"%>
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
							<h1>QnA 글쓰기</h1>
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
									<h3 class="card-title">Q&A 글쓰기</h3>
								</div>
								<!-- /.card-header -->
								<!-- Card-body -->
								<div class="card-body">
									<div class="form-group">
										<input name="subject" class="form-control" placeholder="제목:"
											minlength="1" maxlength="100">
									</div>
									<div class="form-group">
										<textarea name="content" id="writeContentField"
											class="form-control" minlength="1" maxlength="1000">
                    					</textarea>
									</div>
									<div class="form-group">
										<div class="btn btn-default btn-file" onclick="fn_addFile()">
											<i class="fas fa-paperclip"></i> 파일첨부
										</div>
										<p class="help-block">Max. 32MB</p>
									</div>
									<div id="fileList">
										<!--  <input type="file" name=""> 이 들어갈 자리 -->
									</div>
								</div>
								<!-- /.card-body -->

								<div class="card-footer">
									<div class="float-right">
										<button type="button" class="btn btn-primary"
											onclick="fn_insertQnaPost()">
											<i class="fas fa-pencil-alt"></i> 글쓰기
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
	<script type="text/javascript" src="<c:url value="/resources/view/qna_board/qna_post_write.js"/>"></script>
</body>
</html>