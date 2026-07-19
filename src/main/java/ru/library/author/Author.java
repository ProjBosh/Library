package ru.library.author;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String FirstName;
    private String LastName;
    private String MiddleName;
    private String FullName;

    private Date DateOfBirth;
    private Date DateOfDeath;
}
