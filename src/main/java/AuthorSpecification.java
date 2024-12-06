import java.util.List;

public class AuthorSpecification extends AbstractSpecification<String> {
    BookFieldType bookFieldType = BookFieldType.AUTHOR;
    @Override
    public boolean match(Book book, String author) {
        return book.getAuthor().equalsIgnoreCase(author);
    }

    @Override
    public void sort(List<Book> books) {
        setComparator(bookFieldType);
        books.sort(getComparator());
    }
}