<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	#navigator {
		
	}
	
	#navigator span{
		font-size: xx-large;
		color: blue;
	}
	
	table {
		width: 900px;
	}
</style>
</head>
<body>

<!-- 검색 -->
<div>
	
</div>

<!-- 글 목록 -->
<div>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="notice" items="${noticeList}" varStatus="st">
			<tr>
				<td>${ (navi.currentPage-1) * 10 + st.count}</td>
				<td><a href="noticeDetail?noticenum=${notice.noticenum}">${notice.title}</a></td>
				<td>${notice.custid}</td>
				<td>${notice.inputdate}</td>
				<td>${notice.hits}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<!-- 페이징 -->
<div id="navigator">
	<a href="">◁◁</a>
	<a href="">◀</a>
	<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
		<c:if test="${page eq navi.currentPage}">
			<span>${page}</span>
		</c:if>
		
		<c:if test="${page ne navi.currentPage}">
			<a href="noticeList?currentPage=${page}">${page}</a>
		</c:if>
	</c:forEach>
	<a href="">▶</a>
	<a href="">▷▷</a>
</div>

<!-- 글쓰기 -->
<div>
	<c:if test="${loginId == 'admin'}">
		<a href="noticeWrite">공지사항 등록하기</a>
	</c:if>
</div>
</body>
</html>