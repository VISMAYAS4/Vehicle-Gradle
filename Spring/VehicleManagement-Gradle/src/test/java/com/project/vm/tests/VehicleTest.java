package com.project.vm.tests;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;
import com.project.vm.services.VehicleService;

@SpringBootTest
class VehicleTest {
	
	Driver driver;
	Vehicle vehicle;
	
	@Autowired
	VehicleService vehicleService;
	
//	@Test
	void testAddVehicle() {
		Driver driver = new Driver(34,"Raj","Sharma","#45,Bangalore","12345672","raj@gmail.com","FG2303");
		Vehicle vehicle = new Vehicle("KA05827", driver, "Car", "SUV", "Bangalore", "---", 4, 35.00, 1000);
		vehicleService.addVehicle(vehicle);
	}

//	@Test
	void testUpdateVehicle() {
		vehicleService.updateVehicle(vehicle);
	}

//	@Test
	void testCancelVehicle() {
		vehicleService.cancelVehicle(46);
	}

//	@Test
	void testViewVehicle() {
		Vehicle vehicle = vehicleService.viewVehicle(33);
		System.out.println(vehicle);
	}

//	@Test
	void testViewAllVehicle() {
		Driver driver = new Driver(34,"Raj","Sharma","#45,Bangalore","12345672","raj@gmail.com","FG2303");
		List<Vehicle> vehicles = vehicleService.viewAllVehicle(driver);
		System.out.println(vehicles);
	}

}
