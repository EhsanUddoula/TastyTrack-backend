package com.example.TastyTrack.TastyTrackbackend.Repository;

import com.example.TastyTrack.TastyTrackbackend.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRestId(Long Id);
}
