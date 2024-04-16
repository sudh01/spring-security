package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
//// Swagger Config
//@OpenAPIDefinition(
//	    info = @Info(
//	        title = "Custom API title",
//	        version = "3.14"
//	    )
//	)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}