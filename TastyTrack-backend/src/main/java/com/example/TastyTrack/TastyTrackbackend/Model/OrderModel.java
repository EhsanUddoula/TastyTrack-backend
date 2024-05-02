package com.example.TastyTrack.TastyTrackbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderModel {
    private long orderId;
    private String foodItem;
    private long restId;
    private long userId;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    private String paymentStatus;
}
