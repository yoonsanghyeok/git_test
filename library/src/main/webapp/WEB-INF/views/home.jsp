<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
header {
}

aside {
	position: fixed;
	float: left;
	width: 300px;
	height: 1500px;
	display: block;
}

section{
	position: relative;
	left: 300px;
}
</style>
<script src="resources/jquery-3.2.1.min.js"></script>
<script>
if(${loginUser.userid == 'admin'}){
	location.href = "toAdminPage";
}
$(function(){
	$("#signUpBtn").click(function(){
		location.href = "signUpForm";
	});
	$("#signInBtn").click(signIn);
	getBookList();
	$(".toList").click(getBookList);
	$(".toHistory").click(function(){
		$("article").load("resources/user/lendhistory.html");
		$.getScript("resources/user/lendhistory.js");
	});
	$("#searchBtn").on('click',searchBook);
});

function searchBook(){
	var searchtype = $("#searchtype > option:selected").val();
	var searchword = $("#searchword").val();
	$.ajax({
		url: "searchBook"
		, data: "searchtype="+searchtype+"&searchword="+searchword
		, success: printBookList
	});
};

function getBookList(){
	$("article").load("resources/book/booklist.html");	//얘가 printBookList함수의 첫번째 줄에 가니까 booklist.html안에 있는 booklist id를 인식 못하네
	$.ajax({
		url: "getbooklist"
		, dataType: "json"
		, success: printBookList
	});
};

function printBookList(resp){
	var str = JSON.stringify(resp);
	var tags = '';
	$.each(resp, function(index, item){
		tags += '<tr data-sno="'+item.NUM+'"><td class="bookimage">'+item.IMAGEURL+'</td>';
		tags += '<td class="booktitle">'+item.TITLE+'</td>';
		tags += '<td>'+item.PUBLISHER+'</td>';
		tags += '<td>'+item.AUTHOR+'</td>';
		tags += '<td>'+item.STATUS+'</td></tr>';
	});
	$("#booklist > tbody").html(tags);
	$("#booklist > tbody > tr").hover(function(){
		$(this).css({"background-color":"#eee"});
	}, function(){
		$(this).css({"background-color":"#fff"});
	});
	$(".bookimage").on('click',bookdetail);
	$(".booktitle").on('click',bookdetail);
};

function bookdetail(){
	var num = $(this).parent().attr("data-sno");
	$.ajax({
		url: "bookdetail"
		, data: "num="+num
		, success: printBookdetail
	});
};

function printBookdetail(selectedBook){
	var bookdetail_tag = '<table id="bookdetail" data-sno="'+selectedBook.NUM+'"><tr>';
	bookdetail_tag += '<td rowspan="4">'+selectedBook.IMAGEURL+'</td>';
	bookdetail_tag += '<td>제목</td><td>'+selectedBook.TITLE+'</td></tr>';
	bookdetail_tag += '<tr><td>출판사</td><td>'+selectedBook.PUBLISHER+'</td></tr>';
	bookdetail_tag += '<tr><td>저자</td><td>'+selectedBook.AUTHOR+'</td></tr>';
	bookdetail_tag += '<tr><td>대출가능</td><td>';
	//alert(selectedBook.STATUS);
	if(selectedBook.STATUS == 'RESERVED' || selectedBook.STATUS == 'LENT' || selectedBook.STATUS == 'DELAYED'){
		bookdetail_tag += '<span id="status">N</span>';
	} else {
		bookdetail_tag += '<span id="status">Y</span>';
	}
	//alert("${loginUser ne null}");
	if(${loginUser ne null} && (selectedBook.STATUS == undefined || selectedBook.STATUS == 'RETURNED' || selectedBook.STATUS == 'REJECTED' || selectedBook.STATUS == 'DELAY_RETURNED') ){
		bookdetail_tag += '<input id="requestReserve" type="button" value="대출 신청">';
	}
	bookdetail_tag += '</td></tr></table>';
	bookdetail_tag += '<h6>내용</h6>';
	bookdetail_tag += '<pre>'+selectedBook.CONTENT+'</pre>';
	bookdetail_tag += '<input class="toList" type="button" value="돌아가기">';
	$("article").html(bookdetail_tag);//비동기통신인 ajax의 특성상, 이 코드가 ajax메소드 아래에 위치하면 코드를 그리기 이전에 html을 등록해버리기 때문에 제대로 들어가지 않는다.
	$("#requestReserve").on('click', checkReserved);	
	$(".toList").on('click', getBookList);
};

function checkReserved(){
	var usernum = "${loginUser.num}";
	var booknum = $(this).parents("table#bookdetail").attr("data-sno");
	var target = $(this);
	$.ajax({
		url: "checkReserved"
		, data: "usernum="+usernum+"&booknum="+booknum
		, success: function(checkResult){
			//alert(checkResult);
			if(checkResult != ''){
				alert('이미 대출 신청이 완료되었습니다');
			} else {
				reserve(usernum, booknum, target);
			}
		}
	});
}

function reserve(usernum, booknum, target){
	//var usernum = "${loginUser.num}";
	//var booknum = $(this).parents("table#bookdetail").attr("data-sno");
	//alert(usernum);
	//alert(booknum);
	$.ajax({
		url: "reserve"
		, data: "usernum="+usernum+"&booknum="+booknum
		, success: function(result){
			if(result=='1'){
				$("#status").text('N');
				target.remove();
				alert('대출 신청이 완료되었습니다');
			} else {
				alert('오류가 발생하여 대출 신청에 실패했습니다');
			}
		}
	});
}
function signIn(){
	var userid = $("#userid").val();
	var password = $("#password").val();
	if(!(userid && password) == true){
		alert('아이디와 비밀번호를 입력하세요');
		return;
	}
	
	$.ajax({
		url: "signin"
		, method: "POST"
		, data: {"userid":userid, "password":password}
		, success: function(loginUser){
			if(loginUser == ''){
				alert('로그인에 실패했습니다');
			} else if(loginUser.userid == 'admin'){
				location.href = "toAdminPage";
			} else {
				alert(loginUser.username+'님 환영합니다!');
				/* var tags = '<table><tr>';
				tags += '<td><img src="img'+loginUser.thumbnail+'"/></td>';
				tags += '<td>'+loginUser.userid+'님 로그인 중<br>';
				tags += '회원번호<br>'+loginUser.num+'</br></tr>';
				tags += '<tr><td colspan="2"><input id="updateUserBtn" type="button" value="회원정보수정">'
				tags += '<input id="logout" type="button" value="로그아웃"></td></tr></table>';
				$("aside").html(tags);
				$("#logout").click(logout); */
				location.href="home";
			}
		}
	});
};

function setUserInfoPage(){
	var loginUser = "${loginUser}";
	//alert(loginUser);
	var tags = '<table><tr>';
	tags += '<td><img src="img'+"${loginUser.thumbnail}"+'"/></td>';
	tags += '<td>'+"${loginUser.userid}"+'님 로그인 중<br>';
	tags += '회원번호<br>'+"${loginUser.num}"+'</br></tr>';
	tags += '<tr><td colspan="2"><input id="updateUserBtn" type="button" value="회원정보수정">'
	tags += '<input id="logout" type="button" value="로그아웃"></td></tr></table>';
	$("aside").html(tags);
	$("#logout").click(logout);
	$("#updateUserBtn").click(function(){
		location.href="updateUserForm";
	})
	/* $.ajax({
		url: "showImage"
		, data: resp
		, success: function(img){
			alert(img);
			$('#userimage').html('<img src="data:image/png;base64,' + img + '" />');
		}
	}); */
};

function logout(){
	$.ajax({
		url: "logout"
		, success: function(){
			alert('로그아웃 되었습니다');
			location.href="home";
			
			
			/* $("aside").load("resources/loginForm.html");
			$("#signUpBtn").click(function(){
				location.href = "signUpForm";
			});
			$("#signInBtn").click(signIn); */
			
			
			
			/* $.get("resources/loginForm.html", function(data){
				$("aside").html(data);
			})*/
			/* $.get("C:\framework\springtest\library\src\main\webapp\WEB-INF\views/loginForm.html", function(data){
				$("aside").html(data);
			}); */
			//$("aside").html('<js'+'p:direct'+'ive.include file="loginForm.html"/>');
			//$("aside").html('<'+'%@ include file="loginForm.html"%>');
			//setLoginForm();
		}
	});
};

/* function setLoginForm(){
	var loginFormhtml = '';
	loginFormhtml += '<table id="loginform"><tr>';
	loginFormhtml += '<td>아이디</td>'
	loginFormhtml += '<td><input id="userid" type="text"></td></tr>';
	loginFormhtml += '<tr><td>패스워드</td>';
	loginFormhtml += '<td><input id="password" type="password"></td></tr>';
	loginFormhtml += '<tr><td colspan="2">';
	loginFormhtml += '<input id="signUpBtn" type="button" value="회원가입">';
	loginFormhtml += '<input id="signInBtn" type="button" value="로그인">';
	loginFormhtml += '</td></tr></table>';
	$("aside").html(loginFormhtml);	
	$("#signUpBtn").click(function(){
		location.href = "signUpForm";
	});
	$("#signInBtn").click(signIn);
} */
</script>
</head>

<body>
	<header>
		<c:if test="${loginUser ne null}">
			<input class="toList" type="button" value="도서목록">
			<input class="toHistory" type="button" value="대출이력">
		</c:if>
	</header>
	<aside>
		<!-- <script>alert("${loginUser}");alert("${loginUser ne null}");</script> -->
		<c:if test="${loginUser eq null}">
			<%-- <%@ include file="../../resources/loginForm.html" %> --%>
			<script>$("aside").load("resources/loginForm.html");</script>
		</c:if>
		<c:if test="${loginUser ne null}">
			<script>setUserInfoPage();</script>
		</c:if>
	</aside>
	
	<section>
		<nav id="searchForm">
			<select id="searchtype">
				<option value="title">제목</option>
				<option value="publisher">출판사</option>
				<option value="author">저자</option>
				<option value="status">대출가능</option>
			</select>
			<input id="searchword" type="text">
			<input id="searchBtn" type="button" value="검색">	
			<select id="countPerPage">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="15">15</option>
			</select>개씩 표시
			
			
		</nav>

		<nav id="paging">
			
		</nav>		
		
		<article>
			
		</article>
	</section>
</body>

</html>