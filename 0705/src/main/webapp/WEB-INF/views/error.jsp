<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="5;${pageContext.request.contextPath}/">
<title>Insert title here</title>
<style>
	#wrapper{
		width : 700px;
		height : auto;
		text-align : center;
		margin : 0 auto;
	}
</style>
</head>
<body>
<div id="wrapper">
<h2> 오류 발생 - ${errormsg} </h2>
<img src="resources/error.png"/>
<p>5초 뒤에 첫 화면으로 이동합니다. 잠시 후 다시 이용해 주세요</p>
</div>
</body>
</html>