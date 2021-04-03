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
	void testUpdateUser() {
		User user = new User();
		user.setUsername("Raj");
		user.setEmail("rak@gmail.com");
		User u = userService.updateUser(user);
		System.out.print(u);
	}

//	@Test
	void testDeleteUser() {
		 userService.deleteUser(3L);
	}

//	@Test
	void testValidateUser() {
		 User user = new User();
		 user.setUsername("Raj");
		user.setEmail("rak@gmail.com");
		 User user2 = userService.validateUser(user);
	}

}
