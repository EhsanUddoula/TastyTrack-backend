package com.example.TastyTrack.TastyTrackbackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="favourite")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long favouriteId;
    private FoodItemId foodItemId;
}
