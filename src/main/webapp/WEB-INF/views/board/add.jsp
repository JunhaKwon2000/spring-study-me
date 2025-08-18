<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ include file="/WEB-INF/views/include/head_css.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet">
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
					<form:form method="post" enctype="multipart/form-data" modelAttribute="boardVO">
						<form:hidden path="boardNum"/>
						<%-- <input type="hidden" name="boardNum" value=${ notice.boardNum }> --%>
						<div class="mb-4">
							<label for="boardTitle" class="form-label">Title</label>
							<form:input path="boardTitle" cssClass="form-control"/>
							<form:errors path="boardTitle"></form:errors>
							<%-- <input type="text" class="form-control" id="title" name="boardTitle" value="${ notice.boardTitle }"> --%>
						</div>
						<div class="mb-4">
				        	<span>${ member.username }</span>
						</div>
						<div class="mb-4">
							<label for="content">Comments</label>
							<textarea class="form-control"
								placeholder="Write your content here!" id="contents"
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
					</form:form>
				</div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp"%>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp"%>
	<script type="text/javascript" src="/js/board/board_add.js"></script>	
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
	<script type="text/javascript">
		$('#contents').summernote({
			callbacks: {
				onImageUpload: (files) => {
					let form = new FormData();
					form.append('boardFile', files[0]);
					fetch("./boardFile", {
						method: 'POST',
						body: form
					})
					.then(response => response.text())
					.then(response => {
						// console.log(response);
						$('#contents').summernote('editor.insertImage', response);
					})
					.catch(e => console.log(e));
				},
				onMediaDelete: (files) => {
					let form = new FormData();
					form.append('fileName', $(files[0]).attr('src')); // /files/notice/...jpg
					fetch("./boardFileDelete", {
						method: 'POST',
						body: form
					})
					.then(response => response.json())
					.then(response => {
						if (response) {
							window.alert('File Deleted');
						} else {
							window.alert('Delete Fail');							
						}
					})
				}
			}
		});
	</script>
</body>
</html>