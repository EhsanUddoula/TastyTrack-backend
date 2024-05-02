package com.example.TastyTrack.TastyTrackbackend.Entity;


import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="Food_Order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private String foodItem;
    @Column(nullable = false)
    private long restId;
    @Column(nullable = false)
    private long userId;

    @CreatedDate
    private LocalDateTime orderTime;

    @Timestamp
    private LocalDateTime deliveryTime;
    private String paymentStatus;
}
