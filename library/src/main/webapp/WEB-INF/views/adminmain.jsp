<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	aside{
		width: 130px;
		float: left;
	}
	
	section {
		position: relative;
		left: 130px;
	}
</style>
<script src="resources/jquery-3.2.1.min.js"></script>
<script>
	$(function(){
		showRequestList();
	})
	
	if(${loginUser.userid != 'admin'}){
		alert('관리자로 로그인된 상태가 아닙니다. 메인 화면으로 이동합니다.');
		location.href="home";
	}
	
	$(function(){
		$("#requestlistBtn").click(showRequestList);
		$("#lentlistBtn").click(function(){
			$("section").load("resources/adminpage/lentlist.html");
			$.getScript("resources/adminpage/lentlist.js");
		});
		$("#delaylistBtn").click(function(){
			$("section").load("resources/adminpage/delaylist.html");
			$.getScript("resources/adminpage/delaylist.js");
		});
		$("#logout").click(function(){
			$.ajax({
				url: "logout"
				, success: function(){
					alert('관리자모드에서 로그아웃합니다');
					location.href="home";
				}
			})
		})
	});
	
	function showRequestList(){
	/* 	$.getScript({
			url: "resources/adminpage/requestlist.js"
			, success: function(data){
				//alert('script loaded');
			}
		}) */
		//$("section").getScript("resources/adminpage/requestlist.js"); dame
		$("section").load("resources/adminpage/requestlist.html");
		$.getScript("resources/adminpage/requestlist.js");
		
	}
</script>
</head>

<body>
<div id="wrapper">
	<header>
		<input id="requestlistBtn" type="button" value="대출 신청 목록">
		<input id="lentlistBtn" type="button" value="대출 목록">
		<input id="delaylistBtn" type="button" value="연체 목록">
	</header>
	<aside>
		<p>[ 관리자 로그인 ]</p><br>
		<input id="logout" type="button" value="로그아웃">
	</aside>
	<section>
	</section>
</div>
</body>

</html>