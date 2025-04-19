# BookController Class Documentation

## Overview

This is an auto-generated documentation for class `BookController`.

## Java Code

```java
package pl.library.library.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.library.library.services.BookService;
import pl.library.library.entities.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/test")
    public int test(){
        return 1;
    }

        @PostMapping("/add")
        public Book addBook(@RequestBody Book book) {
            return bookService.save(book);
        }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
       return bookService.deleteBook(id);
    }


    @GetMapping("/all")
        public List<Book> getAllBooks() {
            return bookService.findAll();
        }
        @GetMapping("/id/{id}")
        public Book getBookById(@PathVariable long id) {
            return bookService.findById(id);
        }
        @GetMapping("/count/{title}")
        public int countByTitle(@PathVariable String title) {
            return bookService.countBookByTitle(title);
        }
        @GetMapping("/find/{title}")
    public Book findByTitle(@PathVariable String title) { return bookService.findByTitle(title); }
    }

```

## Gemini Response

```java
package pl.library.library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.library.library.services.BookService;
import pl.library.library.entities.Book;

import java.util.List;

/**
 * REST controller for managing books in the library system.
 * Provides endpoints for adding, deleting, retrieving, and searching books.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    /**
     * Constructs a BookController with the given BookService.
     * @param bookService The service used for book operations.
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * A test endpoint.  Should be removed in production.
     * @return The integer value 1.
     */
    @GetMapping("/test")
    public int test(){
        return 1;
    }

    /**
     * Adds a new book to the library.
     * @param book The book to be added.
     * @return The added book.
     */
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    /**
     * Deletes a book from the library by its ID.
     * @param id The ID of the book to be deleted.
     * @return A ResponseEntity indicating success or failure.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
       return bookService.deleteBook(id);
    }

    /**
     * Retrieves all books from the library.
     * @return A list of all books.
     */
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    /**
     * Retrieves a book by its ID.
     * @param id The ID of the book to be retrieved.
     * @return The book with the given ID.
     */
    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.findById(id);
    }

    /**
     * Counts the number of books with the given title.
     * @param title The title to search for.
     * @return The number of books with the given title.
     */
    @GetMapping("/count/{title}")
    public int countByTitle(@PathVariable String title) {
        return bookService.countBookByTitle(title);
    }

    /**
     * Finds a book by its title.
     * @param title The title to search for.
     * @return The book with the given title, or null if not found.
     */
    @GetMapping("/find/{title}")
    public Book findByTitle(@PathVariable String title) { return bookService.findByTitle(title); }
}
```

**Summary:**

This Java class acts as a REST controller for managing books within a library application.  It provides endpoints to add, delete, retrieve individual books by ID, retrieve all books, count books by title, and find a book by title.  It uses a `BookService` to handle the underlying business logic.  The `/test` endpoint is for testing purposes and should be removed in production.

---
