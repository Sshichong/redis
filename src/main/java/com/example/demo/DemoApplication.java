package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		System.getProperties().put("projectName", "springApp");

		SpringApplication.run(DemoApplication.class, args);
	}
	/*public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}*/
}