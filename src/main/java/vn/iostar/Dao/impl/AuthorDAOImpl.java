package vn.iostar.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.Dao.AuthorDAO;
import vn.iostar.connection.DBConnection;
import vn.iostar.entity.Author;

public class AuthorDAOImpl implements AuthorDAO {
	@Override
	public void insert(Author author) {
		String sql = "INSERT INTO author(author_name, date_of_birth) VALUES (?,?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, author.getAuthorName());
			ps.setDate(2, new java.sql.Date(author.getDateOfBirth().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Author findById(int id) {
		String sql = "SELECT * FROM author WHERE author_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("author_id"));
				a.setAuthorName(rs.getString("author_name"));
				a.setDateOfBirth(rs.getDate("date_of_birth"));
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Author> findAll() {
		List<Author> list = new ArrayList<>();
		String sql = "SELECT * FROM author";
		try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement()) {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("author_id"));
				a.setAuthorName(rs.getString("author_name"));
				a.setDateOfBirth(rs.getDate("date_of_birth"));
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void update(Author author) {
		String sql = "UPDATE author SET author_name=?, date_of_birth=? WHERE author_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, author.getAuthorName());
			ps.setDate(2, new java.sql.Date(author.getDateOfBirth().getTime()));
			ps.setInt(3, author.getAuthorId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM author WHERE author_id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}