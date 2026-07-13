package org.example.controller;


import org.example.entity.Vehicle;
import org.example.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {


    private final VehicleService vehicleService;


    public VehicleController(VehicleService vehicleService) {

        this.vehicleService = vehicleService;

    }



    // Add Vehicle
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(
            @RequestBody Vehicle vehicle
    ) {

        return ResponseEntity.ok(
                vehicleService.addVehicle(vehicle)
        );

    }



    // Get All Vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        return ResponseEntity.ok(
                vehicleService.getAllVehicles()
        );

    }



    // Get Vehicle By ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                vehicleService.getVehicleById(id)
        );

    }



    // Update Vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable Long id,
            @RequestBody Vehicle vehicle
    ) {

        return ResponseEntity.ok(
                vehicleService.updateVehicle(id, vehicle)
        );

    }



    // Delete Vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(
            @PathVariable Long id
    ) {

        vehicleService.deleteVehicle(id);

        return ResponseEntity.ok(
                "Vehicle deleted successfully"
        );

    }

}