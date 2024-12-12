package com.fractal.courses.specification.impl;

import com.fractal.courses.model.Book;
import com.fractal.courses.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractSpecification<T> implements Specification<T> {
    private static final String UNKNOWN_FIELD_TYPE = "Unknown book field type.";
    //Шаблонный метод
    public List<Book> find(List<Book> books, T value) {
        List<Book> resultBooks = new ArrayList<>();
        for (Book book : books) {
            if (match(book, value)) {
                resultBooks.add(book);
            }
        }
        return resultBooks;
    }
    //У каждого наследника своя реализация
    public abstract boolean match(Book book, T value);


    public void sort(List<Book> books) {
        books.sort(getComparator());
    }
    public abstract Comparator<Book> getComparator();
}
