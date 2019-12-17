package com.strickers.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.strickers.bankingapp.entity.FavoritePayee;

@Repository
public interface FavoritePayeeRepository extends JpaRepository<FavoritePayee, Integer> {

	FavoritePayee findByPayeeId(Integer payeeId);

	@Query("select p from FavoritePayee p where p.customer.customerId=:customerId and p.status=:status")
	List<FavoritePayee> getPayeesByCustomerIdAndStatus(@Param("customerId") Integer customerId,
			@Param("status") String status);

}
