package com.spring.training.customer;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserCredential {

	@Id
	@GeneratedValue
	private long ucId;

	@Column(unique = true)
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	@OneToOne(cascade = CascadeType.ALL,
	          fetch = FetchType.EAGER)
	@MapsId
	@NotNull
	private Customer customer;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.REFRESH },
	            fetch = FetchType.EAGER)
	@NotEmpty
	@Size(min = 1)
	private List<Role> roles;

	public long getUcId() {
		return this.ucId;
	}

	public void setUcId(final long ucId) {
		this.ucId = ucId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(final List<Role> roles) {
		this.roles = roles;
	}

}
