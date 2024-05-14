package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.Order;
import com.example.TastyTrack.TastyTrackbackend.Model.OrderModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.OrderRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Override
    public Order saveOrder(OrderModel orderModel) {
        Order order= new Order();
        BeanUtils.copyProperties(orderModel,order);
        return orderRepo.save(order);
    }
    @Override
    public List<OrderModel> getAllOrder(){
        List<OrderModel>orderModelList=new ArrayList<>();
        List<Order> orderList= orderRepo.findAll();

        for(Order order: orderList){
            OrderModel orderModel=new OrderModel();
            BeanUtils.copyProperties(order,orderModel);
            orderModelList.add(orderModel);
        }

        return orderModelList;
    }

    @Override
    public Order updateOrder(Long id, OrderModel orderModel) {
        Optional<Order> optionalOrder=orderRepo.findById(id);
        if(optionalOrder.isEmpty()){
            return null;
        }

        Order model= optionalOrder.get();


        model.setFoodItem((orderModel.getFoodItem()));
        model.setDeliveryTime(orderModel.getDeliveryTime());
        model.setRestId(orderModel.getRestId());
        model.setUserId(orderModel.getUserId());
        model.setPaymentStatus(orderModel.getPaymentStatus());

        return orderRepo.save(model);
    }

    @Override
    public List<OrderModel> getAllByRest(Long restId) {
        List<OrderModel>orderModelList=new ArrayList<>();
        List<Order> orderList= orderRepo.findByRestId(restId);

        for(Order order: orderList){
            OrderModel orderModel=new OrderModel();
            BeanUtils.copyProperties(order,orderModel);
            orderModelList.add(orderModel);
        }

        return orderModelList;
    }
}
