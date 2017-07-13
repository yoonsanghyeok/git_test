<%@page import="sesoc.global.test.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#wrapper {
		width:900px;
		margin : 0 auto;
		text-align : center;
	}
	table {
		width:900px;
	}
	.title {			 		/*클래스 이름이 title인 것만 끄집어냄*/
		width : 450px;
	}
	th {
		width : 100px;
		background : #efefef;
	}
	.write {
		text-align : right;
	}
	.home {
		text-align : left;
	}
	a, a:link, a:visited, a:active {
		text-decoration : none;
		color : #000;  /*(=#000000)*/
	}
	a:hover {
		color : #0F0;
		font-weight : bold;
	}
	div.search{
		text-align : right;
	}
</style>
</head>
<body>
<div id="wrapper">
<h2>[ 게시판 글 목록 ]</h2>
<div class="home"><a href="${pageContext.request.contextPath}/"><img src="resources/home.png" /></a></div>


<div class="search">
<form action="boardList" method="GET">
	<select name="searchtype">
		<option value="title" ${searchtype=='title' ? 'selected' : ''}>제목</option>
		<option value="custid" ${searchtype=='custid' ? 'selected' : ''}>작성자</option>
		<option value="content" ${searchtype=='content' ? 'selected' : ''}>글 내용</option>
	</select>
	<input type="text" name="searchword" value="${searchword}">
	<input type="submit" value="검색">
</form>
</div>

<!-- 글 목록이 출력되는 부분 -->

<table border='1'>
	<tr>
		<th>번호</th>
		<th class="title">글제목</th>
		<th>글쓴날</th>
		<th>글쓴이</th>
		<th>조회수</th>
	</tr>
	
	<c:forEach var="board" items="${boardList}" varStatus="st">
	<tr>
		<td>${st.count + (navi.currentPage-1)*10}</td>
		
		<td class="title">
		<c:if test="${board.savedfile ne null}">
			<img src="resources/첨부파일.png" width="10px" height="10px"/>
		</c:if>
		<a href="boardDetail?boardnum=${board.boardnum}&currentPage=${currentPage}&searchtype=${searchtype}&searchword=${searchword}">${board.title}</a>
		</td>
		<td>${board.inputdate}</td>
		<td>${board.custid}</td>
		<td>${board.hits}</td>
	</tr>
	</c:forEach>

</table>
	<c:if test="${not empty loginId}">
		<div class="write"><a href="boardWrite">글쓰기</a></div>
	</c:if>


<!-- 페이징 -->
<div id="navigator">
	<a href="boardList?currentPage=${navi.currentPage-navi.pagePerGroup}">◀◀</a> 
	&nbsp;&nbsp;
	<a href="boardList?currentPage=${navi.currentPage-1}">◁</a>
	&nbsp;&nbsp;
	<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
		<c:if test="${navi.currentPage == page}">
			<span style="color:#00F; font-weight:bolder; font-size:2em;">${page}</span>
		</c:if>
		<c:if test="${navi.currentPage ne page}">
			<a href="boardList?currentPage=${page}&searchtype=${searchtype}&searchword=${searchword}">${page}</a>
		</c:if>
	</c:forEach>
	&nbsp;&nbsp;
	<a href="boardList?currentPage=${navi.currentPage+1}">▷</a>
	&nbsp;&nbsp; 
	<a href="boardList?currentPage=${navi.currentPage+navi.pagePerGroup}">▶▶</a>
</div>



<%-- <div>
<c:forEach var="pCount" begin="1" end="${navi.totalPageCount}">
	<c:if test="${navi.currentPage == pCount}">
		<span style="color:#00F; font-weight:bolder; font-size:2em;">${pCount}</span>
	</c:if>
	<c:if test="${navi.currentPage ne pCount}">
	<a href="boardList?currentPage=${pCount}&searchtype=${searchtype}&searchword=${searchword}">${pCount}</a>
	</c:if>
</c:forEach>
</div> --%>

</div>
</body>
</html>





