<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<form action="noticeWrite" enctype="multipart/form-data" method="POST">
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"></td>
			</tr>
			
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="file"></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="10" cols="60"></textarea></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="등록하기">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>