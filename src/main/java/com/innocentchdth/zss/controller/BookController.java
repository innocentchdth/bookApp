package com.innocentchdth.zss.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.innocentchdth.zss.dto.BookDto;
import com.innocentchdth.zss.dto.TransactionRequestDto;
import com.innocentchdth.zss.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Book Controller")
public class BookController {

	@Value("${apiKey}")
	private String apiKey;

	private final RestTemplate restTemplate;

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.restTemplate = new RestTemplate();
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
	  public ResponseEntity<String> buyBook(@RequestBody TransactionRequestDto transactionRequestDto){
	  final String uri = "https://lab.v.co.zw/interview/api/transaction";
	  HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "Bearer " + apiKey); 
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity <TransactionRequestDto> request = new HttpEntity <>( transactionRequestDto, headers);
      
      ResponseEntity <String> transactionEntity = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
	 
      String body = transactionEntity.getBody();
     
	   return new ResponseEntity<String>(body, HttpStatus.ACCEPTED);
	  
	  
	  
	  
	  
	  }
	 
}
