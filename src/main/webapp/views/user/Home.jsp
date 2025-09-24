<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>
    <c:choose>
        <c:when test="${isAdmin}">
            <h2>Xin chào Admin!</h2>
        </c:when>
        <c:otherwise>
            <h2>Xin chào User!</h2>
        </c:otherwise>
    </c:choose>
</body>
</html>
