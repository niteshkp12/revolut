package com.revolut.webservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.revolut.webservice.db.DB;
import com.revolut.webservice.exception.DataNotFoundException;
import com.revolut.webservice.model.Account;
import com.revolut.webservice.model.User;


public class UserService {
	private Map<Long, User> users = DB.getUsers();

	public UserService() {
		users.put(1L, new User(1, "Nitesh", "Kumar", new Account(0001, new BigDecimal(100))));
		users.put(2L, new User(2, "Nits", "Kumar", new Account(0002, new BigDecimal(500))));
	}

	public List<User> getUsers() {
		List<User> userss = new ArrayList<User>(users.values()); 
		return userss;
	}

	public User getUser(Long userId) {
		User user = users.get(userId);
		if (userId == null) {
			throw new DataNotFoundException("User with id " + userId + " not found");
		}
		return user;
	}

	public User addUser(User user) {
		user.setUserId(users.size() + 1);
		users.put(user.getUserId(), user);
		return user;
	}

	public User deleteUser(Long userId) {
		return users.remove(userId);
	}

	public User update(User user) {
		users.put(user.getUserId(), user);
		return user;
	}

}
