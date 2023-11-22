package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.dto.Sport;
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
public class SportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Sport> getSports(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting sports");
        return entityManager.createQuery("SELECT s FROM Sport s ", Sport.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Sport> getSport(final long id) {
        log.debug("Invoked getting sport with id : {}", id);
        return entityManager.createQuery("SELECT s FROM Sport s WHERE s.id=:id", Sport.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    public void addSport(final Sport sport) {
        log.debug("Invoked adding sport");
        entityManager.persist(sport);
    }

    public Sport updateSport(final long id, final Sport sport) {
        log.debug("Invoked updating sport with id {}", id);
        if (getSport(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        sport.setId(id);
        return entityManager.merge(sport);
    }

    public int removeSport(final long id) {
        log.debug("Invoked removing sport with id {}", id);
        return entityManager.createQuery("DELETE FROM Sport s WHERE s.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
