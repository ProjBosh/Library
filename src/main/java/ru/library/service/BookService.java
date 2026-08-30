package ru.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ru.library.domain.Author;
import ru.library.domain.Book;
import ru.library.repository.AuthorRepository;
import ru.library.repository.BookRepository;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void create(@Validated String isbn, @Validated String title, @Validated Long authorID, Integer publishedYear, Integer quantity) {
        Author authorReference = authorRepository.findById(authorID)
                        .orElseThrow(() -> new IllegalArgumentException("Author not found"));;

        Book newBook = new Book(isbn, title, authorReference, publishedYear, quantity);
        bookRepository.save(newBook);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void delete(@Validated long id) {
        bookRepository.deleteById(id);
    }
}
