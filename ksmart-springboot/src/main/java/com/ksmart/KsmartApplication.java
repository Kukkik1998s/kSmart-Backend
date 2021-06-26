package com.ksmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class KsmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(KsmartApplication.class, args);
	}

}
