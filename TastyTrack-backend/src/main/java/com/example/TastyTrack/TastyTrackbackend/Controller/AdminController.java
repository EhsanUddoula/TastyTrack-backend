package com.example.TastyTrack.TastyTrackbackend.Controller;


import com.example.TastyTrack.TastyTrackbackend.Entity.Admin;
import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.AdminLogin;
import com.example.TastyTrack.TastyTrackbackend.Model.AdminModel;
import com.example.TastyTrack.TastyTrackbackend.Model.UserLogin;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;
import com.example.TastyTrack.TastyTrackbackend.Service.AdminService;
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

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})

@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //private final String FOLDER_PATH="C:/Users/User/Documents/image/admin/";
    private final String FOLDER_PATH="C:/Users/HP/Documents/image/admin/";

    @PostMapping("/register")
    public ResponseEntity<String> save(@ModelAttribute AdminModel adminModel, @RequestParam("image") MultipartFile file) throws IOException {
        String fileName=FOLDER_PATH+ file.getOriginalFilename();
        adminModel.setImg_url(fileName);
        file.transferTo(new File(fileName));

        Admin admin= adminService.save(adminModel);
        if(admin != null){
            return ResponseEntity.ok("Registered Successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public String loginRest(@RequestBody AdminLogin adminLogin){
        Admin admin=adminService.findEmail(adminLogin.getEmail());
        System.out.println(adminLogin.getPassword());
        if(admin == null){
            return "Email Not Found";
        }
        if (!passwordEncoder.matches(adminLogin.getPassword(), admin.getPassword())) {
            return "Password Not Found";
        }

        return admin.getEmail();
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("userId") Long userId) throws IOException{
        AdminModel adminModel = adminService.getUserById(userId);

        if(adminModel != null){

            String imagePath= adminModel.getImg_url();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Set the appropriate content type (e.g., JPEG, PNG, etc.)

            return new ResponseEntity<>(Files.readAllBytes(new File(imagePath).toPath()), headers, HttpStatus.OK);

        }else{
            return null;
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<Long> getAdmin(@PathVariable("email") String email){
        Admin admin=adminService.findEmail(email);
        if(admin != null){
            return ResponseEntity.ok(admin.getAdmin_id());
        }
        return ResponseEntity.notFound().build();
    }
}
