/**
 *  메일 쓰기 페이지에서 저장된 양식 리스트를 불러오는 Modal 관련 script
 */

function fn_loadJsGrid() {
	
	/* jsGrid */
	$("#storedMailTable").jsGrid({
		width: "100%",
		height: "auto",

		autoload:   true,
		paging:     true,
		pageSize:   10,
		pageButtonCount: 5,
		pageIndex:  1,
		sorting: true,
		
		controller: {
			loadData: function() {
				return $.ajax({
		            type: "GET",
		            url: "/app/admin/mail/getStoredMailList.do?" // 양식 list 불러오기
		        });
	        
			}
		},
		fields: [
			{name:"mail_subject", title:"제목", type:"text", align:"center"}
			],
			rowClick: function(args) {
	           //alert("d: "+args.item.mail_subject);
	           fn_loadStoredMailForm(args.item);
	        },
		});
} // fn_loadJsGrid() 끝

// 저장된 메일 양식 불러오기
function fn_loadStoredMailForm(item) {
	$('input[name=mail_subject').val(item.mail_subject);
	//$('textarea[name=mail_content]').html(item.mail_content);
	//alert($('textarea[name=mail_content]').val());
	$('#writeContentField').summernote('code',item.mail_content);
	$("#storedMailListModal").modal("hide");
}

// [Modal]: 저장된 메일 양식 Modal 열기
function fn_openStoredMailListModal(){
	fn_loadJsGrid();
	$("#storedMailListModal").modal("show");
}

// [Modal]: 창 닫기
function fn_closeModal(){
	$("#storedMailListModal").modal("hide");
}


