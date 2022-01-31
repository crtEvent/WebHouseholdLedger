/**
 * 
 */

$(document).ready(function(){
	
	$('#writeContentField').summernote({
		height: 450,
		lang: 'ko-KR',
		toolbar: [
			['style', ['bold', 'italic', 'underline', 'clear']],
			['fontsize', ['fontsize']],
		    ['color', ['color']],
		    ['para', ['paragraph']],
		    ['height', ['height']]
			['table', ['table']],
			['insert', ['link']]
		],
		fontSizes: ['10', '11', '12', '14', '16', '18', '24', '36', '48'],
		callbacks: {
			onKeyup: function (e) {
            	fn_viewContentFieldLength();
            },
			onPaste: function(e) {
				// 이미지 이중첨부 방지
            	var clipboardData = e.originalEvent.clipboardData;
				if (clipboardData && clipboardData.items && clipboardData.items.length) {
					var item = clipboardData.items[0];
					if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
						e.preventDefault();
					}
				}
			},
			onImageUpload : function(files) {
				alert('이미지는 첨부할 수 없습니다.');
			},
			onImageLinkInsert: function(url) {
				alert('이미지는 첨부할 수 없습니다.');
			}
		}
	});
	fn_viewContentFieldLength();
});

//페이지에 글자 수 보여주는 함수
function fn_viewContentFieldLength() {
	
	var contentFieldLength = $('#writeContentField').summernote('code')
	.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")
	.replace(/&([a-z0-9]+|#[0-9a-zA-Z]+);/gi, "0").length;
	
	if(contentFieldLength > 2000){
    	$('#maxContentLength').html('(<span class="text-danger">'+contentFieldLength+'자</span> / 최대 2,000자)');
    	return;
    }
    $('#maxContentLength').html('(<span>'+contentFieldLength+'자</span> / 최대 2,000자)');
}

//게시글 유효성 검사(제목, 내용 글자수 제한)
function fn_checkMailValid() {
	
	var to = $("input[name=mail_to]");
	var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	var subject = $("input[name=mail_subject]");
	var subjectLength = subject.val().replace(/s/g,"").length;
	var content = $("textarea[name=mail_content]");
	
	/* to:보내는 주소 체크 */
	if(!regEmail.test(to.val())){
		alert("이메일 주소 형식이 올바르지 않습니다. 다시 입력해 주세요.");
		to.focus();
		return false;
	}
	
	/* subject: 제목 체크 */
	if(subjectLength < 5 || subjectLength > 100){
		alert("게시글 제목은 5글자 이상 100글자 이하로 작성해 주세요.");
		subject.focus();
		return false;
	}
	
	/* content: 내용 체크 */
	if(content.val().length == 0){
		alert("게시글 내용을 작성해 주세요.");
		content.focus();
		return false;
	}

	var contentFieldLength = $('#writeContentField').summernote('code')
	.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")
	.replace(/&([a-z0-9]+|#[0-9a-zA-Z]+);/gi, "0").length;
	
	if(contentFieldLength > 2000){
		alert("게시글 내용은 2,000자를 초과할 수 없습니다.");
		content.focus();
		return false;
	}
	
	return true;
}

// 메일 보내기 함수
function fn_sendMail(mail_state) {
	
	if(!fn_checkMailValid()) {
		return;
	}
	
	var param = {mail_to : $('input[name=mail_to]').val(), 
			mail_subject : $('input[name=mail_subject]').val(), 
			mail_content : $('textarea[name=mail_content]').val(), 
			mail_state : mail_state};
	
	$.ajax({
		url: '/app/admin/mail/sendMail.do',
		type: 'post',
		data: param,
		success: function(result) {
			if(result){
				// 페이지 이동
				location.replace('/app/admin/mail/mailMessage.do?mail_message=success');
			} else {
				// 페이지 이동
				location.replace('/app/admin/mail/mailMessage.do?mail_message=fail');
			}
		},
		error: function() {
			// 페이지 이동
			location.replace('/app/admin/mail/mailMessage.do?mail_message=fail');
		}
	})
}