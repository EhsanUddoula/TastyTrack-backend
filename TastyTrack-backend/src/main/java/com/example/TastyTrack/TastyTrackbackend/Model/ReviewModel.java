package com.example.TastyTrack.TastyTrackbackend.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewModel {
    private long review_id;
    private long user_id;
    private long rest_id;
    private String item;
    private String review;
    private double rating;
    private LocalDateTime time;
}
