package com.example.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	 @RequestMapping(value = "/")
	   public String home() {
	      return "Eureka Client application - Customer";
	 }
}
