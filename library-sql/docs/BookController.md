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
 * REST controller for managing books.  Provides endpoints for adding, deleting, retrieving, and querying books.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    /**
     * Constructs a BookController with the given BookService.
     * @param bookService The service to use for book operations.
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * A test endpoint, returning 1.  For testing purposes only.
     * @return 1
     */
    @GetMapping("/test")
    public int test(){
        return 1;
    }

    /**
     * Adds a new book to the library.
     * @param book The book to add.
     * @return The added book.
     */
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    /**
     * Deletes a book by its ID.
     * @param id The ID of the book to delete.
     * @return A ResponseEntity indicating success or failure.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
       return bookService.deleteBook(id);
    }


    /**
     * Retrieves all books in the library.
     * @return A list of all books.
     */
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    /**
     * Retrieves a book by its ID.
     * @param id The ID of the book to retrieve.
     * @return The book with the given ID.
     */
    @GetMapping("/id/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.findById(id);
    }

    /**
     * Counts the number of books with a given title.
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

This Java class `BookController` acts as a REST controller for managing books within a library application. It offers endpoints to add, delete, retrieve individual books, retrieve all books, count books by title, and find books by title.  It utilizes a `BookService` for underlying data operations.  A test endpoint is also included.

---
