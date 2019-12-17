package com.strickers.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strickers.bankingapp.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {
	public Bank findByIfscCode(String ifscCode);

}
