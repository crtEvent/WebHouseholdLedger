/**
 * [RECEIVE_MAIL 변경]
 */

$('#receive_mail').change(function() {
	
	var dataToSend;
	
	if($('#receive_mail').is(":checked") == true) {
		dataToSend = {receive_mail : $('#receive_mail').val()};
	} else {
		dataToSend = {receive_mail : ""};
	}
	
	$.ajax({
		url: '/app/my/editReceiveMail.do',
		type: 'post',
		data: dataToSend,
		success: function(data){
			alert('이메일 수신 여부가 변경되었습니다.');
		},
		error: function(){
			alert('이메일 수신 여부 변경에 실패하였습니다. 다시 시도해 주세요.');
		}
	})
});