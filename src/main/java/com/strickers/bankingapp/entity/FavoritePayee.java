package com.strickers.bankingapp.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * FavoritePayee is the entity class
 * 
 * @author Sujal
 * @description This class will maintain the favorite payee details
 */

@Entity
@Table(name = "favorite_payees")
@Getter
@Setter
public class FavoritePayee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer payeeId;

	private Long accountNumber;
	private String favoriteName;
	
	@OneToOne
	@JoinColumn(name ="customer_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name ="ifsc_code")
	private Bank bank;
	
	private String status;
	private LocalDate createdDate;
}
