package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entity.BookEntity;
import com.bookStore.bookStore.exception.ResourceNotFoundException;
import com.bookStore.bookStore.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }

    public List<BookEntity> getAllBook() {
        return bookRepository.findAll();
    }

    public BookEntity getBookById(int id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        return book.get();
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    public List<BookEntity> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return bookRepository.findAll();
        }
        List<BookEntity> byName = bookRepository.findByNameContainingIgnoreCase(query);
        byName.addAll(bookRepository.findByAuthorContainingIgnoreCase(query));
        return byName.stream().distinct().toList();
    }
}
