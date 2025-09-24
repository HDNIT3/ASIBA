package vn.iostar.Dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.Dao.RatingDAO;
import vn.iostar.connection.DBConnection;
import vn.iostar.entity.Rating;

public class RatingDAOImpl implements RatingDAO {
	@Override
	public void insert(Rating rating) {
		String sql = "INSERT INTO rating(userid, bookid, rating, review_text) VALUES (?,?,?,?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, rating.getUserId());
			ps.setInt(2, rating.getBookId());
			ps.setInt(3, rating.getRating());
			ps.setString(4, rating.getReviewText());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Rating rating) {
		String sql = "UPDATE rating SET rating=?, review_text=? WHERE userid=? AND bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, rating.getRating());
			ps.setString(2, rating.getReviewText());
			ps.setInt(3, rating.getUserId());
			ps.setInt(4, rating.getBookId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int userId, int bookId) {
		String sql = "DELETE FROM rating WHERE userid=? AND bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, bookId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Rating find(int userId, int bookId) {
		String sql = "SELECT * FROM rating WHERE userid=? AND bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, bookId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Rating(rs.getInt("userid"), rs.getInt("bookid"), rs.getInt("rating"),
						rs.getString("review_text"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Rating> findByBookId(int bookId) {
		List<Rating> list = new ArrayList<>();
		String sql = "SELECT * FROM rating WHERE bookid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Rating(rs.getInt("userid"), rs.getInt("bookid"), rs.getInt("rating"),
						rs.getString("review_text")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Rating> findByUserId(int userId) {
		List<Rating> list = new ArrayList<>();
		String sql = "SELECT * FROM rating WHERE userid=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Rating(rs.getInt("userid"), rs.getInt("bookid"), rs.getInt("rating"),
						rs.getString("review_text")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}