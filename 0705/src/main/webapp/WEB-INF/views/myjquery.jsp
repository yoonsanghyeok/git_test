<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="resources/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	alert('경고!!');
	$(".a").css("color","red");
	var search = $("#song");
	search.css("font-size", "1.2em");
});
</script>
</head>
<body>

<div>
	<p id="song">동해물과</p>
	<p class="a">백두산이</p>
	<p>마르고</p>
	<p class="a">닳도록</p>
</div>	
</body>
</html>