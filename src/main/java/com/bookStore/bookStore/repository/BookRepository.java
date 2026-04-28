package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByNameContainingIgnoreCase(String name);
    List<BookEntity> findByAuthorContainingIgnoreCase(String author);
}
