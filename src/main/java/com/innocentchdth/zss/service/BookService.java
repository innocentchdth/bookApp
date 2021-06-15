package com.innocentchdth.zss.service;

import java.util.List;

import com.innocentchdth.zss.dto.BookDto;

public interface BookService {
	
	 void createBook(BookDto bookDto);
	 List<BookDto> getAllBooks();
	BookDto getBookById(Long id);
   // void bookTransaction(TransactionRequestDto transactionRequestDto);
	String bookTransaction();
	}


