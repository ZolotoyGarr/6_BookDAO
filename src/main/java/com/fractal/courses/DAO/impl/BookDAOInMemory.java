package com.fractal.courses.DAO.impl;

import com.fractal.courses.*;
import com.fractal.courses.exceptions.DaoException;
import com.fractal.courses.exceptions.UnknownFieldException;
import com.fractal.courses.model.Book;
import com.fractal.courses.model.BookFieldType;
import com.fractal.courses.specification.Specification;
import com.fractal.courses.specification.impl.SpecificationFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookDAOInMemory implements BookDAO {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final List<Book> bookList = new ArrayList<>();
    private final SpecificationFactory specificationFactory = new SpecificationFactory();

    {
        bookList.add(new Book("Ray Bradbury", "Wine dandelion", 1957));
        bookList.add(new Book("Ray Bradbury", "451 fahrenheit", 1953));
    }

    @Override
    public <T> List<Book> findByTag(BookFieldType fieldType, T value) throws UnknownFieldException, DaoException {
        if (fieldType == null) {
            throw new UnknownFieldException("Unknown field type received");
        }
        Specification<T> currentSpec = specificationFactory.create(fieldType);//Unchecked assignment: 'com.fractal.courses.specification.Specification' to 'com.fractal.courses.specification.Specification<T>'
        List<Book> findResultBooks = currentSpec.find(bookList, value);
        if (findResultBooks.isEmpty()) {
            LOGGER.info("There is no books with this tag: " + value);
        }
        return findResultBooks;
    }

    @Override
    public void sortByTag(BookFieldType fieldType) throws UnknownFieldException {
        if (fieldType == null) {
            throw new UnknownFieldException("Unknown field type received");
        }
        Specification<?> currentSpec = specificationFactory.create(fieldType);
        currentSpec.sort(bookList);
    }

    @Override
    public void add(Book book) throws DaoException {
        bookList.add(book);
    }

    @Override
    public void add(List<Book> books) throws DaoException {
        for (Book book : books) {
            this.add(book);
        }
    }

    @Override
    public void showBookList() {
        for (Book book : bookList) {
            LOGGER.info(book);
        }
    }


    @Override
    public void remove(Book book) throws DaoException {
        bookList.remove(book);
    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.fractal.courses.DAO.impl.BookDAO{");
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

}