/**
 * 
 */

function fn_upAssetOrder(asset_idx, element) {
	
	$.ajax({
		url: "/app/ledger/changeAssetOrderUp.do",
		type: 'post',
		data: {asset_idx : asset_idx},
		success: function(result){
			if(result == true){
				var $tr = $(element).closest("tr");
				$tr.prev().before($tr);
			} else {
				alert("요청 처리 중에 오류가 발생했습니다.다시 시도해 주세요.");
			}
		}
	})
	
}

function fn_downAssetOrder(asset_idx, element) {
	
	$.ajax({
		url: "/app/ledger/changeAssetOrderDown.do",
		type: 'post',
		data: {asset_idx : asset_idx},
		success: function(result){
			if(result == true){
				var $tr = $(element).closest("tr");
				$tr.next().after($tr);
			} else {
				alert("요청 처리 중에 오류가 발생했습니다.다시 시도해 주세요.");
			}
		}
	})
	
}