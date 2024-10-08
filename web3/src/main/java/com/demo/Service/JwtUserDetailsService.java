package com.demo.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.model.User;
import com.demo.repo.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {


	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private PasswordEncoder passwordEncoder;

	

	@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
User user = userRepository.findByUsername(username);
if (user == null) {
 throw new UsernameNotFoundException("User not found with username: " + username);
}
return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

	
	public User save(User user) {
 user.setPassword(passwordEncoder.encode(user.getPassword()));
 
 return userRepository.save(user);
    }
}
