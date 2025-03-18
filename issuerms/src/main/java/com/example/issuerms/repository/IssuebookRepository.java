package com.example.issuerms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.issuerms.model.IssueBook;

public interface IssuebookRepository extends JpaRepository<IssueBook, Integer> {
}