package com.example.bookms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookms.model.Book;
import com.example.bookms.repository.BookRepository;
import com.example.common.dto.BooksDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BooksService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ObjectMapper mapper;

	public BooksDTO addBook(BooksDTO bookDTO) {
		Book book = new Book();
		book.setAuthor(bookDTO.getAuthor());
		book.setTitle(bookDTO.getTitle());
		book.setPublishedDate(bookDTO.getPublishedDate());
		book.setTotalCopies(bookDTO.getTotalCopies());
		book.setIssuedCopies(0);
		book = bookRepository.save(book);
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setAvailableCopies(bookDTO.getTotalCopies() - bookDTO.getIssuedCopies());
		return bookDTO;
	}
	
	public BooksDTO updateBook(BooksDTO bookDTO) {
		Optional<Book> book = bookRepository.findById(bookDTO.getIsbn());
		if(book.isPresent()) {
			Book bookToUpdate = book.get();
			if(bookDTO.getTotalCopies() > 0) {
				bookToUpdate.setTotalCopies(bookDTO.getTotalCopies());
			}
			if(bookDTO.getIssuedCopies() > 0) {
				bookToUpdate.setIssuedCopies(bookDTO.getIssuedCopies());
			}
			Book updatedBook = bookRepository.save(bookToUpdate);
			bookDTO.setAvailableCopies(updatedBook.getTotalCopies()-updatedBook.getIssuedCopies());
		}else {
			return null;
		}
		return bookDTO;
	}

	public List<BooksDTO> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		
		List<BooksDTO> booksList= mapper.convertValue(books, new TypeReference<List<BooksDTO>>() {});
		
		booksList.forEach(book ->{
			book.setAvailableCopies(book.getTotalCopies() - book.getIssuedCopies());
		});
		return booksList;
		
	}
	
	public BooksDTO getBook(int isbn) {
		Optional<Book> book = bookRepository.findById(isbn);
		if(book.isPresent()) {
			BooksDTO bookDto= mapper.convertValue(book, BooksDTO.class); 
			bookDto.setAvailableCopies(bookDto.getTotalCopies() - bookDto.getIssuedCopies());
			return bookDto;
		}else {
			return null;
		}
	}
	
	public void deleteBook(int isbn) {
		bookRepository.deleteById(isbn);
	}
	
}
