package ru.library.books;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;

    @Column(unique = true)
    private String isbn;

    private Integer publishedYear;
    private Integer quantity;
}
