package vn.iostar.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.Dao.BookDAO;
import vn.iostar.connection.DBConnection;
import vn.iostar.entity.Book;

public class BookDAOImpl implements BookDAO {
	private Book mapBook(ResultSet rs) throws SQLException {
		Book b = new Book();
		b.setBookid(rs.getInt("bookid"));
		b.setIsbn(rs.getInt("isbn"));
		b.setTitle(rs.getString("title"));
		b.setPublisher(rs.getString("publisher"));
		b.setPrice(rs.getDouble("price"));
		b.setDescription(rs.getString("description"));
		b.setPublishDate(rs.getDate("publish_date"));
		b.setCoverImage(rs.getString("cover_image"));
		b.setQuantity(rs.getInt("quantity"));
		return b;
	}

	@Override
	public void insert(Book book) {
		String sql = "INSERT INTO books (isbn, title, publisher, price, description, publish_date, cover_image, quantity) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getPublisher());
			ps.setDouble(4, book.getPrice());
			ps.setString(5, book.getDescription());
			ps.setDate(6, new java.sql.Date(book.getPublishDate().getTime()));
			ps.setString(7, book.getCoverImage());
			ps.setInt(8, book.getQuantity());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Book book) {
		String sql = "UPDATE books SET isbn=?, title=?, publisher=?, price=?, description=?, publish_date=?, cover_image=?, quantity=? WHERE bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, book.getIsbn());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getPublisher());
			ps.setDouble(4, book.getPrice());
			ps.setString(5, book.getDescription());
			ps.setDate(6, new java.sql.Date(book.getPublishDate().getTime()));
			ps.setString(7, book.getCoverImage());
			ps.setInt(8, book.getQuantity());
			ps.setInt(9, book.getBookid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM books WHERE bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book findById(int id) {
		String sql = "SELECT * FROM books WHERE bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return mapBook(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> findAll() {
		List<Book> list = new ArrayList<>();
		String sql = "SELECT * FROM books";
		try (Connection conn = DBConnection.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next())
				list.add(mapBook(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getAuthorNamesByBookId(int bookId) {
		String sql = "SELECT STRING_AGG(a.author_name, ', ') AS author_name " + "FROM books b "
				+ "LEFT JOIN book_author ba ON b.bookid = ba.bookid "
				+ "LEFT JOIN author a ON ba.author_id = a.author_id " + "WHERE b.bookid = ? " + "GROUP BY b.bookid";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, bookId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String authorNames = rs.getString("author_name");
					return authorNames != null ? authorNames : "";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}