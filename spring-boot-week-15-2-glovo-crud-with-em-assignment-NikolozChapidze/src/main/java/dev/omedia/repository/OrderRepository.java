package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Order;
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
public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Order> getOrders(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting order");
        return entityManager.createQuery("SELECT o FROM Order o ", Order.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Order> getOrder(long id) {
        log.debug("Invoked getting order with id : {}", id);
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.id=:id", Order.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void addOrder(final Order order) {
        log.debug("Invoked adding order");
        entityManager.persist(order);
    }

    public Order updateOrder(final long id, final Order order) {
        log.debug("Invoked updating order with id {}", id);
        if (getOrder(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        order.setId(id);
        return entityManager.merge(order);
    }

    public int removeOrder(final long id) {
        log.debug("Invoked removing order with id {}", id);
        return entityManager.createQuery("DELETE FROM Order o WHERE o.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
