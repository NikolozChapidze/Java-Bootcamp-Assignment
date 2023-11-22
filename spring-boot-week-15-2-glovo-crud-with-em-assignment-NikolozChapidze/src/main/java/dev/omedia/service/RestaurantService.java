package dev.omedia.service;

import dev.omedia.model.Restaurant;
import dev.omedia.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Collection<Restaurant> getRestaurants(int page, int pageSize) {
        return repository.getRestaurants(page, pageSize);
    }

    public Optional<Restaurant> getRestaurant(long id) {
        return repository.getRestaurant(id);
    }

    public void addRestaurant(final Restaurant restaurant) {
        repository.addRestaurant(restaurant);
    }

    public Restaurant updateRestaurant(final long id, final Restaurant restaurant) {
        return repository.updateRestaurant(id, restaurant);
    }

    public int removeRestaurant(final long id) {
        return repository.removeRestaurant(id);
    }
}
