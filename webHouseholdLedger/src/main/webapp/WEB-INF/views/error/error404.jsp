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
			<h2 class="headline text-warning">404</h2>

			<div class="error-content">
				<h3>
					<i class="fas fa-exclamation-triangle text-warning"></i>
					 &nbsp;&nbsp;페이지를 찾을 수 없습니다.
				</h3>

				<p>
					&nbsp;페이지의 주소가 잘못 입력되었거나, 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.
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