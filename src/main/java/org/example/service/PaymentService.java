package org.example.service;


import org.example.entity.Booking;
import org.example.entity.Payment;
import org.example.repository.BookingRepository;
import org.example.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PaymentService {


    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;


    public PaymentService(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository
    ) {

        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;

    }



    // Create Payment
    public Payment createPayment(Payment payment) {


        Booking booking = bookingRepository
                .findById(payment.getBooking().getId())
                .orElseThrow(() ->
                        new RuntimeException("Booking not found")
                );


        payment.setAmount(
                booking.getTotalAmount()
        );


        payment.setPaymentStatus(
                "COMPLETED"
        );


        payment.setPaymentDate(
                LocalDateTime.now()
        );


        Payment savedPayment =
                paymentRepository.save(payment);



        // Confirm booking after payment

        booking.setStatus("CONFIRMED");

        bookingRepository.save(booking);



        return savedPayment;

    }



    // Get All Payments
    public List<Payment> getAllPayments(){

        return paymentRepository.findAll();

    }



    // Get Payment By ID
    public Payment getPaymentById(Long id){

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found")
                );

    }



    // Delete Payment
    public void deletePayment(Long id){

        paymentRepository.deleteById(id);

    }


}