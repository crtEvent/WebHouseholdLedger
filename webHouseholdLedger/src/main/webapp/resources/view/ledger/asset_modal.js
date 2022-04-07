/**
 * 
 */
var insertForm = $("#assetInsertForm");

/* 자산 추가 Modal창 열기 */
function fn_openInsertModal(){
	$("#insert-asset-modal").modal("show");
};

/* Modal창 닫기 */
function fn_closeModal(){
	$("#insert-asset-modal").modal("hide");
}

//input:radio[name=asset_type]에 따라 Modal form변화
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

// 연결 통장 목록 불러오기 - insert modal의 select tag에 붙이기
function fn_getBankAssetList() {
	
	var $select = $('select[name=connection_asset_idx]');
	var $option;
	
	$.ajax({
		url: '/app/ledger/getBankAssetList.do',
		type: 'post',
		success: function(result){
			$(result).each(function(){
				$option = '<option value="'+this.ASSET_IDX+'">'+this.ASSET_NAME+'</option>';
				$select.append($option);
			});
		},
		error: function(){
			
		}
	});
	
};

// 연결 통장 목록 지우기
function fn_deleteBankAssetList() {
	$('select[name=connection_asset_idx] option').remove();
};

// 자산 추가 submit 하기
function fn_insertAsset() {
	if(!fn_validAssetName()){
		return;
	}
	
	insertForm[0].action = "/app/ledger/insertAsset.do";
	insertForm[0].submit();
}

// 유효성 검사: 자산 이름
function fn_validAssetName() {
	var nameLength = $('input[name=asset_name]').val().length;
	alert(nameLength);
	if(nameLength == 0) {
		alert("자산 이름을 입력해 주세요.");
		$('input[name=asset_name]').focus();
		return false;
	}
	if(nameLength > 45) {
		alert("자산 이름은 45자 이내로 작성해 주세요.");
		$('input[name=asset_name]').focus();
		return false;
	}
	return true;
}

// 유효성 검사: 초기 금액
$("input[name=initial_amount]").keyup(function(event) {
    var inputVal = $(this).val();
    $(this).val(inputVal.replace(/[^-][^0-9]/gi,''));
    
    if(inputVal.length > 15) {
    	alert("금액 값은 15자리수 까지만 입력 가능합니다.");
    	$(this).val(inputVal.slice(0, 15));
    }
});