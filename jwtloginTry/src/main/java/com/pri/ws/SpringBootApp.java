/**
 * 
 */
package com.pri.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pri.ws.impl.UserRepository;

/**
 * @author coder
 *
 */

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
public class SpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}