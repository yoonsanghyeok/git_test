<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style></style>
<script src="resources/jquery-3.2.1.min.js"></script>	
<script>
var pwCheck = false;
var idCheck = false;
$(function(){
	$("#password2").on('keyup', checkPassword);
	$("#image").on('change', fileUploadProcess);
	$("#checkid").on('click', checkid);
	$("#cancel").on('click', function(){
		location.href="home";
	})
});

function fileUploadProcess(){
	var filepath = $(this).val();
	if(filepath.length > 0){
		//썸네일 표시
		//var fileCheck = true;
	} else {
		//썸네일 제거
		//var fileCheck = false;
	}
	//아니면 조건 필요없이 무조건 썸네일 등록?
}
function checkPassword(){
	var password = $("#password").val();
	var password2 = $("#password2").val();
	if(password != '' && password == password2){
		$("#pwCheckResult").text('비밀번호가 일치합니다');
		pwCheck = true;
	} else {
		$("#pwCheckResult").text('비밀번호가 일치하지 않습니다');
		pwCheck = false;
	}
};

function checkid(){
	var userid = $("#userid").val();
	if(userid == ''){
		alert('아이디를 입력해주세요');
		return;
	}
	$.ajax({
		url: "checkid"
		, data: "userid="+userid
		, dataType: "text"
		, success: function(resp){
			if(resp == ''){
				$("#idCheckResult").text('사용할 수 있는 아이디입니다');
				idCheck = true;				
			} else {
				$("#idCheckResult").text('이미 가입된 아이디가 존재합니다');
				idCheck = false;
			}
		}
	});
}
function onSubmit(){
	var userid = $("#userid").val();
	var password = $("#password").val();
	var birthdate = $("#birthdate").val();
	var username = $("#username").val();
	var tel1 = $("#tel1 > option:selected").val();
	var tel2 = $("#tel2").val();
	var tel3 = $("#tel3").val();
	var image = $("#image").val();
	
	if(!(userid && password && password2 && birthdate && username && tel2 && tel3 && image)==true){
		alert('가입정보를 모두 입력하세요');
		return false;
	}
	
	if(idCheck == false){
		alert('아이디 중복확인을 해주세요');
		return false;
	}
	if(pwCheck == false){
		alert('비밀번호가 일치하지 않습니다');
		return false;
	}
	
	return true;
	
};
</script>
</head>

<body>
<div id="wrapper">
	<h2> [ 회원가입 ]</h2>
	<form action="signUp" method="POST" enctype="multipart/form-data" onsubmit="return onSubmit();">
	<table>
		<tr>
			<td>아이디</td>
			<td>
				<input id="userid" name="userid" type="text">
				<input id="checkid" type="button" value="중복확인">
				<span id="idCheckResult"></span>
			</td>
		</tr>

		<tr>
			<td>패스워드</td>
			<td><input id="password" name="password" type="password"></td>
		</tr>
		
		<tr>
			<td>패스워드<br>확인</td>
			<td>
				<input id="password2" type="password">
				<span id="pwCheckResult"></span>
			</td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td><input id="username" name="username" type="text"></td>
		</tr>		
		
		<tr>
			<td>생년월일</td>
			<td><input id="birthdate" name="birthdate" type="date"></td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td>
				<select id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="055">055</option>
				</select>
				<input id="tel2" name="tel2" type="text" size="4">
				<input id="tel3" name="tel3" type="text" size="4">
			</td>
		</tr>
		
		<tr>
			<td>사진</td>
			<td>
				<input id="image" type="file" name="file"><br>
				<div id="uploadedImage"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input id="submit" type="submit" value="가입하기">
				<input id="cancel" type="button" value="취소">
			</td>
		
	</table>
	</form>
</div>
</body>

</html>