package com.fractal.courses.specification;

import com.fractal.courses.model.BookFieldType;

public interface BookSpecificationFactory<T> {
    Specification<T> create(BookFieldType field);
}
