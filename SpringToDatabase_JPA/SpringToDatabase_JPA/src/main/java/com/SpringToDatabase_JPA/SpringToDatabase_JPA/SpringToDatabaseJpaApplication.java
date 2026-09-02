package com.SpringToDatabase_JPA.SpringToDatabase_JPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class SpringToDatabaseJpaApplication {


	public static void main(String[] args) {
		System.out.println("User password: "+ new BCryptPasswordEncoder().encode("user123"));
		System.out.println("Admin password: "+ new BCryptPasswordEncoder().encode("admin123"));
		SpringApplication.run(SpringToDatabaseJpaApplication.class, args);
	}

}
