package org.example.service;


import org.example.entity.Booking;
import org.example.entity.Vehicle;
import org.example.repository.BookingRepository;
import org.example.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class BookingService {


    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;


    public BookingService(
            BookingRepository bookingRepository,
            VehicleRepository vehicleRepository
    ) {

        this.bookingRepository = bookingRepository;
        this.vehicleRepository = vehicleRepository;

    }



    // Create Booking
    public Booking createBooking(Booking booking) {


        Vehicle vehicle = vehicleRepository
                .findById(booking.getVehicle().getId())
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found")
                );


        if(!vehicle.isAvailable()) {

            throw new RuntimeException("Vehicle is not available");

        }


        long days = ChronoUnit.DAYS.between(
                booking.getStartDate(),
                booking.getEndDate()
        );


        if(days <= 0) {

            days = 1;

        }


        booking.setTotalAmount(
                days * vehicle.getPricePerDay()
        );


        booking.setStatus("PENDING");


        return bookingRepository.save(booking);

    }



    // Get All Bookings
    public List<Booking> getAllBookings(){

        return bookingRepository.findAll();

    }



    // Get Booking By ID
    public Booking getBookingById(Long id){

        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found")
                );

    }



    // Update Booking Status
    public Booking updateStatus(
            Long id,
            String status
    ){

        Booking booking = getBookingById(id);


        booking.setStatus(status);


        return bookingRepository.save(booking);

    }



    // Delete Booking
    public void deleteBooking(Long id){

        bookingRepository.deleteById(id);

    }

}