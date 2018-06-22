package com.spring.training.customer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRest {

	@Autowired
	private UserCredentialDAO ucDAO;

	@Autowired
	private RoleDAO rDAO;

	@GetMapping("/add")
	public String addUser(@Valid final UserCredential user) {
		List<Role> roles = user.getRoles();
		List<Role> newRoles = new ArrayList<>();
		for (Role role : roles) {
			Role findByRole = this.rDAO.findByRole(role.getRole());
			if (findByRole != null) {
				newRoles.add(findByRole);
			} else {
				Role save = this.rDAO.save(role);
				newRoles.add(save);
			}
		}
		user.setRoles(newRoles);
		this.ucDAO.save(user);
		return "OK";
	}

}
