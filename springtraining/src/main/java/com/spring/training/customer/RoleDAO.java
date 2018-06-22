package com.spring.training.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
