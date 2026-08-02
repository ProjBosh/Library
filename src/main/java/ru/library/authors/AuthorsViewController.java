package ru.library.authors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorsViewController {

    private final AuthorRepository authorRepository;

    AuthorsViewController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // === Отображение списка ===
    @GetMapping
    public String showAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors-list";
    }

    // === Показать форму добавления ===
    @GetMapping("/add")
    public String showAllForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-add";
    }

    // === Обработка добавления ===
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        String fullName = author.getLastName() + " " + author.getFirstName();
        String middleName = author.getMiddleName();
        if(middleName != null && !middleName.isEmpty()) {
            fullName += " " + author.getMiddleName();
        }
        author.setFullName(fullName);
         
        authorRepository.save(author);
        redirectAttributes.addFlashAttribute("message", "Автор успешно добавлен");
        return "redirect:/authors";
    }

    // === Обработка удаления ===
    @PostMapping("/delete")
    public String deleteAuthors(@RequestParam(value = "selectedIds", required = false) List<Long> ids, 
                                RedirectAttributes redirectAttributes) {
        if (ids != null && !ids.isEmpty()) {
            authorRepository.deleteAllById(ids);
            redirectAttributes.addFlashAttribute("message", "Выбранные книги удалены.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Не выбрано ни одной книги.");
        }
        return "redirect:/authors";
    }
}