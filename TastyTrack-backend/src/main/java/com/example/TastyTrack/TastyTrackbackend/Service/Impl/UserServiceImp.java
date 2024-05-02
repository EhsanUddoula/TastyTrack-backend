package com.example.TastyTrack.TastyTrackbackend.Service.Impl;

import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;
import com.example.TastyTrack.TastyTrackbackend.Repository.UserRepo;
import com.example.TastyTrack.TastyTrackbackend.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User save(UserModel userModel) {
        User user=new User(userModel.getUser_id(),userModel.getEmail(),userModel.getFirstName(),userModel.getLastName(),passwordEncoder.encode(userModel.getPassword()),userModel.getAddress(),userModel.getPhone(),userModel.getImg_url(),userModel.getAccCreated(),userModel.getGender());
        return userRepo.save(user);
    }

    @Override
    public List<UserModel> getAllUser() {
        List<UserModel> userModelList=new ArrayList<>();
        List<User> userList=userRepo.findAll();

        for(User user: userList){
            UserModel userModel=new UserModel();
            BeanUtils.copyProperties(user,userModel);
            userModelList.add(userModel);
        }

        return userModelList;
    }
}