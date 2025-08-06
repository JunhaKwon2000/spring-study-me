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
	                <h1 class="h3 mb-4 text-gray-800">${ board }</h1>
	                <div class="row col-md-8 offset-md-2">
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
									<li class="page-item"><a class="page-link" href="./list?pageNum=${ pager.startNum - 1 }">Previous</a></li>
									<c:forEach begin="${ pager.startNum }" end="${ pager.endNum }" var="page">
										<li class="page-item"><a class="page-link" href="./list?pageNum=${ page }">${ page }</a></li>									
									</c:forEach>
									<li class="page-item"><a class="page-link" href="./list?pageNum=${ pager.endNum + 1 }">Next</a></li>
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
</body>
</html>