package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItem;
import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItemId;
import com.example.TastyTrack.TastyTrackbackend.Model.FoodItemModel;

import java.util.List;

public interface FoodItemService {
    FoodItem save(FoodItemModel foodItemModel);
    List<FoodItemModel> getAllFood();

    List<FoodItemModel> getAllFoodByName(String item);

    FoodItem updateFood(FoodItemId food, FoodItemModel model);

    void deleteFood(FoodItemId food);
}
