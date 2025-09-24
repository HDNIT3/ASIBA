package vn.iostar.Dao;

import vn.iostar.entity.Rating;
import java.util.List;

public interface RatingDAO {
    void insert(Rating rating);
    void update(Rating rating);
    void delete(int userId, int bookId);
    Rating find(int userId, int bookId);
    List<Rating> findByBookId(int bookId);
    List<Rating> findByUserId(int userId);
}
