<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ include file="/WEB-INF/views/include/head_css.jsp" %>
</head>
<body>
	<div class="col-md-8 offset-md-2">
		<h1 class="h3 mb-4 text-gray-800">Welcome</h1>
		<form method="post" action="/member/login">
			<div class="mb-4">
				<label for="username" class="form-label">Username</label> 
				<input type="text" class="form-control" id="username" name="username" required="required">
			</div>
			<div class="mb-4">
				<label for="password" class="form-label">Password</label> 
				<input type="text" class="form-control" id="password" name="password" required="required">
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
			<a href="/member/join" class="btn btn-primary">Register</a>
		</form>
	</div>
</body>
</html>