public class TitleSpecification extends AbstractSpecification<String> {

    @Override
    public boolean match(Book book, String title) {
        return book.getTitle().equalsIgnoreCase(title);
    }
}