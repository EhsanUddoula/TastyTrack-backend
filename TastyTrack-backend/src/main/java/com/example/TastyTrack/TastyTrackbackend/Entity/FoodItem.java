package com.example.TastyTrack.TastyTrackbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//    @ManyToMany
//    private List<Restaurant> restaurantList=new ArrayList<>();

//    @ManyToOne
//    private Restaurant restaurant;
}

