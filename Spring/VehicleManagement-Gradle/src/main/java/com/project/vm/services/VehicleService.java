package com.project.vm.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;
import com.project.vm.exceptions.NotFoundException;
import com.project.vm.repositories.IDriverRepository;
import com.project.vm.repositories.IVehicleRepository;
import com.project.vm.serviceInterfaces.IVehicleService;



@Service
public class VehicleService implements IVehicleService{

	@Autowired
	IVehicleRepository vehicleRepository;
	
	@Autowired
	IDriverRepository driverRepository;
	
	@Override
	@Transactional
	public Vehicle addVehicle(Vehicle v) {
		try {
			Driver driver = driverRepository.findByFirstNameAndLastName(v.getDriver().getFirstName(),v.getDriver().getLastName());
			if(driver != null) {
				v.setDriver(driver);
			}
		}catch (NotFoundException e) {
			e.getMessage();
		}	
		return vehicleRepository.save(v);
	}

	@Override
	@Transactional
	public Vehicle updateVehicle(Vehicle vehicle) {
		Vehicle v = viewVehicle(vehicle.getVehicleId());
		if(v == null) {
			throw new NotFoundException("Vehicle with id " +vehicle.getVehicleId() +" not found");
		}
		v.setFixedCharges(vehicle.getFixedCharges());
		return v;
	}

	@Override
	public Vehicle cancelVehicle(int id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		Vehicle v = null;
		if(vehicle.isPresent()){
			v = vehicle.get(); 
			vehicleRepository.delete(v);
		} else {
			throw new NotFoundException("Vehicle with id " +id +" not found");
		}
		return v;
	}

	@Override
	public Vehicle viewVehicle(int  id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		if(vehicle.isEmpty()){
			throw new NotFoundException("Vehicle with id " +id +" not found");
 		}
		return vehicle.get();
	}

	@Override
	public List<Vehicle> viewAllVehicle(Driver driver) {
		List<Vehicle> vehicles = vehicleRepository.findVehicleByDriver(driver);
		if(vehicles.isEmpty()) {
			throw new NotFoundException("Vehicle not found");
		}
		return vehicles;
	}

	@Override
	public List<Vehicle> viewAllVehicles() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		if(vehicles.isEmpty()) {
			throw new NotFoundException("No bookings found");
		}
		return vehicles;
	}

}
