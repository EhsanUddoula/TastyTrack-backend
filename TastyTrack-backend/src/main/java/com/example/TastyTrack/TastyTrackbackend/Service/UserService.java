package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(UserModel userModel);

    List<UserModel> getAllUser();

    UserModel getUserById(Long id);

    User findEmail(String email);
}
