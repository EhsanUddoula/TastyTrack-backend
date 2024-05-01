package com.example.TastyTrack.TastyTrackbackend.Controller;


import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Model.RestaurantModel;
import com.example.TastyTrack.TastyTrackbackend.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant/")
public class RestaurantController {

    @Autowired(required = true)
    private RestaurantService restaurantService;
    private Restaurant restaurant;


    @PostMapping("/register")
    public  String SaveRest(@RequestBody RestaurantModel restaurantModel, Model model){
        restaurant=restaurantService.save(restaurantModel);
        model.addAttribute("message", "Registration Successful...");
        return "Done";
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable long id) {
        // Retrieve the restaurant by ID from the service or repository
        RestaurantModel restaurant = restaurantService.getRestaurantById(id);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant); // Return 200 OK with the restaurant data
        } else {
            return ResponseEntity.notFound().build();// Return 404 Not Found if restaurant not found
        }
    }

    @GetMapping("/search/{name}")
    public  ResponseEntity<List<RestaurantModel>> searchRestaurant(@PathVariable String name){
        List<RestaurantModel> restaurantModels= restaurantService.getRestaurantByName(name);

        if(restaurantModels != null){
            return ResponseEntity.ok(restaurantModels);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRest(@PathVariable Long id, @RequestBody RestaurantModel restaurantModel){
        Restaurant restaurant1= restaurantService.updateRestaurant(id,restaurantModel);

        if(restaurant1 != null){
            return ResponseEntity.ok(restaurant1);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
