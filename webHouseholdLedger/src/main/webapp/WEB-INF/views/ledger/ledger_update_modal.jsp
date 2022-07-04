<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Update Modal -->
<div class="modal fade" id="modal-lg-update">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-default">
		<!-- Modal-content -->
		<div class="modal-content card-info">

			<div class="modal-header">
				<h4 class="modal-title">가계부 내역 수정</h4>
				<button type="button" class="close" onclick="fn_closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<!-- Insert Form -->
				<form name="ledgerUpdateForm" id="ledgerUpdateForm" method="post">
					<input type="hidden" name="ledger_idx" value="">
					<div class="form-group clearfix">
						<table class="text-center col-md-12">
							<tr>
								<td>
									<div class="icheck-success d-inline">
										<input type="radio" name="income_and_expenses" id="updateRadioIncome"
											value="수입">
											<label for="updateRadioIncome"> 수입 </label>
									</div>
								</td>
								<td>
									<div class="icheck-danger d-inline">
										<input type="radio" name="income_and_expenses" id="updateRadioExpenses"
											value="지출">
											<label for="updateRadioExpenses"> 지출 </label>
									</div>
								</td>
								<td>
									<div class="icheck-info d-inline">
										<input type="radio" name="income_and_expenses" id="updateRadioTransfer"
											value="이동">
											<label for="updateRadioTransfer"> 이동 </label>
									</div>
								</td>
							</tr>
							
						</table>
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button type="button" class="btn btn-default">날짜</button>
						</div>
						<input type="date" name="date" class="form-control">
					</div>
						
					<div class="incomeAndExpensesDiv">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">분류</button>
							</div>
							<select name="category" class="form-control">
								<option value="월급">월급</option>
								<option value="상여금">상여금</option>
								<option value="펀드/주식">펀드/주식</option>
								<option value="기타수익">기타수익</option>
							</select>
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">내용</button>
							</div>
							<input name="description" type="text" autocomplete="off"
								class="form-control">
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">자산</button>
							</div>
							<select name="asset_idx" class="form-control">
								<c:forEach var="asset" items="${assetList }">
									<option value="${asset.ASSET_IDX }">${asset.ASSET_NAME }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="transferDiv" style="display: none;">
						<div class="input-group mb-2">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">자산</button>
							</div>
							<select name="former_asset" class="form-control">
								<c:forEach var="asset" items="${assetList }">
									<option value="${asset.ASSET_IDX }">${asset.ASSET_NAME }</option>
								</c:forEach>
							</select>
						</div>
						<div class="text-center mb-2">
							<i class="fas fa-arrow-alt-circle-down fa-2x"></i>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">자산</button>
							</div>
							<select name="latter_asset" class="form-control">
								<c:forEach var="asset" items="${assetList }">
									<option value="${asset.ASSET_IDX }">${asset.ASSET_NAME }</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button type="button" class="btn btn-default">금액</button>
						</div>
						<input name="amount" type="text" autocomplete="off" class="form-control">
					</div>
				</form>
				<!-- /.insert-form -->
			</div>

			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default"
					onclick="fn_closeModal()">닫기</button>
				<button type="button" class="btn btn-primary"
					onclick="fn_updateLedger()">수정하기</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.update modal -->