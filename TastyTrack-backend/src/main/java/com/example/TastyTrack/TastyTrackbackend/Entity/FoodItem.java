package com.example.TastyTrack.TastyTrackbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "Food_Item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodItem {
    @EmbeddedId
    private FoodItemId foodItemId;

    @Column(nullable = false)
    private String rest_name;
    private Double price;
    private String description;
    private String img_url;

    public FoodItem(FoodItemId foodItemId, String rest_name, Double price, String description, String img_url) {
        this.foodItemId = foodItemId;
        this.rest_name = rest_name;
        this.price = price;
        this.description = description;
        this.img_url = img_url;
    }
//    @ManyToMany
//    private List<Restaurant> restaurantList=new ArrayList<>();

//    @ManyToOne
//    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodItemId",cascade = CascadeType.ALL)
    private List<Favourites> favourites=new ArrayList<>();
}

