package com.valrazi.cinemadb.service;

import com.valrazi.cinemadb.model.Booking;
import com.valrazi.cinemadb.model.SeatReserved;
import com.valrazi.cinemadb.repository.BookingRepository;
import com.valrazi.cinemadb.repository.SeatReservedRepository;
import com.valrazi.cinemadb.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SeatReservedRepository seatReservedRepository;

    //get all booking
    public ResponseEntity<Object> getAllBooking(){
        try{
            return ResponseHandler.generateResponse("data", HttpStatus.OK, bookingRepository.findAll());
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    //add booking
    public  ResponseEntity<Object> addNewBooking(Booking booking){
        try{
            SeatReserved newSeatReserve = new SeatReserved();
            newSeatReserve.setSeat(booking.getSeat());
            newSeatReserve.setSchedules(booking.getSchedules());
            seatReservedRepository.save(newSeatReserve);
            return ResponseHandler.generateResponse("data", HttpStatus.OK, bookingRepository.save(booking));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
