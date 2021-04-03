package com.project.vm.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.Customer;
import com.project.vm.services.CustomerService;

@SpringBootTest
class CustomerTest {
	
	Customer customer;
	
	@Autowired
	CustomerService customerService;
	
//	@Test
	void testAddCustomer() {
		Customer customer = new Customer("Cust", "Test", "cust@gmail.com", "9874561230", "Delhi");
		Customer c = customerService.addCustomer(customer);
		System.out.println(c);
	}

//	@Test
	void testRemoveCustomer() {
		customerService.removeCustomer(32);
	}

//	@Test
	void testUpdateCustomer() {
		Customer customer = new Customer(40,"Cust", "Test", "customer@gmail.com", "9874561230", "Delhi");
		customerService.updateCustomer(customer);
	}

//	@Test
	void testViewCustomer() {
		Customer customer = customerService.viewCustomer(40);
		System.out.println(customer);
	}

//	@Test
	void testViewAllCustomer() {
		List<Customer> customers = customerService.viewAllCustomer("Car");
		System.out.println(customers);
	}

//	@Test
	void testViewAllCustomersByLocation() {
		List<Customer> customers = customerService.viewAllCustomersByLocation("Delhi");
		System.out.println(customers);
	}

//	@Test
	void testViewCustomers() {
		List<Customer> customers = customerService.viewCustomers();
		System.out.println(customers);
		assertEquals(4, customers.size());
	}


}
