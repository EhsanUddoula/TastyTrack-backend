package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.Review;
import com.example.TastyTrack.TastyTrackbackend.Model.ReviewModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.ReviewRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Override
    public Review save(ReviewModel reviewModel) {
        Review review=new Review();
        BeanUtils.copyProperties(reviewModel,review);
        return reviewRepo.save(review);
    }

    @Override
    public List<ReviewModel> findByItemAndReview(String item, Long id) {
        List<Review> reviewList= reviewRepo.findByItemAndRestId(item,id);
        List<ReviewModel> reviewModelList=new ArrayList<>();

        for(Review review: reviewList){
            ReviewModel reviewModel= new ReviewModel();
            BeanUtils.copyProperties(review,reviewModel);
            reviewModelList.add(reviewModel);
        }

        return reviewModelList;
    }
}
