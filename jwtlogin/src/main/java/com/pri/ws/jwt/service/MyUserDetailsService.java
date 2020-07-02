package com.pri.ws.jwt.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pri.ws.impl.UserRepository;
import com.pri.ws.jwt.model.MyUserDetails;
import com.pri.ws.jwt.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUsername(username);
		
		user.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+username));
		
		System.out.println(user.get().getUserName()+" ----------DEBUG------- "+user.get().getPassword());
		
		return user.map(MyUserDetails::new).get();
		//return new User("foo","foo",new ArrayList<>());
	}

}

