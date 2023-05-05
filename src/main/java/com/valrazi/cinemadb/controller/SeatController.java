package com.valrazi.cinemadb.controller;

import com.valrazi.cinemadb.model.Seat;
import com.valrazi.cinemadb.service.SeatReservedService;
import com.valrazi.cinemadb.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatService seatService;

    @Autowired
    SeatReservedService srService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllSeat(){
        return seatService.getAllSeat();
    }

    @PostMapping("/initiate")
    public ResponseEntity<Object> addSeat(){
        return  seatService.addSeats();
    }

    @GetMapping("/available/{scheduleId}")
    public ResponseEntity<Object> getAvailableSeats(@PathVariable String scheduleId){
        return  seatService.getAvailableSeats(scheduleId);
    }

    @GetMapping("/reserved")
    public ResponseEntity<Object> getReservedSeats(){
        return srService.getSeatReserved();
    }

}
