import java.util.List;

public interface BookDAO {
    void add(Book book) throws DaoException;
    void add(List<Book> books) throws DaoException;
    void remove(Book book) throws DaoException;
    <T> List<Book> findByTag(BookFieldType fieldType, T name) throws UnknownFieldException, DaoException;
    void sortByTag(BookFieldType fieldType) throws UnknownFieldException;
    List<Book> getBookList();
    void showBookList();
}
