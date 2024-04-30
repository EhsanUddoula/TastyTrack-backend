package com.example.TastyTrack.TastyTrackbackend.Repository;

import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Restaurant findByEmail(String email);

}
