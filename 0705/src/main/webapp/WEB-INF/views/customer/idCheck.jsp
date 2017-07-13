<%@page import="sesoc.global.test.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload = function(){
		document.getElementById("idCheck").onclick=function(){
			var id = document.getElementById("custid").value;
			if(id.length < 3 || id.length > 10){
				alert('3~10자리로 입력');
				return;
			}
			
			if(id.charAt(0) < 'a' || id.charAt(0) > 'z'){
				alert('id는 영문 소문자로 시작');
				return;
			}
			
			var checkForm = document.getElementById("checkForm");
			checkForm.submit();
		}
	};
	
	function idSelect(id){
		opener.document.getElementById("custid").value = id;
		this.close();
	};
</script>
</head>
<body>
<div>
	<h2>[ 아이디 중복확인 ] </h2>
	<form id="checkForm" action="${pageContext.request.contextPath}/customer?action=idcheck" method="POST">
		<input type="text" id="custid" name="custid" placeholder="3~10아이디 입력" />
		<input id="idCheck" type="button" value="중복확인" />  
	</form>
</div>
<div id="result">

<c:if test="${pageContext.request.method eq 'POST'}">
	
	<c:if test="${requestScope.customer eq null}">
	<script>alert('사용가능');</script>
			<div>
				<input type="button" value="${requestScope.id}사용하기" onclick="idSelect('${requestScope.id}')">
			</div>
	</c:if>

	<c:if test="${requestScope.customer ne null}">
			<script>alert('사용불가');</script>
	</c:if>

</c:if>
</div>
</body>
</html>





















