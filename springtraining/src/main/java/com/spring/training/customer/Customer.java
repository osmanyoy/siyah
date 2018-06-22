package com.spring.training.customer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private long custId;
	private String name;
	private String surname;

	@OneToOne(cascade = CascadeType.ALL,
	          fetch = FetchType.EAGER,
	          mappedBy = "customer")
	private UserCredential userCredential;

	public long getCustId() {
		return this.custId;
	}

	public void setCustId(final long custId) {
		this.custId = custId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public UserCredential getUserCredential() {
		return this.userCredential;
	}

	public void setUserCredential(final UserCredential userCredential) {
		this.userCredential = userCredential;
	}

}
