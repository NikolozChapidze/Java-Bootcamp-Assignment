package dev.omedia.controller;

import dev.omedia.model.Restaurant;
import dev.omedia.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("restaurants")
@Slf4j
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public Collection<Restaurant> getRestaurants(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return restaurantService.getRestaurants(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Restaurant getRestaurant(@PathVariable(name = "identifier") long id) {
        Optional<Restaurant> sportOptional = restaurantService.getRestaurant(id);
        return sportOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public int removeRestaurant(@PathVariable long id) {
        return restaurantService.removeRestaurant(id);
    }

    @PostMapping
    public void addRestaurant(@RequestBody Restaurant sport) {
        restaurantService.addRestaurant(sport);
    }

    @PutMapping("{id}")
    public Restaurant updateRestaurant(@PathVariable long id, @RequestBody Restaurant sport) {
        return restaurantService.updateRestaurant(id, sport);
    }
}
