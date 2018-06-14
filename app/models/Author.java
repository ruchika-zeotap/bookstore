package models;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ruchika.gupta on 10/06/18.
 */

@Entity
@Table(name = "authors")
public class Author extends Model {

    public Author() {
    }

    @Id
    @NotNull
    @Column(name = "author_id")
    private Integer Id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "gender")
    private String gender;


    @NotNull
    @Column(name = "age")
    private Integer age;


    public static final Finder<Long, Author> find = new Finder<Long, Author>(Author.class);

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() { return age; }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static Finder<Long, Author> getFind() {
        return find;
    }

}
