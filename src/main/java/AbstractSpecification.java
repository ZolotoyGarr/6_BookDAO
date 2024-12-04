import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpecification<T> implements Specification<T> {
    public List<Book> find(List<Book> books, T value) {
        List<Book> resultBooks = new ArrayList<>();
        for (Book book : books) {
            if (match(book, value)) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }

    public abstract boolean match(Book book, T value);
}
