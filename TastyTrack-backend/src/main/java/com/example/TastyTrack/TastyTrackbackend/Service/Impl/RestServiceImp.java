package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Model.RestaurantModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.RestaurantRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Restaurant save(RestaurantModel restaurantModel) {
        Restaurant restaurant= new Restaurant(restaurantModel.getRest_Id(),restaurantModel.getName(),restaurantModel.getEmail(),restaurantModel.getImg_url(),passwordEncoder.encode(restaurantModel.getPassword()), restaurantModel.getAddress(), restaurantModel.getDescription(), restaurantModel.getPhone());
        return restaurantRepo.save(restaurant);
    }

    @Override
    public RestaurantModel getRestaurantById(long id) {
        RestaurantModel restaurantModel= new RestaurantModel();
        Restaurant restaurant= new Restaurant();
        restaurant= restaurantRepo.getReferenceById(id);
        BeanUtils.copyProperties(restaurant,restaurantModel);

        return restaurantModel;
    }
}
