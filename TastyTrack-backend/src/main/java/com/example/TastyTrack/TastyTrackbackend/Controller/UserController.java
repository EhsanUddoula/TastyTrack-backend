package com.example.TastyTrack.TastyTrackbackend.Controller;

import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.*;
import com.example.TastyTrack.TastyTrackbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin("*")

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //private final String FOLDER_PATH="C:/Users/User/Documents/image/user/";
    private final String FOLDER_PATH="C:/Users/HP/Documents/image/user/";

    @PostMapping("/register")
    public ResponseEntity<String> save(@ModelAttribute UserModel userModel,@RequestParam("image") MultipartFile file) throws IOException {
        String fileName=FOLDER_PATH+ file.getOriginalFilename();
        userModel.setImg_url(fileName);
        file.transferTo(new File(fileName));

        User user= userService.save(userModel);
        if(user != null){
            return ResponseEntity.ok("Registered Successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public String loginRest(@RequestBody UserLogin userLogin){
        User user=userService.findEmail(userLogin.getEmail());
        System.out.println(userLogin.getPassword());
        if(user == null){
            return "Email Not Found";
        }
        if (!passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
            return "Password Not Found";
        }

        return user.getEmail();
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

    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("userId") Long userId) throws IOException{
        UserModel userModel = userService.getUserById(userId);

        if(userModel != null){

            String imagePath= userModel.getImg_url();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Set the appropriate content type (e.g., JPEG, PNG, etc.)

            return new ResponseEntity<>(Files.readAllBytes(new File(imagePath).toPath()), headers, HttpStatus.OK);

        }else{
            return null;
        }
    }
}
