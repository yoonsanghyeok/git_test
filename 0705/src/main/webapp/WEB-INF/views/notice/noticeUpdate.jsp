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
	<form action="noticeUpdate" enctype="multipart/form-data" method="POST">
		<input type="hidden" name="noticenum" value="${notice.noticenum}">
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${notice.title}"></td>
			</tr>
			
			<tr>
				<td>첨부파일</td>
				<td>
					${notice.originalfile}
					<input type="file" name="file">
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="10" cols="60">${notice.content}</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="수정하기">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>