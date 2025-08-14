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
	                <div class="col-md-8 offset-md-2">
		                <div class="card shadow mb-4">
		                    <div class="card-header py-3">
		                        <h6 class="m-0 font-weight-bold text-primary">${ detail.productName }</h6>
		                    </div>
		                    <div class="card-body">
		                    	상품 타입: ${ detail.productsKindVO.kindName }
		                    	<br>
		                    	이자율: ${ detail.productRate }% 
		                    	<br>
		                    	최대 가입 기간: ${ detail.productDate }
		                    	<br>
								상품 설명: ${ detail.productContent }
		                    </div>
		                </div>
	                	<form id="frm">
	                		<input type="hidden" name="productNum" class="productNum" value=${ detail.productNum }>
	                	</form>
		                <div class="d-flex justify-content-between">
			                <div><button class="btn btn-warning action" data-kind="u">Update Product</button></div>
			                <div><button class="btn btn-danger action" data-kind="d">Delete Product</button></div>
			                <div><button class="btn btn-primary action" data-kind="c">Add to Cart</button></div>
			                <div><button class="btn btn-primary action" data-kind="a">Purchase</button></div>			                	                			                
			                <div><a href="./list" class="btn btn-primary" role="button">Product List</a></div>
		                </div>
	                </div>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
	<script type="text/javascript" src="/js/products/products_detail.js"></script>
</body>
</html>