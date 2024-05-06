package com.example.TastyTrack.TastyTrackbackend.Controller;

import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItem;
import com.example.TastyTrack.TastyTrackbackend.Entity.FoodItemId;
import com.example.TastyTrack.TastyTrackbackend.Model.FoodItemModel;
import com.example.TastyTrack.TastyTrackbackend.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/food/")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    private final String FOLDER_PATH="C:/Users/User/Documents/image/Food/";
    private FoodItem foodItem;

    @PostMapping("/save")
    public ResponseEntity<String> SaveFood(@ModelAttribute FoodItemModel foodItemModel,
                                           @RequestParam("image")MultipartFile file) throws IOException {
        String fileName=FOLDER_PATH+ file.getOriginalFilename();
        foodItemModel.setImg_url(fileName);
        file.transferTo(new File(fileName));
        foodItem= foodItemService.save(foodItemModel);

        if (foodItem != null) {
            return ResponseEntity.ok("Food Item Added!"); // Return 200 OK with the restaurant data
        } else {
            return ResponseEntity.notFound().build();// Return 404 Not Found if restaurant not found
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodItemModel>>getAllFood(){
        List<FoodItemModel> foodItemModels=foodItemService.getAllFood();


        if(foodItemModels != null){
            return ResponseEntity.ok(foodItemModels);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{item}")
    public ResponseEntity<List<FoodItemModel>>getAllFoodByName(@PathVariable String item){
        List<FoodItemModel> foodItemModels=foodItemService.getAllFoodByName(item);


        if(foodItemModels != null){
            return ResponseEntity.ok(foodItemModels);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{item}/{restId}")
    public ResponseEntity<FoodItem> updateFood(
            @PathVariable("item") String item,
            @PathVariable("restId") Long restId,
            @RequestBody FoodItemModel model) {

        FoodItemId foodId = new FoodItemId(item, restId);

        FoodItem updatedFoodItem = foodItemService.updateFood(foodId, model);
        if (updatedFoodItem != null) {
            return ResponseEntity.ok(updatedFoodItem); // Return 200 OK with the updated food item data
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if food item not found
        }
    }

    @DeleteMapping("delete/{item}/{restId}")
    public ResponseEntity<String> deleteFood(@PathVariable("item") String item,
                                             @PathVariable("restId") Long restId){
        FoodItemId foodId = new FoodItemId(item, restId);
        foodItemService.deleteFood(foodId);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @GetMapping("/image/{item}/{restId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("item") String item,
                                @PathVariable("restId") Long restId) throws IOException{
        FoodItemId foodItemId = new FoodItemId(item, restId);
        Optional<FoodItem> foodItem1=foodItemService.getFoodById(foodItemId);

        if(foodItem1.isPresent()){

            String imagePath= foodItem1.get().getImg_url();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // Set the appropriate content type (e.g., JPEG, PNG, etc.)

            return new ResponseEntity<>(Files.readAllBytes(new File(imagePath).toPath()), headers, HttpStatus.OK);

        }else{
            return null;
        }
    }

    @GetMapping("/rest/{restId}")
    public ResponseEntity<List<FoodItemModel>>getAllFoodByName(@PathVariable long restId){
        List<FoodItemModel> foodItemModels=foodItemService.getAllFoodById(restId);


        if(foodItemModels != null){
            return ResponseEntity.ok(foodItemModels);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
