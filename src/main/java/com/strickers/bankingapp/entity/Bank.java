package com.strickers.bankingapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "banks")
@Setter
@Getter
public class Bank implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String ifscCode;
	private String bankName;
	private String branchName;

}
