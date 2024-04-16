package com.example.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loginservice.entity.Login;

@Repository
public interface ILoginRepository extends JpaRepository<Login, String> {

}
