public class AuthorSpecification extends AbstractSpecification<String> {
    @Override
    public boolean match(Book book, String author) {
        return book.getAuthor().equalsIgnoreCase(author);
    }
}