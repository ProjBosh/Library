package ru.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ru.library.domain.Book;
import ru.library.repository.BookRepository;

@Service
public class BookService {
    
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public void create(@Validated String isbn, @Validated String title, @Validated String author, Integer publishedYear, Integer quantity) {
        Book newBook = new Book(isbn, title, author, publishedYear, quantity);
        bookRepository.save(newBook);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public void delete(@Validated long id) {
        bookRepository.deleteById(id);
    }
}
