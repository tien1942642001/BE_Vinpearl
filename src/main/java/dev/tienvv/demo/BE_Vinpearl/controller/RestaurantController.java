package dev.tienvv.demo.BE_Vinpearl.controller;

import dev.tienvv.demo.BE_Vinpearl.base.ResponseMessage;
import dev.tienvv.demo.BE_Vinpearl.model.Restaurant;
import dev.tienvv.demo.BE_Vinpearl.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    LocalDateTime localDateTime = LocalDateTime.now();

    @PostMapping("/create")
    public ResponseMessage createNewRestaurant(@RequestBody Restaurant restaurant) {
        System.out.println("local:" +localDateTime);
        restaurant.setCreatedDate(localDateTime.toString());
        restaurantService.save(restaurant);
        return new ResponseMessage(200, "Success", "", null);
    }

    @PostMapping("/update")
    public ResponseMessage updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurant.setCreatedDate(localDateTime.toString());
        restaurantService.save(restaurant);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage showRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurantOptional = restaurantService.findById(id);
        return restaurantOptional.map(restaurant -> new ResponseMessage(200, "Success", restaurant, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }
}
