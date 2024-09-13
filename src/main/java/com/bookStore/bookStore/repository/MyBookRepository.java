package com.bookStore.bookStore.repository;

import com.bookStore.bookStore.entity.MyBookListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookRepository extends JpaRepository<MyBookListEntity, Integer> {
}
