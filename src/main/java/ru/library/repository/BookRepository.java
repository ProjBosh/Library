package ru.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import ru.library.domain.Book;

// @Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT MAX(b.id) FROM Book AS b")
    Long findMaxId();
}
