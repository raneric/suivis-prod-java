package com.sgg.suivisprod.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sgg.suivisprod.domain.User;
import com.sgg.suivisprod.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user ==null) {
			throw new UsernameNotFoundException(username);
		}
		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getUserRole().getRole()).build();
		return userDetails;
	}

}
