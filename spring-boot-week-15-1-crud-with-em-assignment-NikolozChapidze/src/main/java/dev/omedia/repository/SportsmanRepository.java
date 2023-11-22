package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.dto.Sportsman;
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
public class SportsmanRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Sportsman> getSportsmen(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting sportsmen");
        return entityManager.createQuery("SELECT s FROM Sportsman s ", Sportsman.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Sportsman> getSportsman(long id) {
        log.debug("Invoked getting sportsman with id : {}", id);
        return entityManager.createQuery("SELECT s FROM Sportsman s WHERE s.id=:id", Sportsman.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void addSportsman(final Sportsman sportsman) {
        log.debug("Invoked adding sportsmen");
        entityManager.persist(sportsman);
    }

    public Sportsman updateSportsman(final long id, final Sportsman sportsman) {
        log.debug("Invoked updating sportsmen with id {}", id);
        if (getSportsman(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        sportsman.setId(id);
        return entityManager.merge(sportsman);
    }

    public int removeSportsman(final long id) {
        log.debug("Invoked removing sportsmen with id {}", id);
        return entityManager.createQuery("DELETE FROM Sportsman s WHERE s.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
