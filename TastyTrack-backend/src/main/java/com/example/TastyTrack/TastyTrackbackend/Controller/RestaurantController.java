package com.example.TastyTrack.TastyTrackbackend.Controller;


import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItem;
import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItemId;
import com.example.TastyTrack.TastyTrackbackend.Entity.Restaurant;
import com.example.TastyTrack.TastyTrackbackend.Entity.User;
import com.example.TastyTrack.TastyTrackbackend.Model.RestLogin;
import com.example.TastyTrack.TastyTrackbackend.Model.RestModelAddress;
import com.example.TastyTrack.TastyTrackbackend.Model.RestaurantModel;
import com.example.TastyTrack.TastyTrackbackend.Model.UserModel;
import com.example.TastyTrack.TastyTrackbackend.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/restaurant/")
public class RestaurantController {

    @Autowired(required = true)
    private RestaurantService restaurantService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private Restaurant restaurant;
    //private final String FOLDER_PATH="C:/Users/User/Documents/image/Restaurant/";
    private final String FOLDER_PATH="C:/Users/HP/Documents/image/restaurant/";


    @PostMapping("/register")
    public  Long SaveRest(@ModelAttribute RestaurantModel restaurantModel,
                            @RequestParam("image") MultipartFile file) throws IOException {
        String fileName=FOLDER_PATH+ file.getOriginalFilename();
        restaurantModel.setImg_url(fileName);
        file.transferTo(new File(fileName));


        restaurant=restaurantService.save(restaurantModel);

        return restaurant.getRest_Id();
    }
    @GetMapping("/get")
    public ResponseEntity<List<RestaurantModel>> getAll(){
        List<RestaurantModel> userModelList=restaurantService.getAllRestaurant();

        if(userModelList.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(userModelList);
        }
    }
    @PostMapping("/login")
    public String loginRest(@RequestBody RestLogin restLogin){
        Restaurant restaurant1=restaurantService.findEmail(restLogin.getEmail());
        System.out.println(restLogin.getPassword());
        if(restaurant1 == null){
            return "Email Not Found";
        }
        if (!passwordEncoder.matches(restLogin.getPassword(), restaurant1.getPassword())) {
            return "Password Not Found";
        }

        return restaurant1.getEmail();
    }
    @GetMapping("/getOne/{id}")
    public ResponseEntity<RestaurantModel> getRestaurantById(@PathVariable long id) {
        // Retrieve the restaurant by ID from the service or repository
        RestaurantModel restaurant = restaurantService.getRestaurantById(id);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant); // Return 200 OK with the restaurant data
        } else {
            return ResponseEntity.notFound().build();// Return 404 Not Found if restaurant not found
        }
    }

    @GetMapping("/search/{name}")
    public  ResponseEntity<List<RestaurantModel>> searchRestaurant(@PathVariable String name){
        List<RestaurantModel> restaurantModels= restaurantService.getRestaurantByName(name);

        if(restaurantModels != null){
            return ResponseEntity.ok(restaurantModels);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Restaurant> updateRest(@PathVariable Long id, @RequestBody RestaurantModel restaurantModel){
        Restaurant restaurant1= restaurantService.updateRestaurant(id,restaurantModel);

        if(restaurant1 != null){
            return ResponseEntity.ok(restaurant1);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/location/{address}")
    public ResponseEntity<List<RestModelAddress>> getByAddress(@PathVariable("address") String address){
        List<RestModelAddress> restModelAddresses= restaurantService.getRestaurantByAddress(address);

        if(restModelAddresses != null){
            return ResponseEntity.ok(restModelAddresses);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/image/{restId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("restId") Long restId) throws IOException{
        RestModelAddress restModelAddress = new RestModelAddress();
        RestaurantModel restaurant1=restaurantService.getRestaurantById(restId);

        if(restaurant1 != null){

            String imagePath= restaurant1.getImg_url();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Set the appropriate content type (e.g., JPEG, PNG, etc.)

            return new ResponseEntity<>(Files.readAllBytes(new File(imagePath).toPath()), headers, HttpStatus.OK);

        }else{
            return null;
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<Long> getUser(@PathVariable("email") String email){
        Restaurant restaurant1=restaurantService.findEmail(email);
        if(restaurant1 != null){
            return ResponseEntity.ok(restaurant1.getRest_Id());
        }
        return ResponseEntity.notFound().build();
    }
}
