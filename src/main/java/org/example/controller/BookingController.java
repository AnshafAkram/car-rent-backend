package org.example.controller;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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



    public BookingController(
            BookingService bookingService
    ) {

        this.bookingService = bookingService;

    }




    // =========================
    // CREATE BOOKING
    // =========================

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody Booking booking
    ){

        return ResponseEntity.ok(
                bookingService.createBooking(booking)
        );

    }






    // =========================
    // GET ALL BOOKINGS
    // ADMIN USE
    // =========================

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){

        return ResponseEntity.ok(
                bookingService.getAllBookings()
        );

    }






    // =========================
    // GET BOOKINGS BY USER
    // CUSTOMER DASHBOARD
    // =========================

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(
            @PathVariable Long userId
    ){

        return ResponseEntity.ok(
                bookingService.getBookingsByUser(userId)
        );

    }






    // =========================
    // GET BOOKING BY ID
    // =========================

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                bookingService.getBookingById(id)
        );

    }







    // =========================
    // UPDATE BOOKING STATUS
    // ADMIN USE
    // =========================

    @PutMapping("/{id}/status")
    public ResponseEntity<Booking> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ){

        return ResponseEntity.ok(
                bookingService.updateStatus(id, status)
        );

    }







    // =========================
    // DELETE BOOKING
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long id
    ){

        bookingService.deleteBooking(id);


        return ResponseEntity.ok(
                "Booking deleted successfully"
        );

    }

    @GetMapping("/my")
    public ResponseEntity<List<Booking>> myBookings(
            Authentication authentication
    ){

        return ResponseEntity.ok(
                bookingService.getBookingsOfUser(
                        authentication.getName()
                )
        );

    }


}