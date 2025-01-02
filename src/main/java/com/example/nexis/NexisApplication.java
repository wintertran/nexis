package com.example.nexis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NexisApplication {

	public static void main(String[] args) {
//		String databaseUrl = DotenvConfig.get("DATASOURCE_URL");
//		String databaseUsername = DotenvConfig.get("DATASOURCE_USERNAME");
//		String databasePassword = DotenvConfig.get("DATASOURCE_PASSWORD");
//
//		String mailUsername = DotenvConfig.get("MAIL_USERNAME");
//		String mailPassword = DotenvConfig.get("MAIL_PASSWORD");
//
//		// Đặt giá trị vào System Properties
//		System.setProperty("spring.datasource.url", databaseUrl);
//		System.setProperty("spring.datasource.username", databaseUsername);
//		System.setProperty("spring.datasource.password", databasePassword);
//
//		System.setProperty("spring.mail.username", mailUsername);
//		System.setProperty("spring.mail.password", mailPassword);

		SpringApplication.run(NexisApplication.class, args);
		System.out.println("Nexis Application is running...");
	}
}
