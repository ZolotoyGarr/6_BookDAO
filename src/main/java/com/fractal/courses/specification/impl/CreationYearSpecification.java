package com.fractal.courses.specification.impl;

import com.fractal.courses.model.Book;

import java.util.Comparator;

public class CreationYearSpecification extends AbstractSpecification<Integer> {

    @Override
    public boolean match(Book book, Integer value) {
        return (book.getCreationYear() == value);
    }

    @Override
    public Comparator<Book> getComparator() {
        return Comparator.comparing(Book::getCreationYear);
    }
}