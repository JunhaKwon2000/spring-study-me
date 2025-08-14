package com.winter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // This annotation is used to scan for servlet components like filters and servlets
public class Gdj92MavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Gdj92MavenApplication.class, args);
	}

}
