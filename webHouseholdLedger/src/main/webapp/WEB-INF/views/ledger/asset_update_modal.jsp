<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Insert Modal -->
<div class="modal fade" id="update-asset-modal">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-default">
		<!-- Modal-content -->
		<div class="modal-content card-info">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">가계부 내역 수정</h4>
				<button type="button" class="close" onclick="fn_closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<!-- Modal Body -->
			<div class="modal-body">
				<form name="assetUpdateForm" id="assetUpdateForm" method="post">
				
					<input type="hidden" name="asset_idx" value="" />
				
					<div class="row text-center mb-3">
						<div class="col">
							<div class="icheck-success d-inline">
								<input type="radio" name="asset_type" id="radioCash" value="현금"
									checked> <label for="radioCash"> 현 금 </label>
							</div>
						</div>
						<div class="col">
							<div class="icheck-success d-inline">
								<input type="radio" name="asset_type" id="radioBank" value="통장">
								<label for="radioBank"> 통 장 </label>
							</div>
						</div>
						<div class="col">
							<div class="icheck-success d-inline">
								<input type="radio" name="asset_type" id="radioCheck" value="체크">
								<label for="radioCheck"> 체크카드 </label>
							</div>
						</div>
						<div class="col">
							<div class="icheck-success d-inline">
								<input type="radio" name="asset_type" id="radioCredit" value="신용">
								<label for="radioCredit"> 신용카드 </label>
							</div>
						</div>
					</div>
					
					<!-- 1.공통 - 자산 이름 -->
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button type="button" class="btn btn-default">자산이름</button>
						</div>
						<input name="asset_name" type="text" autocomplete="off"
							maxlength="45" class="form-control">
					</div>

					<!-- 2.현금,통장/신용카드 - 초기 금액/카드대금-->
					<div id="updateCashAssetsDiv">
						<!-- 초기 금액 -->
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">초기금액</button>
							</div>
							<input name="initial_amount" type="text" autocomplete="off" class="form-control">
						</div>
					</div>

					<!-- 3.체크,신용 카드 - 연결 통장 -->
					<div id="updateCardAssetsDiv" style="display: none;">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button type="button" class="btn btn-default">연결통장</button>
							</div>
							<select name="connection_asset_idx" class="form-control">
								<c:forEach var="asset" items="${assetList }">
									<option value="${asset.ASSET_NAME }">${asset.ASSET_NAME }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
				</form>
			</div><!-- /. modal-body -->

			<!-- Modal Footer -->
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default"
					onclick="fn_closeModal()">닫기</button>
				<button type="button" class="btn btn-primary" onclick="fn_updateAsset()">수정
					하기</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.insert modal -->