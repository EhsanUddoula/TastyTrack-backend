package com.example.TastyTrack.TastyTrackbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationModel {
    private long reservationId;
    private long userId;
    private String email;
    private Date date;
    private Time time;
    private int no_of_people;
    private String name;
}
