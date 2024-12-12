package com.fractal.courses;

import com.fractal.courses.DAO.impl.BookDAO;
import com.fractal.courses.DAO.impl.BookDAOInMemory;
import com.fractal.courses.model.Book;
import com.fractal.courses.model.UserInteraction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAOInMemory();
        UserInteraction userInteraction = new UserInteraction();

        bookDAO.add(new Book("Bruce Eckel", "Thinking in Java", 1998));
        bookDAO.add(new Book("Bruce Eckel", "Thinking in C++", 1995));
        userInteraction.showMenu();
    }
}