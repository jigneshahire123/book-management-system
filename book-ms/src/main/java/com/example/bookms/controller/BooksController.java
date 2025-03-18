package com.example.bookms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookms.service.BooksService;
import com.example.common.dto.BooksDTO;

@RestController
@RequestMapping("books")
public class BooksController {
        Logger log  = LoggerFactory.getLogger(BooksController.class);

       @Autowired
       BooksService booksService;

        
        
        @PostMapping("/add")
        public ResponseEntity<BooksDTO> addBook(@RequestBody BooksDTO book)
        {
        	BooksDTO addedBook = booksService.addBook(book);
            return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
        }
        
        @GetMapping("/get")
        public ResponseEntity<List<BooksDTO>> getAllBooks() {
        	List<BooksDTO> books = booksService.getAllBooks();
        	if(null != books && !books.isEmpty()) {
        		return new ResponseEntity<>(books, HttpStatus.OK);
        	}else {
        		return new ResponseEntity<>(books, HttpStatus.NOT_FOUND);
        	}
    }

        @GetMapping("/get/{isbn}")
        public ResponseEntity<BooksDTO> getBook(@PathVariable("isbn") int isbn) {
        	BooksDTO book = booksService.getBook(isbn);
        	if(null != book ) {
        		return new ResponseEntity<>(book, HttpStatus.OK);
        	}else {
        		return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        	}
        }

        @PutMapping("/update")
        public ResponseEntity<BooksDTO> updateBook(
                @RequestBody BooksDTO book){
        	booksService.updateBook(book);
        	if(null != book ) {
        		return new ResponseEntity<>(book, HttpStatus.OK);
        	}else {
        		return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
        	}
        	
        }
        

        @DeleteMapping("/delete/{isbn}")
        public ResponseEntity<String> deleteBook(@PathVariable("isbn") int isbn) {
            booksService.deleteBook(isbn);
            return new ResponseEntity<>("Book deleted with id "+isbn, HttpStatus.OK);
    }

        /*
        @PutMapping("/updatebook/{isbn}")
        public ResponseEntity<String> updateBook(@PathVariable("isbn") String isbn,
            @RequestBody Book book) {
            log.info("updating book");
        if(bookRepository.findById(isbn).isPresent()) {
            bookRepository.findById(isbn).map(student -> {
                book.setAuthor(book.getAuthor());
                book.setIsbn(book.getIsbn());
                book.setTitle(book.getTitle());
                book.setIssuedCopies(book.getIssuedCopies());
                book.setTotalCopies(book.getTotalCopies());
                book.setPublishedDate(book.getPublishedDate());
                bookRepository.save(book);
                return new ResponseEntity<>("A book update by id "+isbn, HttpStatus.OK);
            });
        } else
        {
            bookRepository.save(book);
            return new ResponseEntity<>("A new book added in library by id "+isbn, HttpStatus.OK);
        }
        log.info("book updated");
            return new ResponseEntity<>("A book update by id "+isbn, HttpStatus.OK);

    }
    */

}
