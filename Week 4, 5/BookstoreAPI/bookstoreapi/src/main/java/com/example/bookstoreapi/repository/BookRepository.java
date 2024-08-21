package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.model.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);
}
