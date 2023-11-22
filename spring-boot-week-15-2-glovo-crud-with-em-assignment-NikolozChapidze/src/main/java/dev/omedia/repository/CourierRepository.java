package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Courier;
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
public class CourierRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Courier> getCouriers(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting courier");
        return entityManager.createQuery("SELECT c FROM Courier c ", Courier.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Courier> getCourier(long id) {
        log.debug("Invoked getting courier with id : {}", id);
        return entityManager.createQuery("SELECT c FROM Courier c WHERE c.id=:id", Courier.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void addCourier(final Courier courier) {
        log.debug("Invoked adding courier");
        entityManager.persist(courier);
    }

    public Courier updateCourier(final long id, final Courier courier) {
        log.debug("Invoked updating courier with id {}", id);
        if (getCourier(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        courier.setId(id);
        return entityManager.merge(courier);
    }

    public int removeCourier(final long id) {
        log.debug("Invoked removing courier with id {}", id);
        return entityManager.createQuery("DELETE FROM Courier c WHERE c.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
