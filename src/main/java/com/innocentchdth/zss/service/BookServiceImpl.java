package com.innocentchdth.zss.service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.innocentchdth.zss.dto.BookDto;
import com.innocentchdth.zss.dto.CardDto;
import com.innocentchdth.zss.dto.TransactionRequestDto;
import com.innocentchdth.zss.dto.TransactionResponseDto;
import com.innocentchdth.zss.exceptions.BookNotFoundException;
import com.innocentchdth.zss.exceptions.DuplicateResourceException;
import com.innocentchdth.zss.model.Book;
import com.innocentchdth.zss.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Value("${apiKey}")
	private String apiKey ;
	
	/*
	 * @Autowired public TransactionResponseDto requestDto;
	 */

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	private final BookRepository bookRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createBook(BookDto bookDto) {
		// Check if bookDto is previously present
		Optional<Book> bookById = bookRepository.findById(bookDto.getId());
		bookById.ifPresent(book -> {
			throw new DuplicateResourceException(
					"Book with same id present. " + "Either use update API to update the book ");
		});
		if (!bookById.isPresent()) {
			LOGGER.info("No Duplicates found.");
			// Map bookDto to book
			Book book = modelMapper.map(bookDto, Book.class);
			// Set the status to available
			LOGGER.info("The data are mapped and ready to save.");

			// Save to book
			bookRepository.save(book);
		}
	}

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return mapBookListToBookDtoList(books);
	}

	@Override
	public BookDto getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book with id:" + id + " is not found."));

		return modelMapper.map(book, BookDto.class);
	}

	// Convert List of books to List of bookDto
	private List<BookDto> mapBookListToBookDtoList(List<Book> books) {
		return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
	}

	@Override
	public String bookTransaction() {
		RestTemplate restTemplate = new RestTemplate();
		
	TransactionRequestDto requestDto = new TransactionRequestDto();
	ZonedDateTime zonedDateTime = ZonedDateTime.parse("2021-06-10T13:22:30.746+02:00");
	CardDto card = new CardDto("1234560000000001", new Date(2020-01-01));
		requestDto.setType("PURCHASE");
		requestDto.setExtendedType("NONE");
		requestDto.setAmount(123.33);
		requestDto.setCreated(zonedDateTime);
		requestDto.setReference("6b82427b-9e9d-4d14-ae35-1e2e8c827c1d");
		requestDto.setNarration("Test Narration");
		requestDto.setCard(card);;
		requestDto.setAdditionalData(new HashMap<String, Object>() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			{
				put("SampleKey", "This is a sample value");

			}
		});

		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + apiKey);

		HttpEntity<TransactionRequestDto> entity = new HttpEntity<>(requestDto, headers);

		ResponseEntity<Object> response = restTemplate
				.exchange("https://lab.v.co.zw/interview/api/transaction", HttpMethod.POST, entity, Object.class);
		return response.getBody().toString();

}}
