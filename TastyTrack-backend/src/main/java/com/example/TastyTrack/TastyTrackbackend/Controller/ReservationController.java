package com.example.TastyTrack.TastyTrackbackend.Controller;


import com.example.TastyTrack.TastyTrackbackend.Entity.Reservation;
import com.example.TastyTrack.TastyTrackbackend.Entity.Review;
import com.example.TastyTrack.TastyTrackbackend.Model.ReservationModel;
import com.example.TastyTrack.TastyTrackbackend.Model.ReviewModel;
import com.example.TastyTrack.TastyTrackbackend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/reservation/")

public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveReview(@RequestBody ReservationModel reservationModel){
        Reservation reservation= reservationService.save(reservationModel);
        if(reservation != null){
            return ResponseEntity.ok("Done");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{restId}")
    public ResponseEntity<List<ReservationModel>> getReservation(@PathVariable Long restId){
        List<ReservationModel> reservationModels=reservationService.findByRestId(restId);

        if(reservationModels.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(reservationModels);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ReservationModel>> getReservation(){
        List<ReservationModel> reservationModels=reservationService.findAllReservation();

        if(reservationModels.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(reservationModels);
        }
    }
}
