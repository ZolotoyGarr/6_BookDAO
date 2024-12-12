import java.util.Comparator;

public class ComparatorFactory {
    private static final String UNKNOWN_FIELD_TYPE = "Unknown book field type.";

    public Comparator<Book> create(BookFieldType field) throws UnknownFieldException {
        return switch (field) {
            case TITLE -> Comparator.comparing(Book::getTitle);
            case AUTHOR -> Comparator.comparing(Book::getAuthor);
            case CREATION_YEAR -> Comparator.comparingInt(Book::getCreationYear);
            default -> throw new UnknownFieldException(UNKNOWN_FIELD_TYPE); //'default' branch is unnecessary
        };
    }
}