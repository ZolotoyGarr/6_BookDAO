package com.fractal.courses.specification.impl;

import com.fractal.courses.model.BookFieldType;
import com.fractal.courses.exceptions.UnknownFieldException;
import com.fractal.courses.specification.Factory;
import com.fractal.courses.specification.Specification;

public class SpecificationFactory implements Factory {
    private static final String UNKNOWN_FIELD_TYPE = "Unknown book field type.";

    public Specification create(BookFieldType field) throws UnknownFieldException { //Фабричный метод
        switch (field) {
            case CREATION_YEAR -> {
                return new CreationYearSpecification();
            }
            case AUTHOR -> {
                return new AuthorSpecification();
            }
            case TITLE -> {
                return new TitleSpecification();
            }
            default -> throw new UnknownFieldException(UNKNOWN_FIELD_TYPE);
        }
    }
}