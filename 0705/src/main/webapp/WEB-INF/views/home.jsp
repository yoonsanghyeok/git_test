<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<h2> 데이터 전송 테스트 </h2>
	<form action="input" method="POST">
		<label>이름 : <input type="text" name="name"></label>
		<label>나이 : <input type="text" name="age"></label>
		<label>전화번호 : <input type="text" name="phone"></label>
		<input type="submit">
	</form>
	<hr>
	
	<h2> 수집된 데이터 출력 </h2>
	friend : ${friend}<br>
	message : ${msg}
</body>
</html>
