package com.valrazi.cinemadb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valrazi.cinemadb.utils.Uuid;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Table(name = "schedules")
public class Schedules {
    @Id
    @Column(name = "schedule_id", updatable = false)
    private String scheduleId = Uuid.uuidGenerator();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "film_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Film film;

    @Column(name = "showing_date")
    private LocalDate showingDate;
    @Column(name = "time_start")
    private LocalTime timeStart;
    @Column(name = "time_end")
    private LocalTime timeEnd;

    @Column(name = "ticket_price")
    private int ticketPrice;
}
