<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Delete Modal -->
<div class="modal fade" id="modal-updateUserInfo">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-default">
		<!-- Modal-content -->
		<div class="modal-content card-info">

			<div class="modal-header">
				<h4 class="modal-title">회원 정보 수정</h4>
				<button type="button" class="close" onclick="fn_closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body mb-3">
			
				<!-- USER_IDX -->
				<input type="hidden" id="user_idx" value="">
				
				<!-- 아이디 -->
				<div class="row">
					<div class="col-sm-12">
						<div class="col-sm-8 container">
							<label class="col-form-label" for="user_id">
								아이디
							</label>
						</div>
						<div class="input-group col-sm-8 container mb-1" id="div_userId">
							<input type="text" class="form-control" 
								name="user_id" id="user_id" value="" 
								readonly="readonly" autocomplete="off">
							<div class="input-group-append" id="enableEditUserIdBtn" onclick="fn_enableEditUserId()">
								<span class="input-group-text">변경</span>
							</div>
						</div>
						<div class="col-sm-8 container mb-3" id="msg_user_id">${valid_user_id }</div>
					</div>
				</div>
				
				<!-- 이메일 -->
				<div class="row">
					<div class="col-sm-12">
						<div class="col-sm-8 container">
							<label class="col-form-label" for="user_email">
								이메일
							</label>
						</div>
						<div class="input-group col-sm-8 container mb-1" id="div_userEmail">
							<input type="text" class="form-control" 
								name="user_email" id="user_email" value="" 
								readonly="readonly" autocomplete="off">
							<div class="input-group-append" id="enableEditUserEmailBtn" onclick="fn_enableEditUserEmail()">
								<span class="input-group-text">변경</span>
							</div>
						</div>
						<div class="col-sm-8 container mb-3" id="msg_user_email">${valid_user_email }</div>
					</div>
				</div>
				
				<!-- 비밀번호 -->
				<div class="row">
					<div class="col-sm-12 mb-3">
						<div class="col-sm-8 container">
							<label class="col-form-label" for="user_password">
								 비밀번호
							</label>
						</div>
						<div class="col-sm-8 container" id="div_enableUserPassword">
							<button class="btn btn-default btn-block" onclick="fn_enableEditUserPassword()">비밀번호 변경</button>
						</div>
						<div class="col-sm-8 container d-none" id="div_editUserPassword">
							<input type="password" class="form-control mb-1" id="new_user_password" placeholder="새 비밀번호(영문,숫자,특수문자 조합 5-25자)">
							<div class="container mb-2" id="msg_user_pw"></div>
							<input type="password" class="form-control mb-1" id="new_user_password_check" placeholder="새 비밀번호 다시입력">
							<div class="container mb-2" id="msg_user_pw_check"></div>
							<div class="btn-group container">
								<button class="btn btn-default" onclick="fn_editUserPassword()"><i class="fas fa-check-circle"></i> 변경</button>
								<button class="btn btn-default" onclick="fn_cancelEdituserPassword()"><i class="fas fa-times-circle"></i> 취소</button>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 이메일 수신 동의 -->
				<div class="row">
					<div class="col-12">
						<div class="col-sm-8 container">
							<label class="col-form-label">
								 이메일 수신 동의
							</label>
						</div>
						<div class="col-sm-8 container icheck-primary">
							<input type="checkbox" id="receive_mail" value="Y" ${userSession.getReceive_mail() == "Y" ? "checked" : ""}>
							<label for="receive_mail">
								프로모션 정보 수신(선택)에 동의합니다.
							</label>
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.delete modal -->