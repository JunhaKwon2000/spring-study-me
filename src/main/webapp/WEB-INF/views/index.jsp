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
		<h1 class="h3 mb-4 text-gray-800">Login Page</h1>
		<form method="post" enctype="multipart/form-data">
			<div class="mb-4">
				<label for="username" class="form-label">Username</label> <input
					type="text" class="form-control" id="username" name="username">
			</div>
			<div class="mb-4">
				<label for="password" class="form-label">Password</label> <input
					type="text" class="form-control" id="password" name="password">
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
		</form>
		<a href="/member/join" class="btn btn-primary">Register</a>
	</div>
</body>
</html>