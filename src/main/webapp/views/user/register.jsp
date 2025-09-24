<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="w-full max-w-md bg-white rounded-lg shadow-lg p-8">
        <h2 class="text-2xl font-bold mb-6 text-center">Đăng ký tài khoản</h2>

        <% if (request.getAttribute("error") != null) { %>
            <div class="bg-red-100 text-red-700 px-4 py-2 mb-4 rounded">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <% if (request.getAttribute("message") != null) { %>
            <div class="bg-green-100 text-green-700 px-4 py-2 mb-4 rounded">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/register" method="post" class="space-y-4">
            <div>
                <label class="block text-gray-700">Họ và tên</label>
                <input type="text" name="fullname" required 
                       class="w-full px-3 py-2 border rounded focus:outline-none focus:ring focus:ring-blue-300">
            </div>

            <div>
                <label class="block text-gray-700">Email</label>
                <input type="email" name="email" required 
                       class="w-full px-3 py-2 border rounded focus:outline-none focus:ring focus:ring-blue-300">
            </div>

            <div>
                <label class="block text-gray-700">Mật khẩu</label>
                <input type="password" name="password" required 
                       class="w-full px-3 py-2 border rounded focus:outline-none focus:ring focus:ring-blue-300">
            </div>

            <div>
                <label class="block text-gray-700">Xác nhận mật khẩu</label>
                <input type="password" name="confirmPassword" required 
                       class="w-full px-3 py-2 border rounded focus:outline-none focus:ring focus:ring-blue-300">
            </div>

            <button type="submit" 
                    class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition-colors">
                Đăng ký
            </button>
        </form>

        <p class="mt-4 text-center text-gray-600">
            Đã có tài khoản? 
            <a href="<%= request.getContextPath() %>/login" class="text-blue-500 hover:underline">Đăng nhập</a>
        </p>
    </div>
</body>
</html>
