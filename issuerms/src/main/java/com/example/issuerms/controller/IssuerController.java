package com.example.issuerms.controller;

import java.util.List;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.dto.BooksDTO;
import com.example.common.dto.IssueDetailsDTO;
import com.example.issuerms.service.IssuerService;



@RestController
@RequestMapping("issuer")
public class IssuerController {
    Logger log  = LoggerFactory.getLogger(IssuerController.class);
    
    @Autowired
    IssuerService issuerService;
    
    @GetMapping("/bookDetails")
    public ResponseEntity<List<BooksDTO>> getBooks(){
    	List<BooksDTO> books = issuerService.getBooks();
    	if(null != books && !books.isEmpty()) {
    		return new ResponseEntity<>(books, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(books, HttpStatus.NOT_FOUND);
    	}
    }
    
    @PostMapping("/books")
    public ResponseEntity<String> issueBoook(@RequestBody IssueDetailsDTO issueDetails){
    	issueDetails.setCustid(MDC.get("custId").toString());
    	String message = issuerService.issueBook(issueDetails);
    	if(null != message) {
    		return new ResponseEntity<>(message, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    	}
    }

}
