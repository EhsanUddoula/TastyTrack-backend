package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItem;
import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItemId;
import com.example.TastyTrack.TastyTrackbackend.Model.FoodItemModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.FoodItemRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemServImp implements FoodItemService {

    @Autowired
    private FoodItemRepo foodItemRepo;
    @Override
    public FoodItem save(FoodItemModel foodItemModel) {
        FoodItem foodItem= new FoodItem(new FoodItemId(foodItemModel.getItem(),foodItemModel.getRest_id()),foodItemModel.getRest_name(),foodItemModel.getPrice(),foodItemModel.getDescription(),foodItemModel.getImg_url());
        return foodItemRepo.save(foodItem);
    }
}
