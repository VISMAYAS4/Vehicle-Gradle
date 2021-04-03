package com.project.vm.controllers;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.User;
import com.project.vm.exceptions.NotAUserException;
import com.project.vm.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(value = "User", tags = { "UserAPI" })
@RequestMapping(path = "/api/v1")
public class UserController {

	static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	/**
	 * This method is for updating a user by Response Body of User
	 * @return String
	 * @throws NotFoundException
	 * 
	 */
	@PutMapping("/users") 
	@ApiOperation(value = "Updte User email by the response body", notes = "Provide username and new email", response = User.class)
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	@Transactional
	public ResponseEntity<User> updateUser(@RequestBody User u) 
	{
		User user = userService.updateUser(u); 
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}

	/**
	 * This method is for deleting a user by id
	 * 
	 * @return String
	 * @throws NotFoundException
	 * 
	 */
	@ApiOperation(value = "Deleting a user by id", notes = "Provide id", response = User.class)
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@ApiParam(value = "Users to be deleted", required = true) @PathVariable("id") Long id) 
	{
		userService.deleteUser(id); 
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}

	/**
	 * This method is for adding a user by Response Body of User
	 * 
	 * @return String
	 * @throws NotAUserException
	 * 
	 */

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "Validating a user by user details", notes = "Provide user details", response = User.class)
	public ResponseEntity<User> validateUser(@ApiParam(value = "Users to be validated", required = true) @RequestBody User user){
		User u = userService.validateUser(user); 
		return new ResponseEntity<>(u,HttpStatus.OK);
	}

}
