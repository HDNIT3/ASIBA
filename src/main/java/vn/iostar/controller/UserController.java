package vn.iostar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.Dao.UserDAO;
import vn.iostar.Dao.impl.UserDAOImpl;
import vn.iostar.entity.User;

@WebServlet(urlPatterns = {"/login","/home","/logout","/register"})
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userdao = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("login")) {
            req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
        }
        else if (url.contains("register")) {
            req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
        }
        else if (url.contains("home")) {
            HttpSession session = req.getSession(false); // không tạo mới
            if (session != null && session.getAttribute("user") != null) {
                req.setAttribute("user", session.getAttribute("user"));
                req.setAttribute("isAdmin", session.getAttribute("isAdmin"));
                req.getRequestDispatcher("/views/user/Home.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
        else if (url.contains("logout")) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("login")) {
            String fullname = req.getParameter("fullname");
            String password = req.getParameter("password");

            User user = userdao.login(fullname, password);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("isAdmin", user.isAdmin()); // boolean

                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                req.setAttribute("error", "Sai thông tin đăng nhập!");
                req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
            }
        }
        else if (url.contains("register")) {
            String fullname = req.getParameter("fullname");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String confirmPassword = req.getParameter("confirmPassword");

            if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Mật khẩu xác nhận không khớp!");
                req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
                return;
            }

            if (userdao.exists(fullname)) { 
                req.setAttribute("error", "Người dùng đã tồn tại!");
                req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
                return;
            }

            User user = new User();
            user.setFullname(fullname);
            user.setPasswd(confirmPassword);
            user.setAdmin(false);
            user.setEmail(email);
            user.setSignupDate(new java.util.Date());
            user.setPhone(0); 
            user.setLastLogin(null);
            
            try {
            	userdao.insert(user);
            	req.setAttribute("message", "Đăng ký thành công! Hãy đăng nhập.");
                req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
			} catch (Exception e) {
				req.setAttribute("error", "Đăng ký thất bại! Vui lòng thử lại.");
                req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
			}
        }
    }
}
