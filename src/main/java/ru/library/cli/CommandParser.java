package ru.library.cli;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import ru.library.service.AuthorService;
import ru.library.service.BookService;

@Component
public class CommandParser {  
    private String object;
    private String[] args;
    private int lengthArgs = 0;

    private final BookService bookService;
    private final AuthorService authorService;

    public CommandParser (BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void execute(String consoleCommand) {
        String[] arrCommand = consoleCommand.split(" ");
        int length = arrCommand.length;

        if (length >= 2) {
            String action = arrCommand[0];  // Обрабатываемое действие
            object = arrCommand[1];         // Обрабатываем сущеность
            
            if (length >= 3) {
                args = new String[length - 2];
            }

            fillArguments(arrCommand);
            
            switch (action) {
                case "add":
                    addObject();
                    break;

                case "list":
                    showListObjects();
                    break;

                case "delete":
                    deleteObject();
                    break; 
            
                default:
                    break;
            } 

            System.out.println("--- --- --- --- --- --- --- ---\n");
        } 
        else {
            System.err.println("Недостаточное количество параметров. Минимальное количество: 3");
        }
    }

    private void fillArguments(String[] arrCommand) {
        if(arrCommand.length < 3)
            return;
        
        int indexArg = 0;

        for(int i = 2; i < arrCommand.length; i++)
            args[indexArg++] = arrCommand[i].trim();

        lengthArgs = args.length;
    }
    
    private void addObject() {
        if (object.isEmpty())
            return;

        switch (object) {
            case "book": 
                addBook();
                break;
            
            case "author": 
                addAuthor();
                break;

            default:
                break;
        }
    }
    
    private void addBook() {
        if (printErrorIfInvalidArgCount(5)) {
            System.out.println("\nadd book isbn title author published_year quantity");
            return;
        }

        try {
            bookService.create(
                args[0], 
                args[1], 
                args[2], 
                Integer.valueOf(args[3]),
                Integer.valueOf(args[4]) 
            );
            System.out.println("The book was succsessfully create\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAuthor() {
        if (lengthArgs < 4 || lengthArgs > 5) {
            System.out.println("add author требует 4 или 5 аргументов (дата смерти опциональна)");
            System.out.println("\nadd author last_name first_name middle_name date_of_birth {date_of_date}");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate dateOfBirth = LocalDate.parse(args[3], formatter);
        LocalDate dateOfDeath = (lengthArgs == 4 ? null : LocalDate.parse(args[4], formatter));

        try {
            authorService.create(
                args[0],
                args[1],
                args[2],
                dateOfBirth,
                dateOfDeath
            );
            System.out.println("The author was succsessfully created\n");
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    private void showListObjects() {
        if (object.isEmpty())
            return;

        switch (object) {
            case "book":
                showListBooks();
                break;
            
            case "author":
                showListAuthors();
                break;
            
            default:
                break;
        }
    }

    private void showListBooks() {
        System.out.println("\nList of books:");
        bookService.findAll()
            .forEach(
                b -> System.out.println(b.getIsbn() + " |> " + b.getId() + " / " + b.getTitle() +  " / " + b.getAuthor() 
                    + " / " + b.getPublishedYear() +  " / " + b.getQuantity())
            );
    }

    private void showListAuthors() {
        System.out.println("\nList of authors:");
        authorService.findAll()
            .forEach(a -> System.out.println(a.getId() + " / " + a.getFullName() + " / " + a.getDateOfBirth() + " / " + a.getDateOfDeath()));  
    }

    public void deleteObject() {
        if (object.isEmpty())
            return;  
        
        switch (object) {
            case "book":
                deleteBook();
                break;
            
            case "author":
                deleteAuthor();
                break;
            
            default:
                break;
        }
    }

    private void deleteBook() {
        if (printErrorIfInvalidArgCount(1))
            return;

        try {
            bookService.delete(Long.valueOf(args[0]));
            System.out.println("The book was succsessfully deleted\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteAuthor() {
        if (printErrorIfInvalidArgCount(1))
            return;

        try {
            authorService.delete(Long.valueOf(args[0]));
            System.out.println("The auhtor was succsessfully deleted\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean printErrorIfInvalidArgCount(int needed) {
        if (lengthArgs != needed) {
            System.err.println("Добавление " + object + " невозможно. Ожидаемое количество аргументов после '" + object + "' - " + needed + ". Введено - " + lengthArgs);
            return true;
        }
        return false;
    }
}
