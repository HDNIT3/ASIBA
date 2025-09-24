<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Sách Mới</title>
    <style>
        table { border-collapse: collapse; width: 50%; }
        td, th { border: 1px solid black; padding: 8px; text-align: left; }
    </style>
</head>
<body>
    <h2>Thêm Sách Mới</h2>
    <form action="${pageContext.request.contextPath}/addBook" method="post">
        <table>
            <tr>
                <td>Tiêu đề:</td>
                <td><input type="text" name="title" value="Tiêu đề" required></td>
            </tr>
            <tr>
                <td>Mã ISBN:</td>
                <td><input type="number" name="isbn" value="0" required></td>
            </tr>
            <tr>
                <td>Tác giả:</td>
                <td><input type="text" name="description" value="Mô tả mặc định" required></td>
            </tr>
            <tr>
                <td>Publisher:</td>
                <td><input type="text" name="publisher" value="Publisher" required></td>
            </tr>
            <tr>
                <td>Publisher Date:</td>
                <td><%= new java.util.Date() %></td>
            </tr>
            <tr>
                <td>Số lượng:</td>
                <td><input type="number" name="quantity" value="10" required></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="price" name="price" value="100" required></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Thêm Sách"></td>
            </tr>
        </table>
    </form>
</body>
</html>