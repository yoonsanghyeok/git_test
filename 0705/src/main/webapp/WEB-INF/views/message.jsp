<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<script>
	alert("${message}");
	/* location.href="${pageContext.request.contextPath}/index.jsp"; */
	/* location.href="customer?action?=/"; */
	location.href="${pageContext.request.contextPath}/";
</script>
</body>
</html>