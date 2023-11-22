package dev.omedia.controller;

import dev.omedia.exceprion.ResourceNotFoundException;
import dev.omedia.model.Restaurant;
import dev.omedia.service.ProductService;
import dev.omedia.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("restaurants")
@Slf4j
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ProductService productService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, ProductService productService) {
        this.restaurantService = restaurantService;
        this.productService = productService;
    }


    @GetMapping
    public Collection<Restaurant> getRestaurants(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return restaurantService.getRestaurants(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Restaurant getRestaurant(@PathVariable(name = "identifier") long id) {
        Optional<Restaurant> restaurantOptional = restaurantService.getRestaurant(id);
        return restaurantOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant Not Found with id" + id));
    }

    @DeleteMapping("{id}")
    public Restaurant removeRestaurant(@PathVariable long id) {
        productService.removeRestaurantProducts(id);
        return restaurantService.removeRestaurant(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addRestaurant(@RequestBody Restaurant restaurant) {
        if (!(productService.existsProducts(restaurant.getMenu()))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not all products found");
        }
        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping("{id}")
    public boolean updateRestaurant(@PathVariable long id, @RequestBody Restaurant restaurant) {
        if (!(productService.existsProducts(restaurant.getMenu()))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not all products found");
        }
        return restaurantService.updateRestaurant(id, restaurant);
    }

}
