package com.innocentchdth.zss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innocentchdth.zss.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	
}