package com.strickers.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strickers.bankingapp.entity.Customer;

@Repository
public interface customerRepository extends JpaRepository<Customer, Integer>{

}
