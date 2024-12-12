package com.fractal.courses.specification;

import com.fractal.courses.model.BookFieldType;

public interface Factory {
    <T> T create(BookFieldType field);
}
