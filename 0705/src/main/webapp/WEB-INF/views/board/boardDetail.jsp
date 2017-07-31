<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function updateReply(replynum, text){
		document.getElementById("inputReply").value = "댓글 수정";
		document.getElementById("text").value = text;
		document.getElementById("inputReply").onclick = function(){
			if(document.getElementById("inputReply").value == "댓글 수정"){
				var newText = document.getElementById("text").value;
				location.href = "replyUpdate?boardnum=${selectedBoard.boardnum}&replynum="+replynum+"&newText="+newText;
			}
		}
	}
	
	
	function replyWrite(){
		document.getElementById("insert").submit();
	}
	

	function deleteBoard(boardnum, currentPage, searchtype, searchword){
		if(confirm('삭제하시겠습니까?')==true){
			if((typeof searchword)=='undefined'){
				searchword = "";
			}
			location.href = "boardDelete?boardnum="+boardnum+"&currentPage="+currentPage+"&searchtype="+searchtype+"&searchword="+searchword;
		}
	}
	
	document.addEventListener('contextmenu', event => event.preventDefault());
</script>
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
	pre {
		width : 600px;
		height : 200px;
		overflow : auto;
	}
</style>
</head>
<body>
<div id="wrapper">
<h2>게시판</h2>
<form action="boardUpdateForm" method="GET" >
	<input type="hidden" name="boardnum" value="${selectedBoard.boardnum}">
	<input type="hidden" name="searchtype" value="${searchtype}">
	<input type="hidden" name="searchword" value="${searchword}">
	<input type="hidden" name="currentPage" value="${currentPage}">
	<table border="1">
	<tr>
		<th>제목</th>
		<td>${selectedBoard.title}</td>
	</tr>
	<tr>
		<th>글쓴이</th>
		<td>${selectedBoard.custid}</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
		<c:if test="${not empty mimetype}">
			<img src="download?boardnum=${selectedBoard.boardnum}" />
		</c:if>
		<c:if test="${selectedBoard.originalfile ne null}">
			<a href="download?boardnum=${selectedBoard.boardnum}&originalfile=${selectedBoard.originalfile}">${selectedBoard.originalfile}</a>
		</c:if>
		</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<pre>${selectedBoard.content}</pre>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${loginId eq selectedBoard.custid}">
				<input type="submit" value="수정하기" />
				<input type="button" value="삭제하기" onclick="deleteBoard(${selectedBoard.boardnum}, ${currentPage}, ${searchtype}, ${searchword});"/>
			</c:if>
			<a href="boardList?currentPage=${currentPage}&searchtype=${searchtype}&searchword=${searchword}" >목록으로</a>
			<a href="javascript:history.go(-1)" >history.go(-1)사용</a>
		</td>
	</tr>
	</table>
	
</form>
</div>


<!-- 댓글입력 : 로그인한 사람만 -->
<c:if test="${not empty custid}">
<form id="insert" action="replyInsert" method="POST">
<input type="hidden" name="custid" value="${custid}">
<input type="hidden" name="boardnum" value="${selectedBoard.boardnum}">
	<table>
		<tr>
			<td>
				<input type="text" id="text" name="insertReply">
				<input type="button" id="inputReply" value="댓글달기" onclick="replyWrite()">
			</td>
		</tr>
	</table>
</form>
</c:if>


<!-- 댓글 출력 부분 -->
<div class="replyDisplay">
<form action="" method="POST">
<table>
	<c:forEach var="reply" items="${replyList}">
		<tr>
			<td class="replyid">
				${reply.custid}
			</td>
			<td class="replytext">
				${reply.text}
			</td>
			<td class="replydate">
				${reply.inputdate}
			</td>
			<c:if test="${loginId eq reply.custid}">
				<td class="replybtn">
					<a href="replyDelete?boardnum=${selectedBoard.boardnum}&replynum=${reply.replynum}"><input type="button" id="delete" value="삭제"></a>
					<input type="button" id="update" value="수정" onclick="updateReply('${reply.replynum}', '${reply.text}')">
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
</form>
</div>



</body>
</html>