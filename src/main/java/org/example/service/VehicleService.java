package org.example.service;


import org.example.entity.Vehicle;
import org.example.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VehicleService {


    private final VehicleRepository vehicleRepository;


    public VehicleService(VehicleRepository vehicleRepository) {

        this.vehicleRepository = vehicleRepository;

    }


    // Add Vehicle
    public Vehicle addVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);

    }



    // Get All Vehicles
    public List<Vehicle> getAllVehicles() {

        return vehicleRepository.findAll();

    }



    // Get Vehicle By ID
    public Vehicle getVehicleById(Long id) {

        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found")
                );

    }



    // Update Vehicle
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {


        Vehicle vehicle = getVehicleById(id);


        vehicle.setBrand(vehicleDetails.getBrand());

        vehicle.setModel(vehicleDetails.getModel());

        vehicle.setVehicleNumber(vehicleDetails.getVehicleNumber());

        vehicle.setType(vehicleDetails.getType());

        vehicle.setFuelType(vehicleDetails.getFuelType());

        vehicle.setSeats(vehicleDetails.getSeats());

        vehicle.setPricePerDay(vehicleDetails.getPricePerDay());

        vehicle.setImageUrl(vehicleDetails.getImageUrl());

        vehicle.setAvailable(vehicleDetails.isAvailable());


        return vehicleRepository.save(vehicle);

    }



    // Delete Vehicle
    public void deleteVehicle(Long id) {


        Vehicle vehicle = getVehicleById(id);


        vehicleRepository.delete(vehicle);

    }

}