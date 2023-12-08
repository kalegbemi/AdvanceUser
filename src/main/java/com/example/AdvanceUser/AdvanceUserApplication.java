package com.example.AdvanceUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AdvanceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvanceUserApplication.class, args);
	}

}
