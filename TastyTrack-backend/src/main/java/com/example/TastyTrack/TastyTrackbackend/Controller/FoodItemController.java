package com.example.TastyTrack.TastyTrackbackend.Controller;

import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItem;
import com.example.TastyTrack.TastyTrackbackend.Model.FoodItemModel;
import com.example.TastyTrack.TastyTrackbackend.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food/")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    private FoodItem foodItem;

    @PostMapping("/save")
    public ResponseEntity<String> SaveFood(@RequestBody FoodItemModel foodItemModel){
        foodItem= foodItemService.save(foodItemModel);
        if (foodItem != null) {
            return ResponseEntity.ok("Food Item Added!"); // Return 200 OK with the restaurant data
        } else {
            return ResponseEntity.notFound().build();// Return 404 Not Found if restaurant not found
        }
    }
}
