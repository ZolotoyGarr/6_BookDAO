import java.util.ArrayList;
import java.util.List;

public class CreationYearSpecification extends AbstractSpecification<Integer>  {

    @Override
    public boolean match(Book book, Integer value) {
        return (book.getCreationYear() == value);
    }
}