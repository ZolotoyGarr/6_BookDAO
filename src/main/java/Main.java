import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        bookDAO.add(new Book("Bruce Eckel", "Thinking in Java", 1998));
        bookDAO.add(new Book("Bruce Eckel", "Thinking in C++", 1995));
        bookDAO.showMenu();
    }
}