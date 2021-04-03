package com.project.vm.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vm.controllers.AuthController;
import com.project.vm.entities.User;
import com.project.vm.exceptions.NotAUserException;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.repositories.IUserRepository;
import com.project.vm.serviceInterfaces.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	AuthController authController;

	@Override
	@Transactional
	public User updateUser(User user) {
		Optional<User> u = userRepository.findByUsername(user.getUsername());
		if(u.isEmpty()) {
			throw new NotFoundException("User " +user.getId()+" not found");
		}
		User user2 = u.get();
		user2.setEmail(user.getEmail());
		return user;
	}

	@Override
	public void deleteUser(Long i) {
		Optional<User> user = userRepository.findById(i);
		if(user.isEmpty()){
			throw new NotFoundException("User not found with id : "+i);
		}
		User u = user.get();
		userRepository.delete(u);

	}

	@Override
	public User validateUser(User user) {
			User u = userRepository.findUserByUsernameAndEmail(user.getUsername(), user.getEmail());
			if(u == null) 
			{
				throw new NotAUserException("User name : "+user.getUsername()+" or the User email : "+user.getEmail()+" is invalid");
			}
		return user;
	}

}