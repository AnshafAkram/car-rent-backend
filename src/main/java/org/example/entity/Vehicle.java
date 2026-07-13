package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String brand;


    private String model;


    private String vehicleNumber;


    private String type;


    private String fuelType;


    private Integer seats;


    private Double pricePerDay;


    private String imageUrl;


    private boolean available = true;

}