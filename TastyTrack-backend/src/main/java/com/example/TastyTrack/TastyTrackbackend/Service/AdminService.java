package com.example.TastyTrack.TastyTrackbackend.Service;

import com.example.TastyTrack.TastyTrackbackend.Entity.Admin;
import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.AdminModel;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;

public interface AdminService {
    Admin save(AdminModel adminModel);

    Admin findEmail(String email);

    AdminModel getUserById(Long id);
}
