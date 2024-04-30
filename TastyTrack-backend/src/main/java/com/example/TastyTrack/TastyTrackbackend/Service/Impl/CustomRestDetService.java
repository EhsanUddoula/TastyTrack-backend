package com.example.TastyTrack.TastyTrackbackend.Service.Impl;


import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomRestDetService implements UserDetailsService {

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Restaurant restaurant= restaurantRepo.findByEmail(username);

        if(restaurant == null){
            throw new UsernameNotFoundException("Restaurant not found!");
        }

        return new CustomRestDet(restaurant);
    }
}
