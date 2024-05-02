package com.example.TastyTrack.TastyTrackbackend.Model;

import jakarta.persistence.Column;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private long user_id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private String phone;
    private String address;
    private String img_url;
    private LocalDateTime accCreated;

    private String gender;
}
