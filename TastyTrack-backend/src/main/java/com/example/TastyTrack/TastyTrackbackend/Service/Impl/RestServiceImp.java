package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Model.RestModelAddress;
import com.example.TastyTrack.TastyTrackbackend.Model.RestaurantModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.RestaurantRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<RestaurantModel> getRestaurantByName(String name) {
        List<Restaurant> restaurants= restaurantRepo.findByNameLike("%"+name+"%");
        List<RestaurantModel> restaurantModels= new ArrayList<>();

        for( Restaurant restaurant: restaurants){
            RestaurantModel model= new RestaurantModel();
            BeanUtils.copyProperties(restaurant,model);

            restaurantModels.add(model);
        }

        return restaurantModels;
    }

    @Override
    public Restaurant updateRestaurant(long id, RestaurantModel model) {
        Optional<Restaurant> restaurant= restaurantRepo.findById(id);
        if(restaurant.isEmpty()){
            return null;
        }
        Restaurant updatedRestaurant= restaurant.get();

        updatedRestaurant.setAddress(model.getAddress());
        updatedRestaurant.setDescription(model.getDescription());
        updatedRestaurant.setImg_url(model.getImg_url());
        updatedRestaurant.setName(model.getName());
        updatedRestaurant.setEmail(model.getEmail());
        updatedRestaurant.setPhone(model.getPhone());
        updatedRestaurant.setPassword(passwordEncoder.encode(model.getPassword()));

        return restaurantRepo.save(updatedRestaurant);
    }

    @Override
    public List<RestModelAddress> getRestaurantByAddress(String address) {
        List <Restaurant> restaurants= restaurantRepo.findByAddress(address);
        List<RestModelAddress> restModelAddresses= new ArrayList<>();

        for(Restaurant restaurant: restaurants){
            RestModelAddress restModelAddress=new RestModelAddress();
            restModelAddress.setName(restaurant.getName());
            restModelAddress.setDescription(restaurant.getDescription());
            restModelAddress.setImg_url(restaurant.getImg_url());
            restModelAddress.setRest_Id(restaurant.getRest_Id());

            restModelAddresses.add(restModelAddress);
        }
        return restModelAddresses;
    }

    @Override
    public Restaurant findEmail(String email){
        return restaurantRepo.findByEmail(email);
    }
}
