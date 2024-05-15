package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.Reservation;
import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.ReservationModel;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.ReservationRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.ReservationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;
    @Override
    public Reservation save(ReservationModel reservationModel) {
        Reservation reservation= new Reservation();
        BeanUtils.copyProperties(reservationModel,reservation);
        return reservationRepo.save(reservation);
    }

    @Override
    public List<ReservationModel> findByRestId(Long restId) {
        List<ReservationModel> reservationModels=new ArrayList<>();
        List<Reservation> reservations=reservationRepo.findByRestId(restId);

        for(Reservation reservation: reservations){
            ReservationModel reservationModel=new ReservationModel();
            BeanUtils.copyProperties(reservation,reservationModel);
            reservationModels.add(reservationModel);
        }

        return reservationModels;
    }

    @Override
    public List<ReservationModel> findAllReservation() {
        List<ReservationModel> reservationModels=new ArrayList<>();
        List<Reservation> reservations=reservationRepo.findAll();

        for(Reservation reservation: reservations){
            ReservationModel reservationModel=new ReservationModel();
            BeanUtils.copyProperties(reservation,reservationModel);
            reservationModels.add(reservationModel);
        }

        return reservationModels;
    }


}
