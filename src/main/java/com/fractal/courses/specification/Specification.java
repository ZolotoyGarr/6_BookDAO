package com.fractal.courses.specification;

import com.fractal.courses.model.Book;
import com.fractal.courses.exceptions.DaoException;

import java.util.List;

public interface Specification<T> {
    List<Book> find(List<Book> books, T value) throws DaoException; //Общий интерфейс стратегий
    void sort(List<Book> books) throws DaoException;//Return value of the method is never used
}
