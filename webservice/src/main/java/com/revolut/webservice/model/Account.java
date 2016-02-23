package com.revolut.webservice.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Account POJO
 * 
 * @author nitesh
 *
 */
@XmlRootElement
public class Account {
	private int accountNumber;
	private BigDecimal amount;

	public Account() {
	}

	public Account(int accountNumber, BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}

}
