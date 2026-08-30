package ru.library.controller;

// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import ru.library.domain.Book;
// import ru.library.repository.BookRepository;
// import ru.library.service.BookService;

// import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    // private final BookService bookService;
    // private final BookRepository bookRepository;

    // public BooksController(BookRepository bookRepository) {
    //     this.bookService = null;
    //     this.bookRepository = bookRepository;
    // }

    // public BooksController(BookService bookService) {
    //     // this.bookRepository = null;
    //     this.bookService = bookService;
    // }

    // @GetMapping
    // public List<Book> getAllBooks() {
    //     return bookRepository.findAll();
    // }

    // public void create(@Validated String title, @Validated String author, @Validated String isbn, Integer publishedYear, Integer quantity) {
    //     bookService.create(title, author, isbn, publishedYear, quantity);
    // }
}
