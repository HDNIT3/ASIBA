package vn.iostar.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.Dao.BookAuthorDAO;
import vn.iostar.connection.DBConnection;
import vn.iostar.entity.BookAuthor;

public class BookAuthorDAOImpl implements BookAuthorDAO {

	@Override
	public void insert(BookAuthor ba) {
		String sql = "INSERT INTO book_author(bookid, author_id) VALUES (?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, ba.getBookid());
			ps.setInt(2, ba.getAuthorId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int bookid, int authorId) {
		String sql = "DELETE FROM book_author WHERE bookid=? AND author_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, bookid);
			ps.setInt(2, authorId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BookAuthor> findByBookId(int bookid) {
		List<BookAuthor> list = new ArrayList<>();
		String sql = "SELECT * FROM book_author WHERE bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, bookid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BookAuthor ba = new BookAuthor();
				ba.setBookid(rs.getInt("bookid"));
				ba.setAuthorId(rs.getInt("author_id"));
				list.add(ba);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BookAuthor> findByAuthorId(int authorId) {
		List<BookAuthor> list = new ArrayList<>();
		String sql = "SELECT * FROM book_author WHERE author_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, authorId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BookAuthor ba = new BookAuthor();
				ba.setBookid(rs.getInt("bookid"));
				ba.setAuthorId(rs.getInt("author_id"));
				list.add(ba);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}