<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
header {
	background-color: #333;
	color: white;
	padding: 10px 20px;
}

nav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

nav ul li {
	display: inline;
	margin-right: 20px;
}

nav ul li a {
	color: white;
	text-decoration: none;
}
</style>
<header>
	<nav>
		<ul>
			<li><a href="<%=request.getContextPath()%>/home">Trang Chủ</a></li>
			<li><a href="<%=request.getContextPath()%>/ListBook">Sản
					phẩm</a></li>
			<li><a href="<%=request.getContextPath()%>/login">Đăng
					nhập</a></li>
			<%
			Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
			if (isAdmin != null && isAdmin) {
			%>
			<li><a href="<%=request.getContextPath()%>/admin/home">Trang
					quản trị</a></li>
			<%
			}
			%>
			<li><a href="<%=request.getContextPath()%>/logout">Đăng
					xuất</a></li>
			<li><a href="<%=request.getContextPath()%>/register">Đăng
					kí</a></li>
		</ul>
	</nav>
</header>