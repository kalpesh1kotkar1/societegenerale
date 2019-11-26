package com.societegenerale.katatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



/**
 * This is Main method of Application
 * @author Kalpesh Kotkar
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class SocieteGeneraleKataTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocieteGeneraleKataTestApplication.class, args);
	}
}
