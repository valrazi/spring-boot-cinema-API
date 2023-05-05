package com.valrazi.cinemadb.controller;

import com.valrazi.cinemadb.model.Booking;
import com.valrazi.cinemadb.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @GetMapping()
    public ResponseEntity<Object> getAllBooking(){
        return bookingService.getAllBooking();
    }

    @PostMapping()
    public ResponseEntity<Object> addNewBooking(@RequestBody Booking booking){
        return bookingService.addNewBooking(booking);
    }

    @GetMapping("/detail/{bookingCode}")
    public ResponseEntity<Object> getDetailBooking(@PathVariable String bookingCode){
        return bookingService.getBookingDetail(bookingCode);
    }
}
