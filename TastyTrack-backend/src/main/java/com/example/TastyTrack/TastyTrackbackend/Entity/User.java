package com.example.TastyTrack.TastyTrackbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

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

    @CreatedDate
    private LocalDateTime accCreated;

    private String gender;

    public User(long user_id, String email, String firstName, String lastName, String password, String phone, String address, String img_url, LocalDateTime accCreated, String gender) {
        this.user_id = user_id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.img_url = img_url;
        this.accCreated = accCreated;
        this.gender = gender;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="userId", referencedColumnName = "user_id")
    private List<Favourites> favourites=new ArrayList<>();
}
