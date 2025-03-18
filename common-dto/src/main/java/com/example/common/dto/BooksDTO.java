package com.example.common.dto;

public class BooksDTO {

	private int isbn;
	private String author;
	private String publishedDate;
	private String title;
	private int totalCopies;
	private int issuedCopies;
	private int availableCopies=this.totalCopies - this.issuedCopies;
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}
	public int getIssuedCopies() {
		return issuedCopies;
	}
	public void setIssuedCopies(int issuedCopies) {
		this.issuedCopies = issuedCopies;
	}
	
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	@Override
	public String toString() {
		return "BooksDTO [isbn=" + isbn + ", author=" + author + ", publishedDate=" + publishedDate + ", title=" + title
				+ ", totalCopies=" + totalCopies + ", issuedCopies=" + issuedCopies + ", availableCopies="
				+ availableCopies + "]";
	}
	
}
