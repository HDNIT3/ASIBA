package vn.iostar.controller;

import vn.iostar.Dao.BookDAO;
import vn.iostar.Dao.impl.BookDAOImpl;
import vn.iostar.entity.Book;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ListBook", "/addBook", "/bookDetail"}) // Thêm /bookDetail vào urlPatterns
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if ("/ListBook".equals(action)) {
            List<Book> bookList = bookDAO.findAll();
            for (Book book : bookList) {
                String authorNames = bookDAO.getAuthorNamesByBookId(book.getBookid());
                book.setDescription(authorNames);
            }
            request.setAttribute("bookList", bookList);
            request.getRequestDispatcher("/views/book/listBooks.jsp").forward(request, response);
        } else if ("/addBook".equals(action)) {
            request.getRequestDispatcher("/views/book/addBook.jsp").forward(request, response);
        } else if ("/bookDetail".equals(action)) {
            String bookIdParam = request.getParameter("bookid");
            if (bookIdParam != null) {
                int bookId = Integer.parseInt(bookIdParam);
                Book book = bookDAO.findById(bookId); // Giả sử có phương thức findById
                if (book != null) {
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("/views/book/bookDetail.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/ListBook");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/ListBook");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        int isbn = Integer.parseInt(request.getParameter("isbn"));
        String publisher = request.getParameter("publisher");
        Date publishDate = new Date();
        String coverImage = "[cover_image]";
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setPublishDate(publishDate);
        book.setCoverImage(coverImage);
        book.setQuantity(quantity);
        book.setDescription(description);
        book.setPrice(price);

        bookDAO.insert(book);
        
        response.sendRedirect(request.getContextPath() + "/ListBook");
    }
}