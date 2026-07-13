package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
@Data
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double amount;


    private String paymentMethod;


    private String paymentStatus;


    private LocalDateTime paymentDate;



    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;


}