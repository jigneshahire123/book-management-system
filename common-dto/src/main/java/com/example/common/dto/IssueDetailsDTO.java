package com.example.common.dto;

public class IssueDetailsDTO {

	private int id;
	private int isbn;
	private String custid;
	private int noofcopies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public int getNoofcopies() {
		return noofcopies;
	}

	public void setNoofcopies(int noofcopies) {
		this.noofcopies = noofcopies;
	}

	@Override
	public String toString() {
		return "IssueDetailsDTO [id=" + id + ", isbn=" + isbn + ", custid=" + custid + ", noofcopies=" + noofcopies
				+ "]";
	}

}
