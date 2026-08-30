package ru.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(unique = true)
    private String isbn;

    private Integer publishedYear;
    private Integer quantity;

    @Column(name = "authorid")
    private long authorID;


    public Book(String isbn, String title, Author author, Integer publishedYear, Integer quantity) {
        this.isbn = isbn;
        this.title = title;
        this.authorID = author.getId();
        this.publishedYear = publishedYear;
        this.quantity = quantity;
    }
}
