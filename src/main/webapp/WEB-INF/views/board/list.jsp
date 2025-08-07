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
		                <h1 class="h3 mb-4 text-gray-800">${ board }</h1>
		                <div>
	                		<form method="get" id="frm">	
	                			<input type="hidden" name="pageNum" id="pageNum">
			                	<div class="input-group mb-3">
			                		<select class="form-select" aria-label="Default select example" name="kind">
			                			<option value="">Choose</option>
										<option value="k1" ${ pager.kind eq 'k1' ? 'selected' : '' }>Title</option>
										<option value="k2" ${ pager.kind eq 'k2' ? 'selected' : '' }>Content</option>
										<option value="k3" ${ pager.kind eq 'k3' ? 'selected' : '' }>Writer</option>
									</select>
									<input type="text" class="form-control" value="${ pager.keyword }" name="keyword" placeholder="Recipient’s username" aria-label="Recipient’s username" aria-describedby="button-addon2">
									<button class="btn btn-outline-secondary" id="button-addon2">Search</button>
								</div>
	                		</form>
		                </div>
	                	<table class="table">
	                		<thead>
	                			<tr>
	                				<th>No</th>
	                				<th>Title</th>
	                				<th>Writer</th>
	                				<th>Date</th>
	                				<th>Hit</th>
	                			</tr>
	                		</thead>
	                		<tbody>
	                			<c:forEach var="notice" items="${ list }">
		                			<tr>
		                				<td>${ notice.boardNum }</td>
		                				<td>
			                				<c:catch>
												<c:forEach begin="1" end="${ notice.boardDepth }">
												&#128511;
												</c:forEach>			                				
			                				</c:catch>
		                					<a href="./detail?boardNum=${ notice.boardNum }">${ notice.boardTitle }</a>
	                					</td>
		                				<td>${ notice.boardWriter }</td>
		                				<td>${ notice.boardDate }</td>
		                				<td>${ notice.boardHit }</td>
		                			</tr>	                			
	                			</c:forEach>
	                		</tbody>
	                	</table>
	                	<div>
							<nav aria-label="Page navigation example">
								<ul class="pagination">
									<li class="page-item"><a class="page-link pn" href="#" data-pn="${ pager.startNum - 1 }">Previous</a></li>
									<c:forEach begin="${ pager.startNum }" end="${ pager.endNum }" var="page">
										<li class="page-item"><a class="page-link pn" href="#" data-pn="${ page }">${ page }</a></li>									
									</c:forEach>
									<li class="page-item"><a class="page-link pn" href="#" data-pn="${ pager.endNum + 1 }">Next</a></li>
								</ul>
							</nav>
	                	</div>
	                	<div><a href="./add" class="btn btn-primary" role="button">글쓰기</a></div>
	                </div>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
	<script type="text/javascript" src="/js/board/board_list.js"></script>
</body>
</html>