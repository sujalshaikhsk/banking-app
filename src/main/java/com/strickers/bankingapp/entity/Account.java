package com.strickers.bankingapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sujal
 *
 */
@Entity
@Table(name = "accounts")
@Setter
@Getter
@SequenceGenerator(name = "accountsequence", initialValue = 100100)
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountsequence")
	private Long accountNumber;
	
	private String accountName;
	private Double balance;
	private String accountType;
	private String ifscCode;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
