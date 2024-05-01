package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Model.RestaurantModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RestaurantService {
    Restaurant save(RestaurantModel restaurantModel);
    RestaurantModel getRestaurantById(long id);
    List<RestaurantModel> getRestaurantByName(String name);

    Restaurant updateRestaurant(long id, RestaurantModel model);
}
