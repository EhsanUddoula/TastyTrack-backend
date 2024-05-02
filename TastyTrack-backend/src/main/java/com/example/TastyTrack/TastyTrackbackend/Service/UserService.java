package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;

import java.util.List;

public interface UserService {
    User save(UserModel userModel);

    List<UserModel> getAllUser();
}
