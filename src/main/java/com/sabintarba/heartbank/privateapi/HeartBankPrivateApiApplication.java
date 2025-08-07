package com.sabintarba.heartbank.privateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HeartBankPrivateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeartBankPrivateApiApplication.class, args);
	}
}
