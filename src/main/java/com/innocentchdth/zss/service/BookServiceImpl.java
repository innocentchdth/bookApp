package com.innocentchdth.zss.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innocentchdth.zss.dto.BookDto;
import com.innocentchdth.zss.exceptions.BookNotFoundException;
import com.innocentchdth.zss.exceptions.DuplicateResourceException;
import com.innocentchdth.zss.model.Book;
import com.innocentchdth.zss.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

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

	/*
	 * @Override public void bookTransaction(TransactionRequestDto
	 * transactionRequestDto) { transactionRequestDto =
	 * restTemplate.postForObject(uri, transactionRequestDto,
	 * TransactionRequestDto.class);
	 * 
	 * }
	 */
}
