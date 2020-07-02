package com.pri.ws.jwt.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	private int uuid;
    private String username;
    private String password;
    private boolean active;
   // private String roles;

	private List<GrantedAuthority> authorities;

	public MyUserDetails(User user) {
		this.username = user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();
	//	this.roles=user.getRoles();
		this.uuid=user.getId();
		this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

	

//	public String getRoles() {
//		return roles;
//	}
//
//	public void setRoles(String roles) {
//		this.roles = roles;
//	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
}
