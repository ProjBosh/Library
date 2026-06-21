package ru.library.books;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksViewController {

    private final BookRepository bookRepository;

    BooksViewController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // === Отображение списка ===
    @GetMapping
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books-list";
    }

    // === Показать форму добавления ===
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-add";   // новый шаблон book-add.html
    }

    // === Обработка добавления ===
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttrs) {
        // Здесь можно добавить валидацию
        bookRepository.save(book);
        redirectAttrs.addFlashAttribute("message", "Книга успешно добавлена!");
        return "redirect:/books";
    }

    // === Обработка удаления ===
    @PostMapping("/delete")
    public String deleteBooks(@RequestParam(value = "selectedIds", required = false) List<Long> ids,
                              RedirectAttributes redirectAttrs) {
        if (ids != null && !ids.isEmpty()) {
            bookRepository.deleteAllById(ids);
            redirectAttrs.addFlashAttribute("message", "Выбранные книги удалены.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Не выбрано ни одной книги.");
        }
        return "redirect:/books";
    }
}