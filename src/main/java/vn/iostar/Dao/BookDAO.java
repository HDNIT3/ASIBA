package vn.iostar.Dao;

import vn.iostar.entity.Book;
import java.util.List;

public interface BookDAO {
    void insert(Book book);
    void update(Book book);
    void delete(int id);
    Book findById(int id);
    List<Book> findAll();
    String getAuthorNamesByBookId(int bookId);
    void addStringDecriptop(int id,String name,String command);
}