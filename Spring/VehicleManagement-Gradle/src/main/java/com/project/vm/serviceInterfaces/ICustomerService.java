package com.project.vm.serviceInterfaces;

import java.util.List;

import com.project.vm.entities.Customer;


public interface ICustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(int id);
	public Customer viewCustomer(int id);
	public Customer updateCustomer(Customer c);
	public List<Customer> viewAllCustomer(String vtype);
	public List<Customer> viewAllCustomersByLocation(String location);
}
