# BookService Class Documentation

## Overview

This is an auto-generated documentation for class `BookService`.

## Java Code

```java
package pl.library.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.library.library.entities.Book;
import pl.library.library.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDeletionServiceImpl bookDeletionService;

    @Autowired
    public BookService(BookRepository bookRepository, BookDeletionServiceImpl bookDeletionService) {
        this.bookRepository = bookRepository;
        this.bookDeletionService = bookDeletionService;
    }
    public ResponseEntity<String> deleteBook(Long id) {return bookDeletionService.deleteBook(id);}
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAll(){
        List<Book> books = bookRepository.findAll();
        System.out.println("Books: " + books);
        return books;
    }
    public int countBookByTitle(String title){
        return bookRepository.countBookByTitle(title);
    }
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}

```

## Gemini Response

```java
package pl.library.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.library.library.entities.Book;
import pl.library.library.repositories.BookRepository;

import java.util.List;

/**
 * Service class for managing books. Provides methods for saving, retrieving, deleting, and counting books.
 * Uses a {@link BookRepository} to interact with the database and a {@link BookDeletionServiceImpl} for handling book deletion.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDeletionServiceImpl bookDeletionService;

    /**
     * Constructs a new BookService instance.
     * @param bookRepository The repository for accessing book data.
     * @param bookDeletionService The service for handling book deletion.
     */
    @Autowired
    public BookService(BookRepository bookRepository, BookDeletionServiceImpl bookDeletionService) {
        this.bookRepository = bookRepository;
        this.bookDeletionService = bookDeletionService;
    }

    /**
     * Deletes a book by its ID.
     * @param id The ID of the book to delete.
     * @return A ResponseEntity indicating the success or failure of the deletion.
     */
    public ResponseEntity<String> deleteBook(Long id) {return bookDeletionService.deleteBook(id);}

    /**
     * Saves a book to the database.
     * @param book The book to save.
     * @return The saved book.
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieves a book by its ID.
     * @param id The ID of the book to retrieve.
     * @return The book with the given ID, or null if not found.
     */
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all books from the database.
     * @return A list of all books.
     */
    public List<Book> findAll(){
        List<Book> books = bookRepository.findAll();
        System.out.println("Books: " + books); //This line should ideally be removed for production code.  It's for debugging purposes only.
        return books;
    }

    /**
     * Counts the number of books with a given title.
     * @param title The title to search for.
     * @return The number of books with the given title.
     */
    public int countBookByTitle(String title){
        return bookRepository.countBookByTitle(title);
    }

    /**
     * Retrieves a book by its title.
     * @param title The title of the book to retrieve.
     * @return The book with the given title, or null if not found.
     */
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
```

**Summary:**

This service class provides a comprehensive interface for managing books within a library application.  It handles saving, retrieving (by ID and title), deleting, and counting books, delegating database interactions to a repository and utilizing a separate service for deletion handling.  The `findAll` method includes a debugging print statement that should be removed in a production environment.

---
