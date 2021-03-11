package com.project.vm.serviceInterfaces;

import com.project.vm.entities.User;

public interface IUserService {
	public User addUser(User user);
	public void deleteUser(int id);
	public User validateUser(User user);	
	
}
