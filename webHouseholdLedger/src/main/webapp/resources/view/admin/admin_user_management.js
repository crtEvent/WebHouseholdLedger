/**
 * 
 */

$(document).ready(function(){
	
	fn_loadJsGrid();

});

function fn_loadJsGrid() {
	var url = new URL(window.location.href);
	var urlParams = url.searchParams;
	console.log("url: "+url+"urlParams: "+urlParams);
	
	/* jsGrid */
	$("#userTable").jsGrid({
		width: "100%",
		height: "auto",

		autoload:   true,
		paging:     true,
		pageSize:   15,
		pageButtonCount: 5,
		pageIndex:  1,
		sorting: true,
		 
		controller: {
			loadData: function() {
				return $.ajax({
		            type: "GET",
		            url: "/app/admin/getUserManagementList.do?"+urlParams
		        });
	        
			}
		},
		fields: [
			{name:"user_idx", title:"idx", type: "text", align:"center", width: 30},
			{name:"user_id", title:"아이디", type: "text", align:"center"},
			{name:"user_email", title:"이메일", type: "text", align:"center"},
			{name:"post_num", title:"게시글 수", type: "text", align:"center", width: 60},
			{name:"comment_num", title:"댓글 수", type: "text", align:"center", width: 60},
			{name:"reg_date", title:"가입일", type: "text", align:"center"},
			{name:"receive_mail", title:"메일수신", type: "text", align:"center", width: 60},
			{name:"delete_yn", title:"탈퇴여부", type: "text", align:"center", width: 60}
		],
		rowClick: function(args) {
           //alert("d: "+args.item.user_id);
           fn_openUpdateUserInfoModal(args.item);
        },
	});
	
	/* start_date, end_date 설정 */
	let startDate = $("#start_date").val();
	let endDate = $('#end_date').val();
	if(startDate == null || startDate == ''){
		startDate = moment();
		endDate = moment();
	}
	
	/* Date range picker */
	$("#selectPeriod").daterangepicker({
		locale:{
			format: "YYYY-MM-DD",
			applyLabel: "확인",
			cancelLabel: "취소",
			daysOfWeek: [
				"일", "월", "화", "수", "목", "금", "토"
			],
			monthNames: [
				"1월", "2월", "3월", "4월",
				"5월", "6월", "7월", "8월",
				"9월", "10월", "11월", "12월"
			]
		},
		"maxSpan": {
	        "year": 1
	    },
	    opens: "center",
	    startDate: startDate,
		endDate: endDate
	}, function(start, end, label){
		$("#start_date").val(start.format("YYYY-MM-DD"));
		$("#end_date").val(end.format("YYYY-MM-DD"));
		fn_searchUserList('advenced');
	});
}

/* 유저 정보 검색 함수 */
function fn_searchUserList(type) {
	
	var searchUserListtForm = $("<form></form>");
	
	searchUserListtForm.attr("name","searchUserListtForm");
	searchUserListtForm.attr("method","get");
	searchUserListtForm.attr("action","/app/admin/userManagement.do");
	
	switch(type){
	case 'normal':
		searchUserListtForm.append($("<input/>", {type: "hidden", name: $('select[name=search_type] option:selected').val() , value: $('input[name=keyword]').val()}));
		break;
	case 'advenced':
		searchUserListtForm.append($("<input/>", {type: "hidden", name: 'start_date' , value: $("#start_date").val()}));
		searchUserListtForm.append($("<input/>", {type: "hidden", name: 'end_date' , value: $("#end_date").val()}));
		break;
	}
	
	searchUserListtForm.appendTo("body");
	
	searchUserListtForm.submit();
}

// ----------------------------------------------------------------------------------

/* [Modal] 유저 정보 수정 Modal 열기 */
function fn_openUpdateUserInfoModal(item) {
	
	$('#modal-updateUserInfo').modal('show');
	
	$('#user_idx').val(item.user_idx);
	$('#user_id').val(item.user_id);
	$('#user_email').val(item.user_email);
	if(item.receive_mail == 'Y') {
		$('#receive_mail').attr("checked", true);
	} else {
		$('#receive_mail').attr("checked", false);
	}
}

//[Modal]: Modal 창 닫기
function fn_closeModal() {
	$('#modal-updateUserInfo').modal('hide');
}
