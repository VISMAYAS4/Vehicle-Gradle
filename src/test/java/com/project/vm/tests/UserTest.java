package com.project.vm.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.User;
import com.project.vm.services.UserService;

@SpringBootTest
class UserTest {
	User user;
	
	@Autowired
	UserService userService;
	
//	@Test
	void testAddUser() {
		User user = new User("testUser","pwd", "Customer");
		User u = userService.addUser(user);
		System.out.print(u);
	}

//	@Test
	void testDeleteUser() {
		 userService.deleteUser(3);
	}

//	@Test
	void testValidateUser() {
		 User user = new User("testUser","pwd", "Customer");
		 User user2 = userService.validateUser(user);
	}

}
