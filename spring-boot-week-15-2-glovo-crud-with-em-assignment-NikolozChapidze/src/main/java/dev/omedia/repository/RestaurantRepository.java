package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
@Slf4j
public class RestaurantRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Restaurant> getRestaurants(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting restaurant");
        return entityManager.createQuery("SELECT r FROM Restaurant r ", Restaurant.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Restaurant> getRestaurant(long id) {
        log.debug("Invoked getting restaurant with id : {}", id);
        return entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.restaurantId=:id", Restaurant.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void addRestaurant(final Restaurant restaurant) {
        log.debug("Invoked adding restaurant");
        entityManager.persist(restaurant);
    }

    public Restaurant updateRestaurant(final long id, final Restaurant restaurant) {
        log.debug("Invoked updating restaurant with id {}", id);
        if (getRestaurant(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        restaurant.setRestaurantId(id);
        return entityManager.merge(restaurant);
    }

    public int removeRestaurant(final long id) {
        log.debug("Invoked removing restaurant with id {}", id);
        return entityManager.createQuery("DELETE FROM Restaurant r WHERE r.restaurantId=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
