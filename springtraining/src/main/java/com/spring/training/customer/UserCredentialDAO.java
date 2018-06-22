package com.spring.training.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialDAO extends JpaRepository<UserCredential, Long> {

	UserCredential findByUsername(String username);

}
