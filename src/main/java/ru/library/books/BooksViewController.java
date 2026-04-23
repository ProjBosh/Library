package ru.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksViewController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> booksList = bookRepository.findAll();
        model.addAttribute("books", booksList);
        return "books-list"; // Вернет books-list.html
    }
}
