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
   	                <div class="row col-md-8 offset-md-2">
	                	<table class="table">
	                		<thead>
	                			<tr>
	                				<th>상품명</th>
	                				<th>이자율</th>
	                				<th>최대 가입 날짜</th>
	                			</tr>
	                		</thead>
	                		<tbody>
	                			<c:forEach var="products" items="${ list }">
		                			<tr>
		                				<td><a href="./detail?productNum=${ products.productNum }">${ products.productName }</a></td>
		                				<td>${ products.productRate }</td>
		                				<td>${ products.productDate }</td>
		                			</tr>	                			
	                			</c:forEach>
	                		</tbody>
	                	</table>
		                <div><a href="./add" class="btn btn-primary" role="button">Product Add</a></div>
	                </div>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
</body>
</html>