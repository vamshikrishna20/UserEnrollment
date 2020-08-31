package com.healthcare.enrollment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.healthcare.enrollment.model.User;
import com.healthcare.enrollment.service.UserService;
import com.healthcare.enrollment.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService UserService; 

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/User/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> Users = UserService.findAllUsers();
		if (Users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(Users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/User/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User User = UserService.findById(id);
		if (User == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(User, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/User/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (UserService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getUserName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getUserName() + " already exist."),HttpStatus.CONFLICT);
		}
		UserService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/User/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/User/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = UserService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentUser.setUserName(user.getUserName());
		currentUser.setActivation(user.isActivation());
		//currentUser.setDob(user.getDob());

		UserService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	/*
	 * @RequestMapping(value = "/User/{id}/{txType}", method = RequestMethod.PUT)
	 * public ResponseEntity<?> updateUserCount(@PathVariable("id") long
	 * id,@PathVariable("txType") String txType) {
	 * logger.info("Updating User with id {}", id);
	 * 
	 * User currentUser = UserService.findById(id); double stock=0;
	 * if("Issue".equals(txType)) { stock =currentUser.getUsersStock() + 1; if(stock
	 * > currentUser.getNoOfUsers()) { logger.error("Out of stock"); return new
	 * ResponseEntity(new CustomErrorType("Out of stock"), HttpStatus.NOT_FOUND); }
	 * }else { stock =currentUser.getUsersStock() - 1; if(stock < 0) {
	 * logger.error("No more items to return"); return new ResponseEntity(new
	 * CustomErrorType("All Users are received"), HttpStatus.NOT_FOUND); } }
	 * currentUser.setPublichDate(new Date()); currentUser.setUsersStock(stock);
	 * UserService.updateUser(currentUser); return new
	 * ResponseEntity<User>(currentUser, HttpStatus.OK); }
	 */

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/User/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User book = UserService.findById(id);
		if (book == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		UserService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/User/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		UserService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}