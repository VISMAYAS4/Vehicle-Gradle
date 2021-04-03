package com.project.vm.serviceInterfaces;

import java.util.List;

import com.project.vm.entities.Driver;
import com.project.vm.entities.Vehicle;

public interface IVehicleService {
	public Vehicle addVehicle(Vehicle v);
	public Vehicle updateVehicle(Vehicle v);
	public Vehicle cancelVehicle(int id);
	public Vehicle viewVehicle(int id);
	public List<Vehicle> viewAllVehicle(Driver driver) ;
	List<Vehicle> viewAllVehicles();
}
