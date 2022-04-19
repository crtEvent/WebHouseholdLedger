<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 Page Not Found</title>
<%@include file="../include/include_main_head.jsp"%>
</head>
<body class="login-page py-5">

	<section class="content my-5">
		<div class="error-page">
			<h2 class="headline text-danger">500</h2>

			<div class="error-content">
				<h3>
					<i class="fas fa-exclamation-triangle text-danger"></i>
					 &nbsp;&nbsp;내부 서버 오류
				</h3>

				<p>
					&nbsp;서비스 이용에 불편을 드려 죄송합니다. <br>시스템 에러가 발생하여 페이지를 표시할 수 없습니다.
				</p>

			</div>
			<!-- /.error-content -->
		</div>
		<!-- /.error-page -->
	</section>
	<section class="content w-25">
		<div class="row">
			<div class="col-md-6">
				<a href="<c:url value='/ledger/details.do'/>">
					<button type="button" class="btn btn-block btn-outline-info" >
						홈으로
					</button>
				</a>
			</div>
			<div class="col-md-6">
				<button type="button" class="btn btn-block btn-outline-info" onclick="history.back()">
					이전 페이지
				</button>
			</div>
		</div>
	</section>

	<%@include file="../include/include_main_plugins.jsp"%>
</body>
</html>