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
	                <form method="post">
						<%-- <input type="hidden" name="productName" value=${ notice.boardNum }> --%>
						<div class="mb-4">
							<label for="name" class="form-label">Product Name</label>
							<input type="text" class="form-control" id="name" name="productName" value="${ detail.productName }">
						</div>
						<div class="mb-4">
							<label for="date" class="form-label">Product Date</label>
							<input id="date" type="datetime-local" class="form-control" name="productDate" value="${ detail.productDate }">
						</div>
						<div class="mb-4">
							<label for="rate" class="form-label">Product Rate</label>
							<input id="rate" type="number" class="form-control" name="productRate" step="0.01" value="${ detail.productRate }">
						</div>
						<div class="mb-4">
							<label for="content">Product Detail</label>
							<textarea class="form-control" placeholder="Write your content here!" id="content" style="height: 100px" name="productContent">${ detail.productContent }</textarea>
						</div>
						<div class="col-md-4 d-flex justify-content-between">
							<div>
								<c:if test="${ detail.kindNum ne 1 }">
									<input class="form-check-input" type="radio" name="kindNum" id="radioDefault1" value="1">								
								</c:if>
								<c:if test="${ detail.kindNum eq 1 }">								
									<input class="form-check-input" type="radio" name="kindNum" id="radioDefault1" value="1" checked="checked">								
								</c:if>
								<label class="form-check-label" for="radioDefault1">예금</label>
							</div>
							<div>
								<c:if test="${ detail.kindNum ne 2 }">
									<input class="form-check-input" type="radio" name="kindNum" id="radioDefault2" value="2">								
								</c:if>
								<c:if test="${ detail.kindNum eq 2 }">
									<input class="form-check-input" type="radio" name="kindNum" id="radioDefault2" value="2" checked="checked">																
								</c:if>
								<label class="form-check-label" for="radioDefault2">적금</label>
							</div>
							<div>
								<c:if test="${ detail.kindNum ne 3 }">								
									<input class="form-check-input" type="radio" name="kindNum" id="radioDefault3" value="3">
								</c:if>
								<c:if test="${ detail.kindNum eq 3 }">									
									<input class="form-check-input" type="radio" name="kindNum" id="radioDefault3" value="3" checked="checked">
								</c:if>
								<label class="form-check-label" for="radioDefault3">대출</label>
							</div>
						</div>
					    <button type="submit" class="btn btn-primary">Submit</button>
					</form>
                </div>
			</div>
			<%@ include file="/WEB-INF/views/include/footer.jsp" %>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/tail.jsp" %>
</body>
</html>