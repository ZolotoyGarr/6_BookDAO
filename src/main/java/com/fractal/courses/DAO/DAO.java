package com.fractal.courses.DAO;

import com.fractal.courses.model.Book;
import com.fractal.courses.model.BookFieldType;
import com.fractal.courses.exceptions.DaoException;
import com.fractal.courses.exceptions.UnknownFieldException;

import java.util.List;

public interface DAO {
    void add(Book book) throws DaoException;
    void add(List<Book> books) throws DaoException;
    void remove(Book book) throws DaoException;
    <T> List<Book> findByTag(BookFieldType fieldType, T name) throws UnknownFieldException, DaoException;
    void sortByTag(BookFieldType fieldType) throws UnknownFieldException;
    List<Book> getBookList();
    void showBookList();
}
