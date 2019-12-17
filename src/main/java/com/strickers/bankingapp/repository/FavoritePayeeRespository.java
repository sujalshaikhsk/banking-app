package com.strickers.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.strickers.bankingapp.entity.FavoritePayee;

@Repository
public interface FavoritePayeeRespository extends JpaRepository<FavoritePayee, Integer> {

	@Query("select p from FavoritePayee p where p.payeeId=:payeeId")
	public FavoritePayee findByPayeeId(Integer payeeId);

	@Query("select p from FavoritePayee p where p.payeeId=:payeeId and p.customer.customerId=:customerId")
	public FavoritePayee findByPayeeIdAndCustomerId(@Param("customerId") Integer customerId, @Param("payeeId") Integer payeeId);


}
