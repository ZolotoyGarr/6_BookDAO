import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookDAO implements DAO {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final List<Book> bookList = new ArrayList<>();

    {
        bookList.add(new Book("Ray Bradbury", "Wine dandelion", 1957));
        bookList.add(new Book("Ray Bradbury", "451 fahrenheit", 1953));
    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public void showBookList() {
        for (Book book : bookList) {
            LOGGER.info(book);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookDAO{");
        sb.append("bookList=[");
        for (Book book : bookList) {
            sb.append(book.toString()).append(", ");
        }
        if (!bookList.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public void add(Book book) throws DaoException {
        if (book == null || book.getAuthor() == null || book.getTitle() == null || book.getCreationYear() == 0) {
            throw new DaoException("Object received with incomplete information");
        }
        bookList.add(book);
    }

    @Override
    public void add(List<Book> books) throws DaoException {
        for (Book book : books) {
            this.add(book);
        }
    }

    @Override
    public void remove(Book book) throws DaoException {
        if (book == null || book.getAuthor() == null || book.getTitle() == null || book.getCreationYear() == 0) {
            throw new DaoException("Object received with incomplete information");
        }
        bookList.remove(book);
    }

    @Override
    public <T> List<Book> findByTag(BookFieldType fieldType, T value) throws UnknownFieldException, DaoException {
        if (fieldType == null) {
            throw new UnknownFieldException("Unknown field type received");
        }
        SpecificationFactory specificationFactory = new SpecificationFactory();
        Specification currentSpec = specificationFactory.create(fieldType);//Raw use of parameterized class 'Specification'
        List<Book> findResultBooks = currentSpec.find(bookList, value);//Unchecked call to 'find(List<Book>, T)' as a member of raw type 'Specification'
        if (findResultBooks.isEmpty()) {
            LOGGER.info("There is no books with this tag: " + value);
        }
        return findResultBooks;
    }

    @Override
    public void sortByTag(BookFieldType fieldType) throws UnknownFieldException {
        SpecificationFactory specificationFactory = new SpecificationFactory();
        Specification currentSpec = specificationFactory.create(fieldType);//Raw use of parameterized class 'Specification'
        currentSpec.sort(bookList);//Unchecked call to 'sort(List<Book>)' as a member of raw type 'Specification'
    }
}
