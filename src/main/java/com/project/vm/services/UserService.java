package com.project.vm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vm.entities.User;
import com.project.vm.exceptions.AlreadyExistsException;
import com.project.vm.exceptions.NotAUserException;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.repositories.IUserRepository;
import com.project.vm.serviceInterfaces.IUserService;



@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepository userRepository;

	@Override
	public User addUser(User user) {
		User u = userRepository.findByUserName(user.getUserName());
		if(u == null) {
			userRepository.save(user);
		}else {
			throw new AlreadyExistsException("User " +user.getUserId()+" already exixts");
		}
		return user;
	}

	@Override
	public void deleteUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()){
			throw new NotFoundException("User not found with id : "+id);
		}
		User u = user.get();
		userRepository.delete(u);

	}

	@Override
	public User validateUser(User user) {
			User u = userRepository.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
			if(u == null) 
			{
				throw new NotAUserException("User name : "+user.getUserName()+" or the User password : "+user.getPassword()+" is invalid");
			}
		return user;
	}

}
