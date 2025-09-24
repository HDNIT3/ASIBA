<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi Tiết Sách</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">

	<h1 class="mb-4">Trang quản lý Book</h1>

	<c:if test="${not empty book}">
		<div class="card mb-3" style="max-width: 800px;">
			<div class="row g-0">
				<div class="col-md-4 text-center p-2">
					<c:url value="/images/${book.coverImage}" var="coverUrl" />
					<img src="${coverUrl}" class="img-fluid rounded" alt="Ảnh bìa">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h3 class="card-title">${book.title}</h3>
						<p>
							<strong>Mã ISBN:</strong> ${book.isbn}
						</p>
						<p>
							<strong>Nhà xuất bản:</strong> ${book.publisher}
						</p>
						<p>
							<strong>Ngày xuất bản:</strong> ${book.publishDate}
						</p>
						<p>
							<strong>Số lượng:</strong> ${book.quantity}
						</p>
						<p>
							<strong>Giá:</strong> ${book.price}
						</p>
						<p style="white-space: pre-wrap;">
							<strong>Đánh giá:</strong> ${book.description}
						</p>
						<form action="<c:url value='/ListBook' />" method="get">
							<button type="submit" class="btn btn-primary">Quay lại
								danh sách</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<form action="<c:url value='/SubmitReview' />" method="post"
		class="mt-3">
		<div class="mb-3">
			<label for="review" class="form-label"><strong>Đánh
					giá của bạn:</strong></label>
			<textarea id="review" name="review" class="form-control" rows="4"
				placeholder="Viết đánh giá của bạn..."></textarea>
		</div>
		<input type="hidden" name="bookId" value="${idbook}" /> <input
			type="hidden" name="loginname" value="${loginname}" />
		<button type="submit" class="btn btn-success">Gửi đánh giá</button>
	</form>
	<c:if test="${empty book}">
		<div class="alert alert-warning">Không tìm thấy sách.</div>
		<a href="<c:url value='/ListBook' />" class="btn btn-primary">Quay
			lại danh sách</a>
	</c:if>

</body>
</html>
