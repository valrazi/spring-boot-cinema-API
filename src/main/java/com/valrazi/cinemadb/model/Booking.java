package com.valrazi.cinemadb.model;

import com.valrazi.cinemadb.utils.Uuid;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "booking_code", updatable = false, insertable = false)
    private String bookingCode = Uuid.uuidGenerator();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "seat_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Seat seat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "email", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Schedules schedules;


}
