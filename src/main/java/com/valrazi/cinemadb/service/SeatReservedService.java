package com.valrazi.cinemadb.service;

import com.valrazi.cinemadb.repository.SeatReservedRepository;
import com.valrazi.cinemadb.response.ResponseHandler;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SeatReservedService {
    @Autowired
    SeatReservedRepository repo;
    public ResponseEntity<Object> getSeatReserved(){
        try{
            return ResponseHandler.generateResponse("success", HttpStatus.OK, repo.findAll());
        }catch (Exception e){
            return ResponseHandler.generateResponse("failed", HttpStatus.BAD_REQUEST, null);
        }
    }
}
