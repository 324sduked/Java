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
