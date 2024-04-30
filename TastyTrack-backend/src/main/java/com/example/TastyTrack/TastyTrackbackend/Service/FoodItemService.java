package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItem;
import com.example.TastyTrack.TastyTrackbackend.Model.FoodItemModel;

public interface FoodItemService {
    FoodItem save(FoodItemModel foodItemModel);
    //FoodItemModel getFoodByName(String name);
}
