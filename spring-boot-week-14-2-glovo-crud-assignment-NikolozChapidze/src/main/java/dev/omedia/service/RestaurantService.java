package dev.omedia.service;

import dev.omedia.model.Order;
import dev.omedia.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    Set<Restaurant> restaurants = new HashSet<>();

    public Collection<Restaurant> getRestaurants(int page, int pageSize) {
        return this.restaurants.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Restaurant> getRestaurant(long id) {
        return this.restaurants.stream()
                .filter(restaurant -> restaurant.getId() == id)
                .findAny();
    }

    public boolean addRestaurant(final Restaurant restaurant) {
        long maxId = this.restaurants.stream()
                .map(Restaurant::getId)
                .max(Long::compare)
                .orElse(0L);
        restaurant.setId(maxId + 1);
        return this.restaurants.add(restaurant);
    }

    public boolean updateRestaurant(final long id, final Restaurant restaurant) {
        this.removeRestaurant(id);
        restaurant.setId(id);
        return this.addRestaurant(restaurant);
    }

    public boolean addProductToRestaurant(final long id, final long product) {
        Optional<Restaurant> restaurant = getRestaurant(id);
        if (restaurant.isEmpty()) {
            return false;
        }
        Set<Long> menu = restaurant.get().getMenu();
        return menu.add(product);
    }

    public boolean removeProductFromRestaurant(final long id, final long product) {
        Optional<Restaurant> restaurant = getRestaurant(id);
        if (restaurant.isEmpty()) {
            return false;
        }
        Set<Long> menu = restaurant.get().getMenu();
        Optional<Long> prodId = menu.stream()
                .filter(prod -> prod == id)
                .findAny();
        if (prodId.isEmpty()){
            return false;
        }
        menu.remove(prodId.get());
        return true;
    }

    public Optional<Restaurant> removeRestaurant(final long id) {
        Optional<Restaurant> currRestaurant = this.restaurants.stream()
                .filter(restaurant -> restaurant.getId() == id)
                .findAny();
        currRestaurant.ifPresent(this.restaurants::remove);
        return currRestaurant;
    }
}
