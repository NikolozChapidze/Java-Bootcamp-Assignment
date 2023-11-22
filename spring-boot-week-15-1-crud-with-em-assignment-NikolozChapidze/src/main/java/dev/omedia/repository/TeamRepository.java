package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.dto.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
@Transactional
public class TeamRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Team> getTeams(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting teams");
        return entityManager.createQuery("SELECT t FROM Team t ", Team.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Team> getTeam(long id) {
        log.debug("Invoked getting team with id : {}", id);
        return entityManager.createQuery("SELECT t FROM Team t WHERE t.id=:id", Team.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    public void addTeam(final Team team) {
        log.debug("Invoked adding team");
        entityManager.persist(team);
    }

    public Team updateTeam(final long id, final Team team) {
        log.debug("Invoked updating team with id {}", id);
        if (getTeam(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        team.setId(id);
        return entityManager.merge(team);
    }

    public int removeTeam(final long id) {
        log.debug("Invoked removing team with id {}", id);
        return entityManager.createQuery("DELETE FROM Team t WHERE t.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
