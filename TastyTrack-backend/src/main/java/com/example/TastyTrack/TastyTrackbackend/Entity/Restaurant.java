package com.example.TastyTrack.TastyTrackbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rest_Id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private  String email;
    private String img_url;

    @Column(name="password", nullable = false)
    private String password;
    private String address;

    private String description;
    private String phone;


//    @ManyToMany(mappedBy = "restaurantList", cascade =CascadeType.ALL)
//    private List<FoodItem>foodItemList=new ArrayList<>();

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private List<FoodItem> foodItemList= new ArrayList<>();

}
