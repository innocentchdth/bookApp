package com.innocentchdth.zss.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.innocentchdth.zss.dto.BookDto;
import com.innocentchdth.zss.dto.TransactionResponseDto;
import com.innocentchdth.zss.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
@Api(value = "Book Controller")
public class BookController {
	
	
	
	private final BookService bookService;

	
	
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    
    @ApiOperation(value = "Create Book")
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@Valid @RequestBody BookDto bookDto) {
        bookService.createBook(bookDto);
    }

    @ApiOperation(value = "Get Book By Id")
    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }


    @ApiOperation(value = "Get All Books")
    @GetMapping("/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }
    
  @ApiOperation(value = "Buy a book")
  @PostMapping("/transaction")
  ResponseEntity<TransactionResponseDto> buyBook(TransactionResponseDto transactionResponseDto) {
      bookService.bookTransaction();
      return new ResponseEntity<TransactionResponseDto>(transactionResponseDto, HttpStatus.ACCEPTED );
  }
}
	
	


