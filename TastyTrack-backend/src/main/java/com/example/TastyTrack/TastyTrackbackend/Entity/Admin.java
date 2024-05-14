package com.example.TastyTrack.TastyTrackbackend.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long admin_id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;


    private String phone;
    private String address;
    private String img_url;

    private String gender;
}
