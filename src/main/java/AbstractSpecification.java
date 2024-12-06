import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractSpecification<T> implements Specification<T> {
    private static final String UNKNOWN_FIELD_TYPE = "Unknown book field type.";
    private Comparator<Book> comparator;

    public Comparator<Book> getComparator() {
        return comparator;
    }

    public List<Book> find(List<Book> books, T value) { //Шаблонный метод
        List<Book> resultBooks = new ArrayList<>();
        for (Book book : books) {
            if (match(book, value)) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }

    public abstract boolean match(Book book, T value);//У каждого наследника своя реализация

    public abstract void sort(List<Book> books);

    public void setComparator(BookFieldType field) {
        switch (field) {
            case TITLE -> comparator = Comparator.comparing(Book::getTitle);
            case AUTHOR -> comparator = Comparator.comparing(Book::getAuthor);
            case CREATION_YEAR -> comparator = Comparator.comparing(Book::getCreationYear);
            default -> throw new UnknownFieldException(UNKNOWN_FIELD_TYPE);
        }
    }
}
