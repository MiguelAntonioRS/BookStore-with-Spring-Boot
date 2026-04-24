package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entity.BookEntity;
import com.bookStore.bookStore.exception.ResourceNotFoundException;
import com.bookStore.bookStore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private BookEntity testBook;

    @BeforeEach
    void setUp() {
        testBook = new BookEntity(1, "Clean Code", "Robert C. Martin", 29.99);
    }

    @Test
    @DisplayName("Should save book successfully")
    void saveBook_Success() {
        when(bookRepository.save(any(BookEntity.class))).thenReturn(testBook);
        
        BookEntity savedBook = bookService.save(testBook);
        
        assertNotNull(savedBook);
        assertEquals("Clean Code", savedBook.getName());
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    @DisplayName("Should return all books")
    void getAllBooks_Success() {
        List<BookEntity> books = Arrays.asList(testBook, new BookEntity(2, "Effective Java", "Joshua Bloch", 45.99));
        when(bookRepository.findAll()).thenReturn(books);
        
        List<BookEntity> result = bookService.getAllBook();
        
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return book by ID")
    void getBookById_Success() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(testBook));
        
        BookEntity result = bookService.getBookById(1);
        
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Clean Code", result.getName());
    }

    @Test
    @DisplayName("Should throw exception when book not found")
    void getBookById_NotFound() {
        when(bookRepository.findById(999)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(999));
    }

    @Test
    @DisplayName("Should delete book by ID")
    void deleteById_Success() {
        doNothing().when(bookRepository).deleteById(1);
        
        bookService.deleteById(1);
        
        verify(bookRepository, times(1)).deleteById(1);
    }
}