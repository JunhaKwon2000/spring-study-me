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
                	<h1 class="h3 mb-4 text-gray-800 text-uppercase">${ board }</h1>
					<p>제목 : ${ notice.boardTitle }</p>
					<p>작성자 : ${ notice.boardWriter } </p>
					<p>내용 : ${ notice.boardContent } </p>
					<p>작성일 : ${ notice.boardDate } </p>
					<p>[첨부파일]</p>
					<c:forEach items="${ notice.boardFileVO }" var="file">
						<a href="/files/${ board }/${ file.saveName }">${ file.oriName }</a>
						<br>
						${ file.saveName }							
					</c:forEach>
	                <div>
	                	<form id="frm">
	                		<input type="hidden" name="boardNum" value=${ notice.boardNum }>
	                	</form>
	                	<button class="btn btn-warning action" data-kind="u">Update</button>
	                	<button class="btn btn-danger action" data-kind="d">Delete</button>
	                	<c:if test="${ board ne 'notice' }">
		                	<button class="btn btn-primary action" data-kind="r">Reply</button>
	                	</c:if>
	                </div>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
	<script type="text/javascript" src="/js/board/board_detail.js"></script>
</body>
</html>