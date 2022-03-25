<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Edit User Image Modal -->
<div class="modal fade" id="edit_user_image_modal">
	<!-- Modal-dialog -->
	<div class="modal-dialog">
		<!-- Modal-content -->
		<div class="modal-content">

			<div class="modal-header">
				<h4 class="modal-title">유저 이미지 변경</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="row mb-4">
					<div class="col-lg-4 container">
						<div class="my-frame">
							<div class="my-img-wrapper-default">
								<img class="img-circle" id="preview_user_image"
									src="${userSession.getUser_image() }" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="row mb-2">
					<button type="button" class="btn btn-outline-primary btn-block" onclick="fn_changeUserImage()">
						<i class="fas fa-upload"></i>&nbsp;&nbsp;이미지 파일 업로드
					</button>
				</div>
				
				<div class="row">
					<p class="text-danger ml-4">
						※ 업로드 가능한 파일 크기: 1MB이하.<br>
						※ 업로드 가능한 파일 확장자 : jpg, jpeg, png, bmp
					</p>
				</div>

			</div>

			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" onclick="fn_editUserImage()">이미지 변경</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->