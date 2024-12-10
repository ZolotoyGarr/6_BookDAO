import java.util.Comparator;

public class AuthorSpecification extends AbstractSpecification<String> {

    @Override
    public Comparator<Book> getComparator() {
        return Comparator.comparing(Book::getAuthor);
    }

    @Override
    public boolean match(Book book, String author) {
        return book.getAuthor().equalsIgnoreCase(author);
    }

}