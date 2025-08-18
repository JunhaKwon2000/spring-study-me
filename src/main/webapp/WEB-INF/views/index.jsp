<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	                <h1 class="h3 mb-4 text-gray-800">Hello World!</h1>
	                <h3>
	                	<spring:message code="welcome.message2" text="Welcome" />
	                </h3>
	                <c:if test="${ not empty member }">
	                	<h3>Welcome, ${ member.name }</h3>
	                	<h3>
	                		<spring:message code="user.info" arguments="${ member.username },${ member.email }" argumentSeparator="," />
	                	</h3>
	                </c:if>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
</body>
</html>