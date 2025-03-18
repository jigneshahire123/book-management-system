package com.example.bookms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookms.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}