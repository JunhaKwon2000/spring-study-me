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
		<h1 class="h3 mb-4 text-gray-800">Register Page</h1>
		<form method="post" enctype="multipart/form-data">
			<div class="mb-4">
				<label for="username" class="form-label">Username</label> <input
					type="text" class="form-control" id="username" name="username">
			</div>
			<div class="mb-4">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password" name="password">
			</div>
			<div class="mb-4">
				<label for="name" class="form-label">Name</label> <input
					type="text" class="form-control" id="name" name="name">
			</div>
			<div class="mb-4">
				<label for="email" class="form-label">Email</label> <input
					type="email" class="form-control" id="email" name="email">
			</div>
			<div class="mb-4">
				<label for="phone" class="form-label">Phone</label> <input
					type="text" class="form-control" id="phone" name="phone">
			</div>
			<div class="mb-4">
				<label for="birth" class="form-label">Birth</label> <input
					type="date" class="form-control" id="birth" name="birth">
			</div>
			<div class="mb-4">
				<label for="profile" class="form-label">Profile</label>
				<br>
				<input type="file" id="profile" name="profile">
			</div>
			<button type="submit" class="btn btn-primary">Register</button>
		</form>
	</div>
</body>
</html>