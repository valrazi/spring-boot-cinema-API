package com.valrazi.cinemadb.model;

import com.valrazi.cinemadb.utils.Uuid;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "films")
public class Film {
    //TODO FIX ILEGAL ATTEMP FILM CODE

    @Id
    @Column(name = "film_Code", updatable = false)
    private String filmCode = Uuid.uuidGenerator();


    @Column(name = "film_name")
    private String filmName;

    @Column(name = "airing_status")
    private Boolean airingStatus;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt = LocalDate.now();

}