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
	                <h1 class="h3 mb-4 text-gray-800">Product Cart</h1>
	                <label for="check-all">Select All</label>
	                <input type="checkbox" id="check-all">
	                <div class="d-flex justify-content-around">
		                <c:forEach items="${ list }" var="product">
		                	<div class="card" style="width: 18rem;">
		 						<div class="card-body">
		 							<div class="d-flex justify-content-between">
					                	<h5 class="card-title"><a href="../products/detail?productNum=${ product.productNum }">${ product.productName }</a></h5>
					                	<input type="checkbox" class="ch" value="${ product.productNum }">
		 							</div>
				                	<br>
				                	<p>Details: ${ product.productContent }</p>
				                	<br>
				                	<p>Date: ${ product.productDate }</p>
				                	<br>
				                	<p>Rate: ${ product.productRate }</p>
				                	<br>
				                	<p>Kind: ${ product.productsKindVO.kindName }</p>
			                	</div>
							</div>
		                </c:forEach>
	                </div>
	                <form id="frm"><input type="hidden" id="delInp" name="productNum"></form>
	                <div>
	                	<button class="btn btn-danger delete">Delete</button>
	                	<button class="btn btn-primary purchase">Purchase</button>
	                </div>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
	
	<script type="text/javascript" src="/js/member/member_cart.js"></script>
</body>
</html>