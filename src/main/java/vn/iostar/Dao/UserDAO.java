package vn.iostar.Dao;

import vn.iostar.entity.User;
import java.util.List;

public interface UserDAO {
    void insert(User user);
    void update(User user);
    void delete(int id);
    User findById(int id);
    List<User> findAll();
    User login(String fullname, String passwd);
    boolean exists(String fullname);
}
