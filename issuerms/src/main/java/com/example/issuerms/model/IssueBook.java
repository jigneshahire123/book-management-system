package com.example.issuerms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ISSUEBOOK")
public class IssueBook {
	
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	 private int id;
	 
	 @Column(name = "isbn")
	 private int isbn;
	
    
    @Column(name = "custid")
    private String custid;


    @Column(name = "noofcopies")
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


}