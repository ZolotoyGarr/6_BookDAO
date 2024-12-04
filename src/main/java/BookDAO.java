import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookDAO implements DAO {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final Scanner scanner = new Scanner(System.in);
    private List<Book> bookList = new ArrayList<>();

    {
        bookList.add(new Book("Ray Bradbury", "451 fahrenheit", 1953));
        bookList.add(new Book("Ray Bradbury", "Wine dandelion", 1957));
    }

    public List<Book> getBookList() {
        return bookList;
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
        Specification<T> currentSpec = specificationFactory.create(fieldType);
        List<Book> findResultBooks = currentSpec.find(bookList, value);
        if (findResultBooks.isEmpty()) {
            LOGGER.info("There is no books with this tag: " + value);
        }
        return findResultBooks;
    }

    @Override
    public void sortByTag(BookFieldType fieldType) throws UnknownFieldException {
        ComparatorFactory comparatorFactory = new ComparatorFactory();
        Comparator<Book> comparator = comparatorFactory.create(fieldType);
        bookList.sort(comparator);
    }

    public void addByUser() {
        LOGGER.info("Enter book title");
        String title = scanner.nextLine();
        LOGGER.info("Enter author");
        String author = scanner.nextLine();
        LOGGER.info("Enter creation year");
        int creationYear = scanner.nextInt();
        scanner.nextLine(); // Добавлено для считывания символа новой строки
        add(new Book(title, author, creationYear));
        LOGGER.info("Book " + title + " was successfully added");
    }

    public void removeByUser() {
        LOGGER.info("Enter book title");
        String title = scanner.nextLine();
        List<Book> findResult = findByTag(BookFieldType.TITLE, title);
        if (findResult.isEmpty()) {
            LOGGER.info("There is no books with such title, try another one");
            removeByUser(); // выходим из цикла вручную
        } else {
            for (Book book : findResult) {
                remove(book);
                LOGGER.info(book + " was successfully removed");
            }
        }
    }

    public void findByUser() {
        LOGGER.info("Enter finding tag (1. Title; 2. Author; 3. Creation year)");
        BookFieldType tag;
        List<Book> findResultBooks;
        int choice = scanner.nextInt();
        scanner.nextLine(); // Добавлено для считывания символа новой строки
        if (choice <= 0 || choice > 3) {
            LOGGER.info("Incorrect value, choose option 1 - 3");
            findByUser();
        } else {
            switch (choice) {
                case 1 -> {
                    tag = BookFieldType.TITLE;
                    LOGGER.info("Enter title: ");
                    String title = scanner.nextLine();
                    findResultBooks = findByTag(tag, title);
                    if (!findResultBooks.isEmpty()) {
                        LOGGER.info("Find result books: " + findResultBooks);
                    }
                }
                case 2 -> {
                    tag = BookFieldType.AUTHOR;
                    LOGGER.info("Enter author: ");
                    String author = scanner.nextLine();
                    findResultBooks = findByTag(tag, author);
                    if (!findResultBooks.isEmpty()) {
                        LOGGER.info("Find result books: " + findResultBooks);
                    }
                }
                case 3 -> {
                    tag = BookFieldType.CREATION_YEAR;
                    LOGGER.info("Enter creation year: ");
                    int creationYear = scanner.nextInt();
                    findResultBooks = findByTag(tag, creationYear);
                    if (!findResultBooks.isEmpty()) {
                        LOGGER.info("Find result books: " + findResultBooks);
                    }
                }
            }
        }
    }

    public void sortByUser() {
        LOGGER.info("Enter sorting tag (1. Title; 2. Author; 3. Creation year)");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Добавлено для считывания символа новой строки
        if (choice <= 0 || choice > 3) {
            LOGGER.info("Incorrect value, choose option 1 - 3");
            findByUser();
        } else {
            switch (choice) {
                case 1 -> {
                    sortByTag(BookFieldType.TITLE);
                    LOGGER.info(getBookList());
                }
                case 2 -> {
                    sortByTag(BookFieldType.AUTHOR);
                    LOGGER.info(getBookList());
                }
                case 3 -> {
                    sortByTag(BookFieldType.CREATION_YEAR);
                    LOGGER.info(getBookList());
                }
            }
        }
    }

    public void showMenu() {
        LOGGER.info("Choose one option: ");
        LOGGER.info("1. Add new book");
        LOGGER.info("2. Delete new book");
        LOGGER.info("3. Find books by tag");
        LOGGER.info("4. Sort books by tag");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Добавлено для считывания символа новой строки
        if (choice <= 0 || choice > 4) {
            LOGGER.info("Incorrect value, choose option 1 - 4");
            showMenu();
        } else {
            switch (choice) {
                case 1 -> {
                    addByUser();
                    showMenu();
                }
                case 2 -> {
                    removeByUser();
                    showMenu();
                }
                case 3 -> {
                    findByUser();
                    showMenu();
                }
                case 4 -> {
                    sortByUser();
                    showMenu();
                }
            }
        }
    }
}
