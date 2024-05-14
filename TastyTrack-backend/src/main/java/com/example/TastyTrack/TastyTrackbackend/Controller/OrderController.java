package com.example.TastyTrack.TastyTrackbackend.Controller;

import com.example.TastyTrack.TastyTrackbackend.Entity.Order;
import com.example.TastyTrack.TastyTrackbackend.Model.OrderModel;
import com.example.TastyTrack.TastyTrackbackend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody OrderModel orderModel){
        Order order=orderService.saveOrder(orderModel);
        if(order != null){
            return ResponseEntity.ok("Done");
        }else{
                return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderModel>> getAllOrderList(){
        List<OrderModel> orderModelList= orderService.getAllOrder();

        if(orderModelList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderModelList);
        }
    }

    @GetMapping("/get/{restId}")
    public ResponseEntity<List<OrderModel>> getOrderListRest(@PathVariable("restId") Long restId){
        List<OrderModel> orderModelList= orderService.getAllByRest(restId);

        if(orderModelList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderModelList);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderModel orderModel){
        Order order= orderService.updateOrder(id,orderModel);
        if(order != null){
            return ResponseEntity.ok(order);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
