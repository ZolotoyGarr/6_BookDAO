import java.util.List;

public class CreationYearSpecification extends AbstractSpecification<Integer>  {
    BookFieldType bookFieldType = BookFieldType.CREATION_YEAR;
    @Override
    public boolean match(Book book, Integer value) {
        return (book.getCreationYear() == value);
    }

    @Override
    public void sort(List<Book> books) {
        setComparator(bookFieldType);
        books.sort(getComparator());
    }
}