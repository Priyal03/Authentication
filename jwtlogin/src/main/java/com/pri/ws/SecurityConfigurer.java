package com.pri.ws;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pri.ws.jwt.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter filter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests()
//		.antMatchers("/admin").hasRole("ADMIN")
//		.antMatchers("/user").hasAnyRole("ADMIN","USER")
//		.antMatchers("/").permitAll();
		//.and().formLogin();
		
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated()//.antMatchers(HttpMethod.GET,"/").permitAll()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
//		http.cors().and().csrf().disable().
//        authorizeRequests()
//        .antMatchers("/api/auth/**").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
