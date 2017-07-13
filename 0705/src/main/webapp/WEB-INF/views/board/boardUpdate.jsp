<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#wrapper{
		width : 800px;
		margin : 0 auto;
		
	}
	div#wrapper>h2 {
		text-align : center;
	}
	th {
		width: 100px;
	}
	td {
		width:600px;
	}
	div#wrapper table {
		margin:0 auto;
	}
</style>
<script type="text/javascript">
</script>
</head>
<body>
<div id="wrapper">
<h2>게시판</h2>
<form action="boardUpdate" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="boardnum" value="${board.boardnum}">
	<input type="hidden" name="searchtype" value="${searchtype}">
	<input type="hidden" name="searchword" value="${searchword}">
	<input type="hidden" name="currentPage" value="${currentPage}">
	
	<table border="1">
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" value="${board.title}"/></td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td>${loginId}</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<input type="file" name="upload"><br>
			${board.originalfile}
			<a href="deleteFile?boardnum=${board.boardnum}&currentPage=${currentPage}&searchtype=${searchtype}&searchword=${searchword}"><input type="button" value="파일 삭제"></a>
		</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td><textarea name="content" cols="60" rows="10">${board.content}</textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정완료" />
			<input type="reset" value="초기화" />
			<a href="boardList" >목록으로</a>
		</td>
	</tr>
	</table>
</form>
</div>
</body>
</html>