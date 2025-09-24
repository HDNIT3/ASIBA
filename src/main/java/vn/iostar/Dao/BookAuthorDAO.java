package vn.iostar.Dao;

import vn.iostar.entity.BookAuthor;
import java.util.List;

public interface BookAuthorDAO {
    void insert(BookAuthor ba);
    void delete(int bookid, int authorId);
    List<BookAuthor> findByBookId(int bookid);
    List<BookAuthor> findByAuthorId(int authorId);
}