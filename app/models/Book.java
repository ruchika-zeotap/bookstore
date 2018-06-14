package models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

/**
 * Created by ruchika.gupta on 10/06/18.
 */

@Entity
@Table(name = "books")
public class Book extends Model {

    public Book() {
    }

    @Id
    @NotNull
    @Column(name = "book_id")
    private Integer Id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "version")
    private String version;


    public static final Finder<Long, Book> find = new Finder<Long, Book>(Book.class);

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() { return version; }

    public void setVersion(String version) {
        this.version = version;
    }

    public static Finder<Long, Book> getFind() {
        return find;
    }

}
