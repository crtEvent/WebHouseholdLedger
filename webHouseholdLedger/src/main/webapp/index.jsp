<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<p>Hello World</p>
	<div>
		<p><a href="<c:url value='/sample/testLogger.do'/>">/sample/testLogger.do</p>
		<p><a href="<c:url value='/sample/testDB.do'/>">/sample/testDB.do</p>
		<p><a href="<c:url value='/sample/testMyBatis.do'/>">/sample/testMyBatis.do</p>
		<p><a href="<c:url value='/sample/find_root.do'/>">/sample/find_root.do</p>
		<p><a href="<c:url value='/sample/auto_signin.do'/>">자동 로그인</p>
	</div>
	<div>
		<p><a href="<c:url value='/ledger/details.do'/>">/ledger/details.do</p>
		<p><a href="<c:url value='/ledger/calendar.do'/>">/ledger/calendar.do</p>
		<p><a href="<c:url value='/user/signin.do'/>">/user/signin.do</p>
		<p><a href="<c:url value='/user/signup.do'/>">/user/signup.do</p>
		<p><a href="<c:url value='/user/findUserInfo.do'/>">/user/findUserInfo.do</p>
	</div>
	<div>
		<p><a href="<c:url value='/stats/category.do'/>">/stats/category.do</p>
		<p><a href="<c:url value='/stats/yearly.do'/>">/stats/yearly.do</p>
	</div>
	<div>
		<p><a href="<c:url value='/qna/list.do'/>">/qna/list.do</p>
		<p><a href="<c:url value='/qna/post.do?board_idx=1'/>">//qna/post.do?board_idx=1</p>
		<p><a href="<c:url value='/qna/writePost.do'/>">/qna/writePost.do</p>
	</div>
	<div>
		<p><a href="<c:url value='/admin/memberManagement.do'/>">/admin/memberManagement.do</p>
		<p><a href="<c:url value='/admin/getUserManagementList.do'/>">/admin/getUserManagementList.do</p>
	</div>
	<div>
		<p><a href="<c:url value='/my/myinfo.do'/>">/my/myinfo.do</p>
	</div>
	
</body>
</html>