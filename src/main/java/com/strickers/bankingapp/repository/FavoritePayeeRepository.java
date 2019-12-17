package com.strickers.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.strickers.bankingapp.entity.FavoritePayee;

@Repository
public interface FavoritePayeeRepository extends JpaRepository<FavoritePayee, Integer> {

	/**
	 * @author Sujal
	 * @description This method is used fetch Payees details based on customer id and status
	 * @param customerId is login user id
	 * @return PayeeResponseDto is the list of Favorite Payees and response code
	 */
	@Query("select p from FavoritePayee p where p.customer.customerId=:customerId and p.status=:status")
	List<FavoritePayee> getPayeesByCustomerIdAndStatus(@Param("customerId") Integer customerId, @Param("status") String status);

}
