package com.valrazi.cinemadb.controller;

import com.valrazi.cinemadb.model.Film;
import com.valrazi.cinemadb.model.Schedules;
import com.valrazi.cinemadb.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    FilmService service;



    @PostMapping
    public ResponseEntity<Object> addNewFilm(@RequestBody Film newFilm){
        return service.addFilm(newFilm);
    }
    @DeleteMapping("/{uname}")
    public ResponseEntity<Object> removeFilm(@PathVariable String uname){
        return service.deleteFilm(uname);
    }
    @PatchMapping("/{uname}")
    public ResponseEntity<Object> updateFilm(@PathVariable String uname, @RequestBody Film film){
        return service.updateFilm(uname, film);
    }

    @GetMapping("/nowshow")
    public ResponseEntity<Object> getFilmNowShow(){
        return service.getFilmNowShowing();
    }

    @GetMapping("/comingsoon")
    public ResponseEntity<Object> getComingSoon(){
        return service.getComingSoonFilm();
    }


    @GetMapping("/schedules/{filmCode}")
    public ResponseEntity<Object> getAllSchedule(@PathVariable String filmCode){
        return service.getBySchedules(filmCode);
    }
}

