package com.example.TastyTrack.TastyTrackbackend.Controller;

import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;
import com.example.TastyTrack.TastyTrackbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> save(@RequestBody UserModel userModel){
        User user= userService.save(userModel);
        if(user != null){
            return ResponseEntity.ok("Registered Successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserModel>> getAll(){
        List<UserModel> userModelList=userService.getAllUser();

        if(userModelList.isEmpty()){
           return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(userModelList);
        }
    }
}
