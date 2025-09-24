<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <h1>Trang đăng nhập</h1>
    <form method="post" action="/OT1/login">
        <input type="text" name="fullname" placeholder="Tên đăng nhập"/>
        <input type="password" name="password" placeholder="Mật khẩu"/>
        <button type="submit">Đăng nhập</button>
    </form>
</body>
</html>