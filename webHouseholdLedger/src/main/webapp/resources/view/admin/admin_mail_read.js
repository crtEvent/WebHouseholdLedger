/**
 * 
 */

var url = new URL(window.location.href);

// 다른 메일 읽기 페이지로 이동
function fn_linkToMail(mail_idx) {
	
	if(mail_idx == null) {
		alert("다음글/이전글이 없습니다.");
		return;
	}
	
	var linkToMailForm = $("<form></form>");
	
	linkToMailForm.attr("name", "linkToMailForm");
	linkToMailForm.attr("method", "get");
	linkToMailForm.attr("action", url);
	
	linkToMailForm.append($("<input/>", {type: "hidden", name: "mail_idx", value: mail_idx}));
	
	linkToMailForm.appendTo("body");
	linkToMailForm.submit();
}
