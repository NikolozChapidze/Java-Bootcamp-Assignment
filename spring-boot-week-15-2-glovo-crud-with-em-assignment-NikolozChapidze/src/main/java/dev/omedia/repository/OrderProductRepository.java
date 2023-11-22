package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.OrderProduct;
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
public class OrderProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<OrderProduct> getOrderProducts(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting orderProduct");
        return entityManager.createQuery("SELECT o FROM OrderProduct o ", OrderProduct.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<OrderProduct> getOrderProduct(long id) {
        log.debug("Invoked getting orderProduct with id : {}", id);
        return entityManager.createQuery("SELECT o FROM OrderProduct o WHERE o.orderItemId=:id", OrderProduct.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void addOrderProduct(final OrderProduct orderProduct) {
        log.debug("Invoked adding orderProduct");
        entityManager.persist(orderProduct);
    }

    public OrderProduct updateOrderProduct(final long id, final OrderProduct orderProduct) {
        log.debug("Invoked updating orderProduct with id {}", id);
        if (getOrderProduct(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        orderProduct.setOrderItemId(id);
        return entityManager.merge(orderProduct);
    }

    public int removeOrderProduct(final long id) {
        log.debug("Invoked removing orderProduct with id {}", id);
        return entityManager.createQuery("DELETE FROM OrderProduct o WHERE o.orderItemId=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
