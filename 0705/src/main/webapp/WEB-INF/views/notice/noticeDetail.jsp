<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function noticeUpdate(noticenum){
		location.href="noticeUpdate?noticenum="+noticenum;
	}
	
	function noticeDelete(noticenum){
		alert(noticenum);
		if(confirm('삭제하시겠습니까?')==true){
			location.href="noticeDelete?noticenum="+noticenum;
		}
	}
</script>
<style>
	
	th {
		width: 100px;
	}
	
	td {
		width: 600px;
	}
</style>
</head>
<body>

<div>
	<table border="1">
		<tr>
			<th>제목</th>
			<td>${notice.title}</td>
		</tr>
		
		<tr>
			<th>작성일</th>
			<td>${notice.inputdate}</td>
		</tr>
		
		<tr>
			<th>첨부파일</th>
			<td>${notice.originalfile}</td>
		</tr>
		
		<tr>
			<th>조회수</th>
			<td>${notice.hits}</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td><pre>${notice.content}</pre></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="수정하기" onclick="noticeUpdate(${notice.noticenum});">
				<input type="button" value="삭제하기" onclick="noticeDelete(${notice.noticenum});">
			</td>
		</tr>
	</table>
	<a href="javascript:history.go(-1)">돌아가기</a>
</div>
</body>
</html>