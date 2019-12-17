package com.strickers.bankingapp.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "favorite_payees")
@Getter
@Setter
public class FavoritePayee implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer payeeId;

	private Long accountNumber;
	
	@OneToOne
	@JoinColumn(name ="customer_id")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "ifsc_code")
	private Bank bank;
	
	private String status;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	private String favoriteName;


}
