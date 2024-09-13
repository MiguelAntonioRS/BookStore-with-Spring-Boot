package com.bookStore.bookStore.service;

import com.bookStore.bookStore.entity.BookEntity;
import com.bookStore.bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(BookEntity book) {
        bookRepository.save(book);
    }

    public List<BookEntity> getAllBook() {
        return bookRepository.findAll();
    }

    public BookEntity getBookById(int id) {
        return bookRepository.findById(id).get();
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
