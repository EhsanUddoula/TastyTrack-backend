package com.example.TastyTrack.TastyTrackbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodItemModel {
    private String item;
    private Long rest_id;
    private String rest_name;
    private Double price;
    private String description;
    private String img_url;
    private String category;

}
