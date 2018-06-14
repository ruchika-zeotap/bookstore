package models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.Index;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "book_author_mapping")
public class BookAuthorMapping extends Model {

    public BookAuthorMapping() {
    }

    @Id
    @NotNull
    @Column(name = "mapping_id")
    private Integer mapping_id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    private Book book_id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id", nullable = false)
    private Author author_id;

    public static final Finder<Long, BookAuthorMapping> find = new Finder<Long, BookAuthorMapping>(BookAuthorMapping.class);

    public Integer getId() {
        return mapping_id;
    }

    public void setId(Integer id) {
        this.mapping_id = id;
    }

    public Book getBook() {
        return book_id;
    }

    public void setBook(Book book) {
        this.book_id = book;
    }

    public Author getAuthor() {
        return author_id;
    }

    public void setAuthor(Author author) {
        this.author_id = author;
    }

}
