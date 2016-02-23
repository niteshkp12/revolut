package com.revolut.webservice.resource;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.revolut.webservice.exception.DataNotFoundException;
import com.revolut.webservice.exception.InSufficientAmountException;
import com.revolut.webservice.model.Account;
import com.revolut.webservice.model.Link;
import com.revolut.webservice.model.User;
import com.revolut.webservice.model.UserAccount;
import com.revolut.webservice.service.UserService;

@Path("/users")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class UserResource {
	UserService userService = new UserService();

	/**
	 * Get the list of all users
	 * 
	 * @return
	 */
	@GET
	public List<User> getUsers() {
		System.out.println("UserResource.getUsers()");
		return userService.getUsers();
	}

	/**
	 * Get a user
	 * 
	 * @param userId
	 * @param uriInfo
	 * @return
	 */
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long userId, @Context UriInfo uriInfo) {
		User user = userService.getUser(userId);
		if (user == null){
			throw new DataNotFoundException("Requested user not found in the system: " + userId);
		}
		Link link = new Link(getUriForSelf(uriInfo, user), "self");
		user.setLink(link);
		return user;
	}

	/**
	 * It credits the user passed in the url with the amount passed in the
	 * message body. Balance of the user passed in the body is reduced by that
	 * amount
	 * 
	 * @param userId
	 * @param userAccount
	 * @return
	 */
	@PUT
	@Path("/{userId}/credit")
	public User creditAccount(@PathParam("userId") long userId, UserAccount userAccount) {
		System.out.println("UserId: " + userId);
		User user = userService.getUser(userId);
		if (user == null) {
			throw new DataNotFoundException("Requested user not found in the system: " + userId);
		} else if (userAccount != null) {
			long debitId = userAccount.getUserId();
			BigDecimal debitAmount = userAccount.getAmount();
			User userTobeDebited = userService.getUser(debitId);
			if (userTobeDebited == null) {
				throw new DataNotFoundException("User to be debited not found in the system: " + userId);
			} else if (debitAmount.compareTo(userTobeDebited.getAcount().getAmount()) > 0) {
				throw new InSufficientAmountException("Insufficient balance to debit");
			} else {
				BigDecimal totalAmountafterAddition = user.getAcount().getAmount().add(debitAmount);
				Account account = user.getAcount();
				account.setAmount(totalAmountafterAddition);

				user.setAcount(account);
				userService.update(user);

				System.out.println("User1 after updating: " + user);

				BigDecimal totalAmountAfterdeduction = userTobeDebited.getAcount().getAmount().subtract(debitAmount);
				Account accountTobeDebit = userTobeDebited.getAcount();
				accountTobeDebit.setAmount(totalAmountAfterdeduction);

				userTobeDebited.setAcount(accountTobeDebit);
				userService.update(userTobeDebited);
				System.out.println("User1 after updating: " + userTobeDebited);

			}
		}
		return user;
	}

	@PUT
	@Path("/{userId}/debit")
	public User debitAccount(@PathParam("userId") long userId, UserAccount userAccount) {
		System.out.println("UserId: " + userId);
		User user = userService.getUser(userId);
		if (user == null) {
			throw new DataNotFoundException("Requested user not found in the system: " + userId);
		} else if (userAccount != null) {
			long debitId = userAccount.getUserId();
			BigDecimal debitAmount = userAccount.getAmount();
			User userTobeDebited = userService.getUser(debitId);
			if (userTobeDebited == null) {
				throw new DataNotFoundException("User to be debited not found in the system: " + userId);
			} else if (debitAmount.compareTo(userTobeDebited.getAcount().getAmount()) > 0) {
				throw new InSufficientAmountException("Insufficient balance to debit");
			} else {
				BigDecimal totalAmountafterAddition = user.getAcount().getAmount().add(debitAmount);
				Account account = user.getAcount();
				account.setAmount(totalAmountafterAddition);
				user.setAcount(account);
				userService.update(user);
				System.out.println("User1 after updating: " + user);

				BigDecimal totalAmountAfterdeduction = userTobeDebited.getAcount().getAmount().subtract(debitAmount);
				Account accountTobeDebit = user.getAcount();
				accountTobeDebit.setAmount(totalAmountAfterdeduction);
				userTobeDebited.setAcount(accountTobeDebit);
				userService.update(userTobeDebited);
				System.out.println("User2 after updating: " + userTobeDebited);
			}
		}
		return user;
	}

	/**
	 * Add a user
	 * 
	 * @param user
	 * @param uriInfo
	 * @return
	 */
	@POST
	public Response addUser(User user, @Context UriInfo uriInfo) {
		System.out.println("UserResource.addUser()");
		User newUser = userService.addUser(user);
		String newId = String.valueOf(newUser.getUserId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newUser).build();
	}

	/**
	 * Update the user detail
	 * 
	 * @param userId
	 * @param user
	 * @param uriInfo
	 * @return
	 */
	@PUT
	@Path("/{userId}")
	public User updateUser(@PathParam("userId") long userId, User user, @Context UriInfo uriInfo) {
		System.out.println("UserId: " + userId);
		User validateUser = userService.getUser(userId);
		if (validateUser == null) {
			throw new DataNotFoundException("Requested user not found in the system: " + userId);
		} else {
			user.setUserId(userId);
			user = userService.update(user);
		}
		return user;
	}

	/**
	 * Delete the user
	 * 
	 * @param userId
	 * @return
	 */
	@DELETE
	@Path("/{userId}")
	public User deleteUser(@PathParam("userId") Long userId) {
		return userService.deleteUser(userId);
	}

	private String getUriForSelf(UriInfo uriInfo, User user) {
		String uri = uriInfo.getBaseUriBuilder().path(UserResource.class).path(Long.toString(user.getUserId())).build()
				.toString();
		return uri;
	}

}
