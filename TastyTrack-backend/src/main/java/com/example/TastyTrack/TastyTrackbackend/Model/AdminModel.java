package com.example.TastyTrack.TastyTrackbackend.Model;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminModel {
    private long admin_id;

    private String email;

    private String firstName;

    private String lastName;


    private String password;


    private String phone;
    private String address;
    private String img_url;

    private String gender;
}

