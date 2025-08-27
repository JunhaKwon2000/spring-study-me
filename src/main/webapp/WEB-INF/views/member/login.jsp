<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<%@ include file="/WEB-INF/views/include/head_css.jsp" %>
</head>
<body>
	<div class="col-md-8 offset-md-2">
		<h1 class="h3 mb-4 text-gray-800">Welcome</h1>
		<h3 class="h3">${ param.failMsg }</h3>
		<form method="post" action="/member/login">
			<div class="mb-4">
				<label for="username" class="form-label">Username</label> 
				<input type="text" class="form-control" id="username" name="username" required="required" value="${ cookie.rememberMe.value }">				
			</div>
			<div class="mb-4">
				<label for="password" class="form-label">Password</label> 
				<input type="text" class="form-control" id="password" name="password" required="required">
			</div>
			<div class="mb-4">
				<label for="rememberMe">Remember Me</label>
				<input type="checkbox" name="rememberMe" id="rememberMe" value="1">
			</div>
			<div class="mb-4">
				<label for="remember-me">Remember Me on this PC</label>
				<input type="checkbox" name="remember-me" id="remember-me" value="1">
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
			<a href="/member/join" class="btn btn-primary">Register</a>
		</form>
		<div>
			<a href="/member/kakaoLogin" class="btn btn-secondary">카카오로 로그인</a>
		</div>
	</div>
</body>
</html>