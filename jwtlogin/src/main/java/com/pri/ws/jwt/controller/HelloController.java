package com.pri.ws.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pri.ws.JWTUtil;
import com.pri.ws.jwt.model.AuthenticationRequest;
import com.pri.ws.jwt.model.AuthenticationResponse;
import com.pri.ws.jwt.service.MyUserDetailsService;

@RestController
public class HelloController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserdetails;
	
	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping("/hello")
	public String hello() {
		// TODO Auto-generated method stub
		return "todo yey!!";
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Username or Password is incorrect!");
		}
		
		final UserDetails userDetails=myUserdetails.loadUserByUsername(request.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
}
