package com.revolut.webservice.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserAccount {

	private long userId;
	private BigDecimal amount;

	public UserAccount() {
	}

	public UserAccount(long userId, BigDecimal amount) {
		this.userId = userId;
		this.amount = amount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UserAccount [userId=" + userId + ", amount=" + amount + "]";
	}

}
