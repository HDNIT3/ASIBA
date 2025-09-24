<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vn.iostar.entity.Book" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Sách</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>Danh Sách Sách</h2>
    <a href="${pageContext.request.contextPath}/addBook">Thêm Sách Mới</a>
    <table>
        <tr>
        	<th>Ảnh Bìa</th>
            <th>Tiêu đề</th>
            <th>Mã ISBN</th>
            <th>Publisher</th>
            <th>Ngày Xuất Bản</th>
            <th>Số lượng</th>
            <th>Giá</th>
            <th>Tác giả</th>
            <th>Hành động</th>
        </tr>
        <% 
            List<Book> bookList = (List<Book>) request.getAttribute("bookList");
            if (bookList != null) {
                for (Book book : bookList) {
        %>
            <tr>
            	<td><img src="${pageContext.request.contextPath}/images/<%= book.getCoverImage() != null ? book.getCoverImage() : "default.jpg" %>" height="100" width="100"></td>
                <td><%= book.getTitle() != null ? book.getTitle() : "" %></td>
                <td><%= book.getIsbn() %></td>
                <td><%= book.getPublisher() != null ? book.getPublisher() : "" %></td>
                <td><%= book.getPublishDate() != null ? book.getPublishDate() : "" %></td>
                <td><%= book.getQuantity() %></td>
                <td><%= book.getPrice() %></td>
                <td><%= book.getDescription() != null ? book.getDescription() : "" %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/bookDetail?bookid=<%= book.getBookid() %>">Xem chi tiết</a>
                </td>
            </tr>
        <% 
                }
            } else {
        %>
            <tr>
                <td colspan="7">Không có sách nào trong danh sách.</td> <!-- Cập nhật colspan thành 7 -->
            </tr>
        <% 
            }
        %>
    </table>
</body>
</html>