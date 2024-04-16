package com.example.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.customerservice.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

}
