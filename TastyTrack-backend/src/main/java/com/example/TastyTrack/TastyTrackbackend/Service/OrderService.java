package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.Order;
import com.example.TastyTrack.TastyTrackbackend.Model.OrderModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order saveOrder(OrderModel orderModel);

    List<OrderModel> getAllOrder();

    Order updateOrder(Long id, OrderModel orderModel);

    List<OrderModel> getAllByRest(Long restId);
}
