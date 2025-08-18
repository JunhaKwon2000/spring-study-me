<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ include file="/WEB-INF/views/include/head_css.jsp" %>
</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="/WEB-INF/views/include/sidebar.jsp" %>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<%@ include file="/WEB-INF/views/include/topbar.jsp" %>
                <div class="container-fluid">
	                <!-- 페이지 본문 -->
	                <h1 class="h3 mb-4 text-gray-800">Welcome ${ detail.name }</h1>
	                <img alt="profile-image" src="/files/member/${ detail.profileVO.saveName }">
	                <br>
	                <p>[Username]</p>
	                <p>${ detail.username }</p>
	                <br>
	                <p>[Name]</p>
	                <p>${ detail.name }</p>
	                <br>
   	                <p>[Birthday]</p>
	                <p>${ detail.birth }</p>
	                <br>
	                <p>[Email]</p>
	                <p>${ detail.email }</p>
	                <br>
            	    <p>[Phone]</p>
	                <p>${ detail.phone }</p>
	                <br>
	                <p>[Role]</p>
	                <c:forEach items="${ detail.roleVO }" var="role">
	                	<p>${ role.roleName }</p>
	                </c:forEach>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
</body>
</html>