<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Delete Modal -->
<div class="modal fade" id="modal-lg-delete">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-default">
		<!-- Modal-content -->
		<div class="modal-content card-info">

			<div class="modal-header">
				<h4 class="modal-title">가계부 내역 삭제</h4>
				<button type="button" class="close" onclick="fn_closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="text-center font-weight-bold mt-3">
					<p>정말로 삭제하시겠습니까?</p>
				</div>
				<form name="ledgerDeleteForm" id="ledgerDeleteForm" method="post">
					<input type="hidden" name="ledger_idx" value="">
				</form>
			</div>
			
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default"
					onclick="fn_closeModal()">닫기</button>
				<button type="button" class="btn btn-primary"
					onclick="fn_deleteLedger()">삭제하기</button>
			</div>
			
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.delete modal -->