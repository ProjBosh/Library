package ru.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ru.library.domain.Author;
import ru.library.repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void create(@Validated String lastName, @Validated String firstName, @Validated String middleName, @Validated LocalDate dateOfBirth, LocalDate dateOfDeath) {
        Author author = new Author(lastName, firstName, middleName, dateOfBirth, dateOfDeath);
        authorRepository.save(author);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void delete(@Validated long id) {
        authorRepository.deleteById(id);
    }
}
