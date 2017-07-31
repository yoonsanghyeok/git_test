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
$(function(){
	$("#new_pw2").on('keyup', checkPassword);
	$("#image").on('change', fileUploadProcess);
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
	var new_pw = $("#new_pw").val();
	var new_pw2 = $("#new_pw2").val();
	if(new_pw != '' && new_pw == new_pw2){
		$("#pwCheckResult").text('비밀번호가 일치합니다');
		pwCheck = true;
	} else {
		$("#pwCheckResult").text('비밀번호가 일치하지 않습니다');
		pwCheck = false;
	}
};

function onSubmit(){
	var before_pw = $("#before_pw").val();
	var new_pw = $("#new_pw").val();
	var tel1 = $("#tel1 > option:selected").val();
	var tel2 = $("#tel2").val();
	var tel3 = $("#tel3").val();
	var image = $("#image").val();
	
	if(!(new_pw && new_pw2 && tel2 && tel3 && image)==true){
		alert('수정 정보를 모두 입력하세요');
		return false;
	}
	
	if("${loginUser.password}" != before_pw){
		alert('기존 패스워드가 일치하지 않습니다');
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
	<h2> [ 회원정보 수정 ]</h2>
	<form action="updateUser" method="POST" enctype="multipart/form-data" onsubmit="return onSubmit();">
	<input type="hidden" name="num" value="${loginUser.num}">
	<input type="hidden" name="userid" value="${loginUser.userid}">
	<input type="hidden" name="username" value="${loginUser.username}">
	<input type="hidden" name="birthdate" value="${loginUser.birthdate}">
	
	<table>
		<tr>
			<td>회원번호</td>
			<td>${loginUser.num}</td>
		</tr>
		
		<tr>
			<td>아이디</td>
			<td>${loginUser.userid}</td>
		</tr>

		<tr>
			<td>기존 패스워드</td>
			<td><input id="before_pw" type="password"></td>
		</tr>
		
		<tr>
			<td>새 패스워드</td>
			<td><input id="new_pw" name="password" type="password"></td>
		</tr>
		
		<tr>
			<td>새 패스워드 확인
			<td>
				<input id="new_pw2" type="password">
				<span id="pwCheckResult"></span>
			</td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td>${loginUser.username}</td>
		</tr>		
		
		<tr>
			<td>생년월일</td>
			<td>${loginUser.birthdate}</td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td>
				<select id="tel1" name="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="055">055</option>
				</select>
				<input id="tel2" name="tel2" type="text" size="4" value="${loginUser.tel2}">
				<input id="tel3" name="tel3" type="text" size="4" value="${loginUser.tel3}">
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
				<input id="submit" type="submit" value="수정하기">
				<input id="cancel" type="button" value="취소">
			</td>
		
	</table>
	</form>
</div>
</body>

</html>