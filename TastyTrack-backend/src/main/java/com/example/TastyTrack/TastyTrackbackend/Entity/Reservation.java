package com.example.TastyTrack.TastyTrackbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name="Reservation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;
    private long userId;
    private long restId;
    private String email;
    private Date date;
    private Time time;
    private int no_of_people;
    private String name;
}


