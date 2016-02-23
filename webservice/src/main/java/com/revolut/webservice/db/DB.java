package com.revolut.webservice.db;

import java.util.HashMap;
import java.util.Map;

import com.revolut.webservice.model.Account;
import com.revolut.webservice.model.User;

/**
 * This class act as Database class
 * 
 * @author nitesh
 *
 */
public class DB {

	private static Map<Long, User> users = new HashMap();
	private static Map<Long, Account> accounts = new HashMap();

	public static Map<Long, User> getUsers() {
		return users;
	}

	public static Map<Long, Account> getAccounts() {
		return accounts;
	}

}
