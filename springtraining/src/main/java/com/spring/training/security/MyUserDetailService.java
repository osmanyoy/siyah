package com.spring.training.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetailService implements UserDetailsService {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public MyUserDetailService(final BCryptPasswordEncoder bCryptPasswordEncoderParam) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoderParam;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		if (username.equals("osman")) {
			return new User(username,
			                this.bCryptPasswordEncoder.encode("1234"),
			                AuthorityUtils.createAuthorityList("ADMIN"));
		} else if (username.equals("ali")) {
			return new User(username,
			                this.bCryptPasswordEncoder.encode("1234"),
			                AuthorityUtils.createAuthorityList("USER"));
		} else {
			throw new UsernameNotFoundException("Not a valid user");
		}
	}

}
