package dev.omedia.service;

import dev.omedia.dto.Team;
import dev.omedia.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TeamService {
    TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public Collection<Team> getTeams(int page, int pageSize) {
        return repository.getTeams(page, pageSize);
    }

    public Optional<Team> getTeam(long id) {
        return repository.getTeam(id);
    }

    public void addTeam(final Team team) {
        repository.addTeam(team);
    }

    public Team updateTeam(final long id, final Team team) {
        return repository.updateTeam(id, team);

    }

    public int removeTeam(final long id) {
        return repository.removeTeam(id);

    }
}
