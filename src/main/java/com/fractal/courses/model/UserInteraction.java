package com.fractal.courses.model;

import com.fractal.courses.DAO.impl.BookDAO;
import com.fractal.courses.DAO.impl.BookDAOInMemory;
import com.fractal.courses.Main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final Scanner scanner = new Scanner(System.in);
    private final BookDAO bookDAO = new BookDAOInMemory();

    public void addByUser() {
        String title = inputString("Enter book title");
        String author = inputString("Enter author");
        int creationYear = inputInt("Enter creation year");

        bookDAO.add(new Book(title, author, creationYear));
        LOGGER.info("com.fractal.courses.model.Book \"" + title + "\" was successfully added");
    }

    public void removeByUser() {
        String title = inputString("Enter book title");
        List<Book> findResult = bookDAO.findByTag(BookFieldType.TITLE, title);
        if (findResult.isEmpty()) {
            LOGGER.info("There is no books with such title, try another one");
        } else {
            for (Book book : findResult) {
                bookDAO.remove(book);
                LOGGER.info(book + " was successfully removed");
            }
        }
    }

    public void findByUser() {
        LOGGER.info("Enter finding tag (1. Title; 2. Author; 3. Creation year)");
        int choice = inputInt("Choose option 1 - 3");
        List<Book> findResultBooks = null;

        switch (choice) {
            case 1 -> {
                String title = inputString("Enter title: ");
                findResultBooks = bookDAO.findByTag(BookFieldType.TITLE, title);
            }
            case 2 -> {
                String author = inputString("Enter author: ");
                findResultBooks = bookDAO.findByTag(BookFieldType.AUTHOR, author);
            }
            case 3 -> {
                int creationYear = inputInt("Enter creation year: ");
                findResultBooks = bookDAO.findByTag(BookFieldType.CREATION_YEAR, creationYear);
            }
            default -> {
                LOGGER.info("Incorrect value, choose option 1 - 3");
                findByUser();
            }
        }
        if (findResultBooks != null && !findResultBooks.isEmpty()) {
            LOGGER.info("Find result books: " + findResultBooks);
        }
    }

    public void sortByUser() {
        LOGGER.info("Enter sorting tag (1. Title; 2. Author; 3. Creation year)");
        int choice = inputInt("Choose option 1 - 3");

        switch (choice) {
            case 1 -> bookDAO.sortByTag(BookFieldType.TITLE);
            case 2 -> bookDAO.sortByTag(BookFieldType.AUTHOR);
            case 3 -> bookDAO.sortByTag(BookFieldType.CREATION_YEAR);
            default -> {
                LOGGER.info("Incorrect value, choose option 1 - 3");
                sortByUser();
                return;
            }
        }
        LOGGER.info(bookDAO.getBookList());
    }

    public void showBooks() {
        LOGGER.info("Available books: ");
        bookDAO.showBookList();
    }

    public void showMenu() {
        LOGGER.info("Choose one option: ");
        LOGGER.info("1. Add new book");
        LOGGER.info("2. Delete new book");
        LOGGER.info("3. Find books by tag");
        LOGGER.info("4. Sort books by tag");
        LOGGER.info("5. Show available books");
        int choice = inputInt("Choose option 1 - 5");

        switch (choice) {
            case 1 -> addByUser();
            case 2 -> removeByUser();
            case 3 -> findByUser();
            case 4 -> sortByUser();
            case 5 -> showBooks();
            default -> {
                LOGGER.info("Incorrect value, choose option 1 - 4");
                showMenu();
            }
        }
        showMenu(); // Продолжаем показывать меню после выполнения команды
    }

    private String inputString(String message) {
        LOGGER.info(message);
        return scanner.nextLine();
    }

    private int inputInt(String message) {
        LOGGER.info(message);
        while (!scanner.hasNextInt()) {
            LOGGER.info("Invalid input. " + message);
            scanner.next(); // Считывание некорректного ввода
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Считывание символа новой строки
        return input;
    }
}
