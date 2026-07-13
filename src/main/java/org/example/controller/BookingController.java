package org.example.controller;


import org.example.entity.Booking;
import org.example.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {


    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;

    }



    // Create Booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody Booking booking
    ){

        return ResponseEntity.ok(
                bookingService.createBooking(booking)
        );

    }



    // Get All Bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){

        return ResponseEntity.ok(
                bookingService.getAllBookings()
        );

    }



    // Get Booking By ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                bookingService.getBookingById(id)
        );

    }



    // Update Booking Status
    @PutMapping("/{id}/status")
    public ResponseEntity<Booking> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ){

        return ResponseEntity.ok(
                bookingService.updateStatus(id, status)
        );

    }



    // Delete Booking
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long id
    ){

        bookingService.deleteBooking(id);

        return ResponseEntity.ok(
                "Booking deleted successfully"
        );

    }

}