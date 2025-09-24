<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    html, body {
        height: 100%;
        margin: 0;
    }
    .wrapper {
        min-height: 100%;
        display: flex;
        flex-direction: column;
    }
    .content {
        flex: 1; /* chiếm hết phần trống */
    }
</style>
</head>
<body>
    <div class="wrapper">
        <div>
            <%@ include file="/common/web/header.jsp"%>
        </div>

        <div class="content">
            <sitemesh:write property="body" />
        </div>

        <div>
            <%@ include file="/common/web/footer.jsp"%>
        </div>
    </div>
</body>
</html>
