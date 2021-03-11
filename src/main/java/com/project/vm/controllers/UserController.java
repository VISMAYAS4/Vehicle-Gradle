package com.project.vm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.User;
import com.project.vm.services.UserService;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/users") 
	public ResponseEntity<User> addUser(@RequestBody User u) 
	{
		User user = userService.addUser(u); 
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}


	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) 
	{
		userService.deleteUser(id); 
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<User> validateUser(@RequestBody User user){
		User u = userService.validateUser(user); 
		return new ResponseEntity<>(u,HttpStatus.OK);
	}

}

