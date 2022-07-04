/**
 * 
 */
var insertForm = $("#assetInsertForm");
var updateForm = $("#assetUpdateForm");

/* 자산 추가 Modal창 열기 */
function fn_openInsertModal(){
	$("#insert-asset-modal").modal("show");
};

/* Modal창 닫기 */
function fn_closeModal(){
	$("#insert-asset-modal").modal("hide");
	$("#update-asset-modal").modal("hide");
}

// [INSERT MODAL FORM 변화]: input:radio[name=asset_type]에 따라 Modal form변화
insertForm.find("input:radio[name=asset_type]").click(function(){
	
	if(insertForm.find("input:radio[name='asset_type'][value='현금']").is(":checked")){
		insertForm.find("#insertCashAssetsDiv").show();
		insertForm.find("#insertCardAssetsDiv").hide();
		// 연결 통장 목록 제거
		fn_deleteBankAssetList();
	}else if(insertForm.find("input:radio[name='asset_type'][value='통장']").is(":checked")){
		insertForm.find("#insertCashAssetsDiv").show();
		insertForm.find("#insertCardAssetsDiv").hide();
		// 연결 통장 목록 제거
		fn_deleteBankAssetList();
	}else if(insertForm.find("input:radio[name='asset_type'][value='체크']").is(":checked")){
		insertForm.find("#insertCashAssetsDiv").hide();
		insertForm.find("#insertCardAssetsDiv").show();
		// 연결 통장 목록 제거
		fn_deleteBankAssetList();
		// 연결 통장 목록 보여주기
		fn_getBankAssetList();
	}else if(insertForm.find("input:radio[name='asset_type'][value='신용']").is(":checked")){
		insertForm.find("#insertCashAssetsDiv").show();
		insertForm.find("#insertCardAssetsDiv").show();
		// 연결 통장 목록 제거
		fn_deleteBankAssetList();
		// 연결 통장 목록 보여주기
		fn_getBankAssetList();
	}
	
});

// 자산 수정 Modal창 열기
function openUpdateModal(asset_idx) {
	
	$.ajax({
		url: '/app/ledger/getAssetOne.do',
		data: {asset_idx: asset_idx},
		type: 'post',
		success: function(result){
			// asset_idx 설정
			updateForm.find('input[name=asset_idx]').val(result.ASSET_IDX);
			
			// asset_type 에 따라 Form 변경
			let asset_type = result.ASSET_TYPE;
			switch(asset_type) {
			case "현금":
				// 체크박스 활성/비활성
				updateForm.find("input:radio[name=asset_type][value='현금']").prop("disabled", false);
				updateForm.find("input:radio[name=asset_type][value='통장']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='체크']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='신용']").prop("disabled", true);
				
				// asset_type 체크
				updateForm.find("input:radio[name=asset_type][value='현금']").prop("checked", true);
				
				// updateForm 변경
				updateForm.find("#updateCashAssetsDiv").show();
				updateForm.find("#updateCardAssetsDiv").hide();
				
				// 연결 통장 목록 제거
				fn_deleteBankAssetList();
				
				// 값 설정
				updateForm.find('input[name=asset_name]').val(result.ASSET_NAME);
				updateForm.find('input[name=initial_amount]').val(result.INITIAL_AMOUNT);
				
				break;
			case "통장":
				// 체크박스 활성/비활성
				updateForm.find("input:radio[name=asset_type][value='현금']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='통장']").prop("disabled", false);
				updateForm.find("input:radio[name=asset_type][value='체크']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='신용']").prop("disabled", true);
				
				// asset_type 체크
				updateForm.find("input:radio[name=asset_type][value='통장']").prop("checked", true);
				
				// updateForm 변경
				updateForm.find("#updateCashAssetsDiv").show();
				updateForm.find("#updateCardAssetsDiv").hide();
				
				// 연결 통장 목록 제거
				fn_deleteBankAssetList();
				
				// 값 설정
				updateForm.find('input[name=asset_name]').val(result.ASSET_NAME);
				updateForm.find('input[name=initial_amount]').val(result.INITIAL_AMOUNT);
				
				break;
			case "체크":
				// 체크박스 활성/비활성
				updateForm.find("input:radio[name=asset_type][value='현금']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='통장']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='체크']").prop("disabled", false);
				updateForm.find("input:radio[name=asset_type][value='신용']").prop("disabled", true);
				
				// asset_type 체크
				updateForm.find("input:radio[name=asset_type][value='체크']").prop("checked", true);
				
				// updateForm 변경
				updateForm.find("#updateCashAssetsDiv").hide();
				updateForm.find("#updateCardAssetsDiv").show();
				
				// 연결 통장 목록 제거
				fn_deleteBankAssetList();
				// 연결 통장 목록 보여주기
				fn_getBankAssetList(result.CONNECTION_ASSET_IDX);
				
				// 값 설정
				updateForm.find('input[name=asset_name]').val(result.ASSET_NAME);
				updateForm.find('input[name=initial_amount]').val('0');
				
				break;
			case "신용":
				// 체크박스 활성/비활성
				updateForm.find("input:radio[name=asset_type][value='현금']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='통장']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='체크']").prop("disabled", true);
				updateForm.find("input:radio[name=asset_type][value='신용']").prop("disabled", false);
				
				// asset_type 체크
				updateForm.find("input:radio[name=asset_type][value='신용']").prop("checked", true);
				
				// updateForm 변경
				updateForm.find("#updateCashAssetsDiv").show();
				updateForm.find("#updateCardAssetsDiv").show();
				
				// 연결 통장 목록 제거
				fn_deleteBankAssetList();
				// 연결 통장 목록 보여주기
				fn_getBankAssetList(result.CONNECTION_ASSET_IDX);
				
				// 값 설정
				updateForm.find('input[name=asset_name]').val(result.ASSET_NAME);
				updateForm.find('input[name=initial_amount]').val(result.INITIAL_AMOUNT);
				break;
			}
			
			$("#update-asset-modal").modal("show");
			
		}, // success끝 
		error: function(){
			alert("요청이 실패했거나 서비스가 제시간에 응답하지 못했습니다. 다시 시도해주세요.");
		}
	}); // ajax 끝
	
}

// 연결 통장 목록 불러오기 - insert modal의 select tag에 붙이기
function fn_getBankAssetList(connection_asset_idx) {
	
	var $select = $('select[name=connection_asset_idx]');
	var $option;
	
	$.ajax({
		url: '/app/ledger/getBankAssetList.do',
		type: 'post',
		success: function(result){
			$(result).each(function(){
				$option = '<option value="'+this.ASSET_IDX+'">'+this.ASSET_NAME+'</option>';
				$select.append($option);
			}); // each 끝
			
			if(connection_asset_idx != undefined) {
				updateForm.find('[name=connection_asset_idx]').val(connection_asset_idx).prop("selected", true);
			}
		}, // success 끝
		error: function(){
			
		}
	}); // ajax 끝
	
};

// 연결 통장 목록 지우기
function fn_deleteBankAssetList() {
	$('select[name=connection_asset_idx] option').remove();
};

// 자산 추가 submit 하기
function fn_insertAsset() {
	if(!fn_validAssetName(insertForm)){
		return;
	}
	
	insertForm[0].action = "/app/ledger/insertAsset.do";
	insertForm[0].submit();
}

// 자산 수정 submit 하기
function fn_updateAsset() {
	if(!fn_validAssetName(updateForm)){
		return;
	}
	
	updateForm[0].action = "/app/ledger/updateAsset.do";
	updateForm[0].submit();
}

// 유효성 검사: 자산 이름
function fn_validAssetName(from) {
	var nameLength = from.find('input[name=asset_name]').val().length;
	
	if(nameLength == 0) {
		alert("자산 이름을 입력해 주세요.");
		from.find('input[name=asset_name]').focus();
		return false;
	}
	if(nameLength > 45) {
		alert("자산 이름은 45자 이내로 작성해 주세요.");
		from.find('input[name=asset_name]').focus();
		return false;
	}
	return true;
}

// 유효성 검사: 초기 금액 - 숫자만 입력 가능 & 15 자리수 제한 & 세 자리수 마다 콤마
$("input[name=initial_amount]").keyup(function(event) {
	
	// 숫자 이외의 문자 제거 (- 기호와 숫자만 허용)
	var inputVal = $(this).val().replace(/[^-0-9]/gi,'');
    
	// 숫자 이외의 문자 제거 (- 기호 표시)
    if(inputVal.lastIndexOf("-")>0){ //중간에 -가 있다면 replace
        if(inputVal.indexOf("-")==0){ //음수라면 replace 후 - 붙여준다.
        	inputVal = "-"+inputVal.replace(/[-]/gi,'');
        }else{
        	inputVal = inputVal.replace(/[-]/gi,'');
        }
    
    }
    
    // 자리수 제한
    if(inputVal.length > 15) {
    	alert("금액 값은 15자리수 까지만 입력 가능합니다.");
    	inputVal = inputVal.slice(0, 15);
    }
    
    // 세 자리수 마다 콤마 찍기
    var reg = /(^[+-]?\d+)(\d{3})/;
    while(reg.test(inputVal)) {
    	inputVal = inputVal.replace(reg, '$1'+','+'$2');
    }
    
    $(this).val(inputVal);
    
});