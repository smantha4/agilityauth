package com.servicemesh.auth.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.servicemesh.auth" })
public class AgilityauthApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgilityauthApplication.class, args);
	}
}
