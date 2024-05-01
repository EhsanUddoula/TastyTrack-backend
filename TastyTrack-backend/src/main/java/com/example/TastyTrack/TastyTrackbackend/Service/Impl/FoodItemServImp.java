package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItem;
import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItemId;
import com.example.TastyTrack.TastyTrackbackend.Model.FoodItemModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.FoodItemRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServImp implements FoodItemService {

    @Autowired
    private FoodItemRepo foodItemRepo;
    @Override
    public FoodItem save(FoodItemModel foodItemModel) {
        FoodItem foodItem= new FoodItem(new FoodItemId(foodItemModel.getItem(),foodItemModel.getRest_id()),foodItemModel.getRest_name(),foodItemModel.getPrice(),foodItemModel.getDescription(),foodItemModel.getImg_url());
        return foodItemRepo.save(foodItem);
    }

    @Override
    public List<FoodItemModel> getAllFood() {
        List<FoodItem> foodItemList= foodItemRepo.findAll();
        List<FoodItemModel> foodItems=new ArrayList<>();
        for(FoodItem food : foodItemList){
            FoodItemModel model= new FoodItemModel();
            model.setItem(food.getFoodItemId().getItem());
            model.setRest_id(food.getFoodItemId().getRestaurant_id());
            model.setPrice(food.getPrice());
            model.setDescription(food.getDescription());
            model.setRest_name(food.getRest_name());
            model.setImg_url(food.getImg_url());

            foodItems.add(model);
        }

        return foodItems;
    }

    @Override
    public List<FoodItemModel> getAllFoodByName(String item) {
        List<FoodItem> foodItemList= foodItemRepo.findByFoodItemId_ItemLike("%"+item+"%");
        List<FoodItemModel> foodItems=new ArrayList<>();
        for(FoodItem food : foodItemList){
            FoodItemModel model= new FoodItemModel();
            model.setItem(food.getFoodItemId().getItem());
            model.setRest_id(food.getFoodItemId().getRestaurant_id());
            model.setPrice(food.getPrice());
            model.setDescription(food.getDescription());
            model.setRest_name(food.getRest_name());
            model.setImg_url(food.getImg_url());

            foodItems.add(model);
        }

        return foodItems;
    }

    @Override
    public FoodItem updateFood(FoodItemId foodId, FoodItemModel model) {
        // Step 1: Retrieve the existing FoodItem
        Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(foodId);
        if (optionalFoodItem.isEmpty()) {
            // Handle the case where the FoodItem with the given ID is not found
            return null;
        }
        FoodItem existingFoodItem = optionalFoodItem.get();

        // Step 2: Update relevant fields from the FoodItemModel
        existingFoodItem.setRest_name(model.getRest_name());
        existingFoodItem.setPrice(model.getPrice());
        existingFoodItem.setDescription(model.getDescription());
        existingFoodItem.setImg_url(model.getImg_url());

        // Step 3: Save the updated FoodItem
        return foodItemRepo.save(existingFoodItem);
    }

    @Override
    public void deleteFood(FoodItemId food) {
        Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(food);
        if (optionalFoodItem.isEmpty()) {
            // Handle the case where the FoodItem with the given ID is not found
            return;
        }

        foodItemRepo.deleteById(food);
    }


}
