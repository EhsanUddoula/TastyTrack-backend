package com.example.TastyTrack.TastyTrackbackend.Controller;

import com.example.TastyTrack.TastyTrackbackend.Entity.Review;
import com.example.TastyTrack.TastyTrackbackend.Model.ReviewModel;
import com.example.TastyTrack.TastyTrackbackend.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review/")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<String> saveReview(@RequestBody ReviewModel reviewModel){
        Review review= reviewService.save(reviewModel);
        if(review != null){
            return ResponseEntity.ok("Done");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{item}/{restId}")
    public ResponseEntity<List<ReviewModel>> getReviewByItemAndRest(@PathVariable("item") String item, @PathVariable("restId") Long restId){
        List<ReviewModel> reviewModelList= reviewService.findByItemAndReview(item, restId);

        if(reviewModelList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(reviewModelList);
        }
    }
}
