package vn.iostar.Dao;

import vn.iostar.entity.Author;
import java.util.List;

public interface AuthorDAO {
    void insert(Author author);
    void update(Author author);
    void delete(int id);
    Author findById(int id);
    List<Author> findAll();
}