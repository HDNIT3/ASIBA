package vn.iostar.Dao.impl;

import java.sql.*;
import java.util.*;

import vn.iostar.Dao.UserDAO;
import vn.iostar.connection.DBConnection;
import vn.iostar.entity.User;

public class UserDAOImpl implements UserDAO {

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users (email, fullname, phone, passwd, signup_date, last_login, is_admin) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFullname());
            ps.setInt(3, user.getPhone());
            ps.setString(4, user.getPasswd());
            ps.setTimestamp(5, new Timestamp(user.getSignupDate().getTime()));
            ps.setTimestamp(6, user.getLastLogin() != null ? new Timestamp(user.getLastLogin().getTime()) : null);
            ps.setBoolean(7, user.isAdmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET email=?, fullname=?, phone=?, passwd=?, last_login=?, is_admin=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFullname());
            ps.setInt(3, user.getPhone());
            ps.setString(4, user.getPasswd());
            ps.setTimestamp(5, user.getLastLogin() != null ? new Timestamp(user.getLastLogin().getTime()) : null);
            ps.setBoolean(6, user.isAdmin());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapUser(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User login(String fullname, String passwd) {
        String sql = "SELECT * FROM users WHERE fullname=? AND passwd=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullname);
            ps.setString(2, passwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setFullname(rs.getString("fullname"));
        u.setPhone(rs.getInt("phone"));
        u.setPasswd(rs.getString("passwd"));
        u.setSignupDate(rs.getTimestamp("signup_date"));
        u.setLastLogin(rs.getTimestamp("last_login"));
        u.setAdmin(rs.getBoolean("is_admin"));
        return u;
    }

	@Override
	public boolean exists(String fullname) {
		String sql = "SELECT COUNT(*) FROM users WHERE fullname = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, fullname);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}