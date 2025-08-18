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
	                <h1 class="h3 mb-4 text-gray-800">Your Accounts</h1>
	                <div class="d-flex justify-content-around">
		                <c:forEach items="${ list }" var="account">
		                	<div class="card" style="width: 18rem;">
		 						<div class="card-body">
				                	<br>
				                	<p>Account Number: ${ account.accountNum }</p>
				                	<br>
				                	<p>Date: ${ account.accountDate }</p>
				                	<br>
				                	<p>Balance: ${ account.accountBalance }</p>
				                	<br>
				                	<p>Product Name: ${ account.productsVO.productName }</p>
				                	<br>				                	
				                	<p>Kind: ${ account.productsKindVO.kindName }</p>
			                	</div>
							</div>
		                </c:forEach>
	                </div>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
</body>
</html>