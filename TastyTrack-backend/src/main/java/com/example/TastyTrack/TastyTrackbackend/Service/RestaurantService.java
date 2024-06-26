package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Model.RestModelAddress;
import com.example.TastyTrack.TastyTrackbackend.Model.RestaurantModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RestaurantService {
    Restaurant save(RestaurantModel restaurantModel);
    RestaurantModel getRestaurantById(long id);
    List<RestaurantModel> getRestaurantByName(String name);

    List<RestaurantModel> getAllRestaurant();

    Restaurant updateRestaurant(long id, RestaurantModel model);

    List<RestModelAddress> getRestaurantByAddress(String address);

    Restaurant findEmail(String email);
}
