import java.util.List;

public interface Specification<T> {
    List<Book> find(List<Book> books, T value) throws DaoException; //Общий интерфейс стратегий
    void sort(List<Book> books) throws DaoException;//Return value of the method is never used
}
