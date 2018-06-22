package com.spring.training.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailService implements UserDetailsService {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserCredentialDAO credentialDAO;

	public MyUserDetailService(final BCryptPasswordEncoder bCryptPasswordEncoderParam) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoderParam;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserCredential findByUsername = this.credentialDAO.findByUsername(username);
		if (findByUsername == null) {
			throw new UsernameNotFoundException("Not a valid user");
		}
		List<GrantedAuthority> authorities = new ArrayList<>(10);

		for (Role role : findByUsername.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}

		return new User(username,
		                this.bCryptPasswordEncoder.encode(findByUsername.getPassword()),
		                authorities);
	}

}
