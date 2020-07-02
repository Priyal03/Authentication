package com.pri.ws.jwt.model;

public class AuthenticationRequest {

	private String username;
	private String password;
	
	public AuthenticationRequest() {
		// TODO Auto-generated constructor stub
	}

	AuthenticationRequest(String uname,String pass){
		
		username=uname;
		password=pass;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
