package com.example.TastyTrack.TastyTrackbackend.Repository;

import com.example.TastyTrack.TastyTrackbackend.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRestId(Long Id);
}
