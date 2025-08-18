<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ include file="/WEB-INF/views/include/head_css.jsp" %>
</head>
<body>
	<div class="col-md-8 offset-md-2">
		<h1 class="h3 mb-4 text-gray-800">Update Page</h1>
		<form:form method="post" enctype="multipart/form-data"  modelAttribute="memberVO">
			<div class="mb-4">
				<label for="name" class="form-label">Name</label>
				<form:input path="name" cssClass="form-control"/>
				<!-- <input type="text" class="form-control" id="name" name="name"> -->
				<form:errors path="name"></form:errors>
			</div>
			<div class="mb-4">
				<label for="email" class="form-label">Email</label>
				<form:input path="email" cssClass="form-control"/>
				<!-- <input type="email" class="form-control" id="email" name="email"> -->
				<form:errors path="email"></form:errors>
			</div>
			<div class="mb-4">
				<label for="phone" class="form-label">Phone</label>
				<form:input path="phone" cssClass="form-control"/>
				<!-- <input type="text" class="form-control" id="phone" name="phone"> -->
				<form:errors path="phone"></form:errors>
			</div>
			<div class="mb-4">
				<label for="birth" class="form-label">Birth</label>
				<input type="date" class="form-control" id="birth" name="birth" value="${ memberVO.birth }">
				<form:errors path="birth"></form:errors>
			</div>
			<div class="mb-4">
				<label for="profile" class="form-label">Profile</label>
				<br>
				<input type="file" id="profile" name="profile">
			</div>
			<button type="submit" class="btn btn-primary">Update</button>
		</form:form>
	</div>
</body>
</html>