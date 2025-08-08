<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ include file="/WEB-INF/views/include/head_css.jsp"%>
</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="/WEB-INF/views/include/sidebar.jsp"%>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<%@ include file="/WEB-INF/views/include/topbar.jsp"%>
				<div class="container-fluid">
					<!-- 페이지 본문 -->
					<h1 class="h3 mb-4 text-gray-800">Write Page</h1>
					<form method="post" enctype="multipart/form-data">
						<input type="hidden" name="boardNum" value=${ notice.boardNum }>
						<div class="mb-4">
							<label for="title" class="form-label">Title</label> <input
								type="text" class="form-control" id="title" name="boardTitle"
								value="${ notice.boardTitle }">
						</div>
						<div class="mb-4">
							<label for="writer" class="form-label">Writer</label> <input
								type="text" class="form-control" id="writer" name="boardWriter"
								value="${ notice.boardWriter }">
						</div>
						<div class="mb-4">
							<label for="content">Comments</label>
							<textarea class="form-control"
								placeholder="Write your content here!" id="content"
								style="height: 100px" name="boardContent">${ notice.boardContent }</textarea>
						</div>
						<div>
							<button type="button" id="add" class="btn btn-primary">File Plus</button>
						</div>
						<div>
							<c:forEach items="${ notice.boardFileVO }" var="file">
								<button type="button" class="deleteFile" data-file-num="${ file.fileNum }">${ file.oriName }</button>
							</c:forEach>
						</div>
						<!-- 업데이트 할 때 현재 파일의 개수를 보여주는 것 -->
						<!-- ${notice.boardFileVO.size()} 이것도 가능 -->
						<div id="result" data-file-count="${notice.boardFileVO.size()}">
							<!-- File Plus -->
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp"%>
	<script type="text/javascript" src="/js/board/board_add.js"></script>
</body>
</html>