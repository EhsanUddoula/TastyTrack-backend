package com.example.TastyTrack.TastyTrackbackend.Repository;

import com.example.TastyTrack.TastyTrackbackend.Entity.Order;
import com.example.TastyTrack.TastyTrackbackend.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByRestId(Long restId);
}
