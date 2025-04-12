package pl.library.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.library.library.entities.Book;
import pl.library.library.repositories.BookRepository;
import pl.library.library.services.BookService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;

    private Book book;
    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setId(2L);
        book.setTitle("Test title");
        book.setAuthor("Test author");
        book.setIsbn("1234567890");
    }

    private void verifyBookProperties(Book book) {
        assertNotNull(book);
        assertEquals("Test title", book.getTitle());
        assertEquals("Test author", book.getAuthor());
        assertEquals("1234567890", book.getIsbn());
    }
    @Test
    void SaveBookTest() {
        Mockito.when(bookRepository.save(book)).thenReturn(book);
       Book savedBook = bookService.save(book);
      verifyBookProperties(savedBook);
    }
    @Test
    void findBookByIdTest() {
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Book foundBook = bookService.findById(book.getId());
        verifyBookProperties(foundBook);
    }
    @Test
    void CountBooksTest() {}
}
