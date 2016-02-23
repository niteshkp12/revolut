package com.revolut.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User POJO
 * @author nitesh
 *
 */
@XmlRootElement
public class User {

	private long userId;
	private String firstName;
	private String lastName;
	private Account acount;
	private Link link;

	public User() {

	}

	public User(long userId, String firstName, String lastName, Account acount) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.acount = acount;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Account getAcount() {
		return acount;
	}

	public void setAcount(Account acount) {
		this.acount = acount;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", acount=" + acount
				+ "]";
	}

}
