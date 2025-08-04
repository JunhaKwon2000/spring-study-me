<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
					<h1 class="h3 mb-4 text-gray-800">Notice Write Page</h1>
					<form method="post">
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
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp"%>
</body>
</html>