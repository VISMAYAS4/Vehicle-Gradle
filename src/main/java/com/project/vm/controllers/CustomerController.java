package com.project.vm.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.vm.entities.Customer;
import com.project.vm.repositories.ICustomerRepository;
import com.project.vm.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "Customer", tags = { "CustomerAPI" })
public class CustomerController {
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;
	
	
	/**
	 * This method is for adding a customer
	 * 
	 * @param Customer
	 * @return Customer
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	@PostMapping("/customers")
	@Transactional
	@ApiOperation(value = "Add a customer", response = Customer.class)
	public ResponseEntity<Customer> addCustomer(@ApiParam(value = "Customer to be added", required = true) @RequestBody Customer c) {
		Customer customer = customerService.addCustomer(c);
		return new ResponseEntity<>(customer,HttpStatus.CREATED);
	}
	
	
	
	/**
	 * This method is for updating EmailId of a customer
	 * 
	 * @return String
	 * @throws NotFoundException
	 * 
	 */
	@PutMapping("/customers")
	@Transactional
	@ApiOperation(value = "Update a customer", notes = "Provide customer id", response = Customer.class)
	public ResponseEntity<String> updateCustomer(@ApiParam(value = "Customer to be updated", required = true) @RequestBody Customer c) {
		customerService.updateCustomer(c);
		return new ResponseEntity<>("Successfuly updated", HttpStatus.ACCEPTED);
	}
	
	
	
	/**
	 * This method is for deleting a customer by id
	 * 
	 * @return String
	 * @throws NotFoundException
	 * 
	 */
	@DeleteMapping("/customers/{id}")
	@ApiOperation(value = "Delete a customer", notes = "Provide customer id", response = Customer.class)
	public  ResponseEntity<String> removeCustomer(@PathVariable("id") int id) {
		customerService.removeCustomer(id);
		return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
	}
	
	
	
	/**
	 * This method is for getting a list of all customers
	 * 
	 * @return List<Customer>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/customers")
	@ApiOperation(value = "View all customers", response = Customer.class)
	public ResponseEntity<List<Customer>> viewCustomers() {
		List<Customer> customer = customerService.viewCustomers();
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	
	
	/**
	 * This method is for fetching a customer by id
	 * 
	 * @return Customer
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/customers/{id}")
	@ApiOperation(value = "View customer by id", notes = "Provide customer id", response = Customer.class)
	public Customer viewCustomerById(@PathVariable("id") int id) {
		return customerService.viewCustomer(id);
	}
	
	
	
	/**
	 * This method is for getting a list of all customers by vehicle type
	 * 
	 * @return List<Customers>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/customerByVehicleType/{type}")
	@ApiOperation(value = "View customer by vehicle type", notes = "Provide vehicle type", response = Customer.class)
	public ResponseEntity<List<Customer>> viewAllCustomers(@ApiParam(value = "Vehicle type to view customers", required = true) @PathVariable("type") String type) {
		List <Customer> customer = customerService.viewAllCustomer(type);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	
	
	/**
	 * This method is for getting a list of all customers by vehicle location
	 * 
	 * @return List<Customers>
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/customerByVehicleLocation/{loc}")
	@ApiOperation(value = "View customers by vehicle location", notes = "Provide vehicle location", response = Customer.class)
	public ResponseEntity<List<Customer>> viewAllCustomersByLocation(@PathVariable("loc") String loc) {
		List <Customer> customer = customerService.viewAllCustomersByLocation(loc);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
}