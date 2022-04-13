package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
