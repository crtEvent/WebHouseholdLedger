<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-md-3">
	<div class="card">
		<div class="card-header">
			<h3 class="card-title">메일 메뉴</h3>

			<div class="card-tools">
				<button type="button" class="btn btn-tool"
					data-card-widget="collapse">
					<i class="fas fa-minus"></i>
				</button>
			</div>
		</div>
		<!-- /.card-header -->
		<div class="card-body p-0">
			<ul class="nav nav-pills flex-column">
				<li class="nav-item active">
					<a href="<c:url value='/admin/mail/sentMailBox.do?currentPage=1'/>" class="nav-link">
						<i class="fas fa-inbox"></i>&nbsp;&nbsp;&nbsp;보낸 목록
					</a>
				</li>
				<li class="nav-item">
					<a href="<c:url value='/admin/mail/writeMail.do'/>" class="nav-link">
						<i class="far fa-envelope"></i>&nbsp;&nbsp;&nbsp;메일 쓰기
					</a>
				</li>
				<li class="nav-item">
					<a href="<c:url value='/admin/mail/savedMailFormList.do?currentPage=1'/>" class="nav-link">
						<i class="far fa-file-alt"></i>&nbsp;&nbsp;&nbsp;저장된 양식
					</a>
				</li>
			</ul>
		</div>
		<!-- /.card-body -->
	</div>
	<!-- /.card -->
</div>