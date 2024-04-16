package com.example.customerservice.entity;


import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue
	private int customerId;
	private String customerName;
	
	@ElementCollection(targetClass=String.class)
	private List<String> contactNos;
	
	private String environment;
	
}
