<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vn.iostar.entity.Book" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Sách</title>
    <style>
        .detail { width: 50%; margin: 20px auto; padding: 20px; border: 1px solid #ccc; }
        img { max-width: 200px; height: auto; }
        a { text-decoration: none; padding: 5px 10px; background-color: #4CAF50; color: white; border-radius: 3px; }
        a:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <h2>Chi Tiết Sách</h2>
    <a href="${pageContext.request.contextPath}/ListBook">Quay lại danh sách</a>
    <div class="detail">
        <% 
            Book book = (Book) request.getAttribute("book");
            if (book != null) {
        %>
            <p><strong>Ảnh Bìa:</strong> 
                <img src="${pageContext.request.contextPath}/images/<%= book.getCoverImage() != null ? book.getCoverImage() : "default.jpg" %>" alt="<%= book.getTitle() %>" height="200" width="200">
            </p>
            <p><strong>Tiêu đề:</strong> <%= book.getTitle() != null ? book.getTitle() : "" %></p>
            <p><strong>Mã ISBN:</strong> <%= book.getIsbn() %></p>
            <p><strong>Publisher:</strong> <%= book.getPublisher() != null ? book.getPublisher() : "" %></p>
            <p><strong>Ngày Xuất Bản:</strong> <%= book.getPublishDate() != null ? book.getPublishDate() : "" %></p>
            <p><strong>Số lượng:</strong> <%= book.getQuantity() %></p>
            <p><strong>Giá:</strong> <%= book.getPrice() %></p>
            <p><strong>Đánh giá:</strong> <%= book.getDescription() %></p>
        <% } else { %>
            <p>Không tìm thấy sách.</p>
        <% } %>
    </div>
</body>
</html>