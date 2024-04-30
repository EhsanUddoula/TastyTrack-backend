package com.example.TastyTrack.TastyTrackbackend.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantModel {
    private long rest_Id;
    private String name=" ";
    private  String email=" ";
    private String img_url;
    private String password=" ";
    private String address;
    private String description;
    private String phone;
}
