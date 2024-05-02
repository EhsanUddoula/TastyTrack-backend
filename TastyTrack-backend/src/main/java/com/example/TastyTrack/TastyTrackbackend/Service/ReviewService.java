package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.Review;
import com.example.TastyTrack.TastyTrackbackend.Model.ReviewModel;

import java.util.List;

public interface ReviewService {
    Review save(ReviewModel reviewModel);

    List<ReviewModel> findByItemAndReview(String item,Long id);
}
