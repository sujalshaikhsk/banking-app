package com.strickers.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strickers.bankingapp.entity.FavoritePayee;

@Repository
public interface FavoritePayeeRepository extends JpaRepository<FavoritePayee, Integer>{

	FavoritePayee findByPayeeId(Integer payeeId);
}