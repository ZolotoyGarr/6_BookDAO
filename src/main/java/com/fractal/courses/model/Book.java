package com.fractal.courses.model;

public class Book {
    private final String author;
    private final String title;
    private final int creationYear;

    public Book(String author, String title, int creationYear) {
        if (author == null || title == null || creationYear == 0) {
            throw new IllegalArgumentException("Illegal argument received");
        }
        this.author = author;
        this.title = title;
        this.creationYear = creationYear;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getCreationYear() {
        return creationYear;
    }

    @Override
    public String toString() {
        return "com.fractal.courses.model.Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", creationYear=" + creationYear +
                '}';
    }
}
