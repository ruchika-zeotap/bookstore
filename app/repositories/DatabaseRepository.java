package repositories;

import io.ebean.Ebean;
import io.ebean.Model;
import models.*;
import java.util.*;

public class DatabaseRepository implements Repository {

    public List<Book> getBooksList() {
        List<Book> list = Ebean.find(Book.class).findList();
        return list;
    }

    public List<Author> getAuthorList() {
        List<Author> list = Ebean.find(Author.class).findList();
        return list;
    }

    public List<BookAuthorMapping> getBookAuthorMappingList() {
        List<BookAuthorMapping> list = Ebean.find(BookAuthorMapping.class).findList();
        return list;
    }

    public <E extends Model> E getObject(Class<E> model, Integer id) {
        if (id != null && model != null) {
            return Ebean.find(model, id);
        }
        return null;
    }

    @Override
    public Object fetchCompleteDataStore() {
        return null;
    }

    @Override
    public Object fetchPaginatedDataStore(Object query, String token) {
        return null;
    }
}
