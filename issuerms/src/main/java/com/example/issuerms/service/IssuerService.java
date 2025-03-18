package com.example.issuerms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.common.dto.BooksDTO;
import com.example.common.dto.IssueDetailsDTO;
import com.example.issuerms.model.IssueBook;
import com.example.issuerms.repository.IssuebookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Component
public class IssuerService {
	
	Logger LOG  = LoggerFactory.getLogger(IssuerService.class);

	@Autowired
	public WebClient.Builder webClient;
	
	@Autowired 
	IssuebookRepository ibRepo;
	
	public List<BooksDTO> getBooks() {
		Flux<BooksDTO> booksFlux = webClient.build().get().uri("http://localhost:8080/books/get").retrieve()
				.bodyToFlux(BooksDTO.class);
		
		return booksFlux
	    .collect(Collectors.toList())
	    .share().block();
		
	}

	public String issueBook(IssueDetailsDTO issueDetails) {
		LOG.info("Issuing book {} for customer {}",issueDetails.getIsbn(),issueDetails.getCustid());
		try {
			int bookId = issueDetails.getIsbn();
			BooksDTO bookToBeissued = webClient.build().get().uri("http://localhost:8080/books/get/"+bookId).retrieve()
					.bodyToMono(BooksDTO.class).block();
			
			if(bookToBeissued!=null && bookToBeissued.getAvailableCopies() >= issueDetails.getNoofcopies()) {
				IssueBook issueBook = new IssueBook();
				issueBook.setIsbn(issueDetails.getIsbn());
				issueBook.setCustid(issueDetails.getCustid());
				issueBook.setNoofcopies(issueDetails.getNoofcopies());
				IssueBook issueedDetails = ibRepo.save(issueBook);
				if(issueedDetails != null) {
					// update the number if issued copies
					bookToBeissued.setIssuedCopies(bookToBeissued.getIssuedCopies()+issueDetails.getNoofcopies());
					BooksDTO updatedBook = webClient.build().put().uri("http://localhost:8080/books/update").body(Mono.just(bookToBeissued), BooksDTO.class)
					.retrieve().bodyToMono(BooksDTO.class).block();
					return "Book "+updatedBook.getTitle()+" is issued to customer";
				}
			}else {
				return "Book "+bookToBeissued.getTitle()+" can not be issue to customer. No sufficient copies available";
			}
		}
		catch(Exception e) {
			LOG.error("Exception while geting book details {}",e);
		}
		
		return null;	
	}
}
