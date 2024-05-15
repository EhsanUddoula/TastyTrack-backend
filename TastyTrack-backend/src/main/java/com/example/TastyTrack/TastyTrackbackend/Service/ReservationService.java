package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.Reservation;
import com.example.TastyTrack.TastyTrackbackend.Model.ReservationModel;

import java.util.List;

public interface ReservationService {
    Reservation save(ReservationModel reservationModel);
    List<ReservationModel> findByRestId(Long restId);

    List<ReservationModel> findAllReservation();
}
