package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "bookings")
@Data
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate startDate;


    private LocalDate endDate;


    private Double totalAmount;


    private String status;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


}