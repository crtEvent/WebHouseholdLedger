<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Insert Modal -->
<div class="modal fade" id="find-user-id-modal">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-default">
		<!-- Modal-content -->
		<div class="modal-content card-info">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">아이디 찾기</h4>
				<button type="button" class="close" onclick="fn_closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<!-- Modal Body -->
			<div class="modal-body">
				<p>해당 이메일로 가입한 아이디 입니다.</p>
				<table class="table table-hover text-nowrap table-bordered">
					<tbody></tbody>
				</table>
			</div><!-- /. modal-body -->

			<!-- Modal Footer -->
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default"
					onclick="fn_closeModal()">확인</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.insert modal -->