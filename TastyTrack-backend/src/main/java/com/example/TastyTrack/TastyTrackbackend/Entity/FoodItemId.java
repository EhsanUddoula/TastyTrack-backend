package com.example.TastyTrack.TastyTrackbackend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodItemId implements Serializable {

    @Column(name="Item")
    private String item;
    @Column(name="rest_Id")
    private Long Restaurant_id;
}
