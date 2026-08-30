package ru.library;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.library.cli.CommandParser;

@SpringBootApplication
public class MainCLI implements CommandLineRunner{
    private final CommandParser commandParser;
    private final ApplicationContext applicationContext;

    public MainCLI(CommandParser commandParser, ApplicationContext applicationContext) {
        this.commandParser = commandParser;
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainCLI.class, args);
    }

    @Override
    public void run(String... args) {
        long startTime = System.currentTimeMillis();
        long stopTime = 0;
        int milliseconds = 1000;
        boolean getOut = false;

        try (Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Start enter:\n");
            while(!getOut) {                
                String command = scanner.nextLine().trim();
                if(command.equals("exit")){
                    getOut = true;
                } 
                else if(!command.trim().isEmpty()) {
                    commandParser.execute(command);
                }
            }
        }

        stopTime = System.currentTimeMillis();
        System.out.println("Working hours: " + (stopTime - startTime) / milliseconds);

        int exitCode = SpringApplication.exit(applicationContext, () -> 0);
        System.exit(exitCode);
    }
}
