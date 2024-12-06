import java.util.List;

public class TitleSpecification extends AbstractSpecification<String> {
    BookFieldType bookFieldType = BookFieldType.TITLE;

    @Override
    public boolean match(Book book, String title) {
        return book.getTitle().equalsIgnoreCase(title);
    }

    @Override
    public void sort(List<Book> books) {
        setComparator(bookFieldType);
        books.sort(getComparator());
    }
}