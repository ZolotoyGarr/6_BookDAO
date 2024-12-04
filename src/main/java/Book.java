public class Book {
    private final String author;
    private final String title;
    private final int creationYear;

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", creationYear=" + creationYear +
                '}';
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

    public Book(String author, String title, int creationYear) {
        this.author = author;
        this.title = title;
        this.creationYear = creationYear;
    }
}
