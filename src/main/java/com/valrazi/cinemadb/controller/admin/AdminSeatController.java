package com.valrazi.cinemadb.controller.admin;

import com.valrazi.cinemadb.service.SeatReservedService;
import com.valrazi.cinemadb.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class AdminSeatController {
    @Autowired
    SeatService seatService;

    @Autowired
    SeatReservedService srService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllSeat(){
        return seatService.getAllSeat();
    }

    @PostMapping("/initiate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> addSeat(){
        return  seatService.addSeats();
    }


    @GetMapping("/available/{scheduleId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getAvailableSeats(@PathVariable String scheduleId){
        return  seatService.getAvailableSeats(scheduleId);
    }


}
