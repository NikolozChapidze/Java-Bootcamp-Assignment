package dev.omedia.service;

import dev.omedia.model.Team;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamService {
    Set<Team> teams = new HashSet<>();

    public Collection<Team> getTeams(int page, int pageSize) {
        return this.teams.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Team> getTeam(long id) {
        return this.teams.stream()
                .filter(team -> team.getId() == id)
                .findAny();
    }

    public boolean addTeam(final Team team) {
        long maxId = this.teams.stream()
                .map(Team::getId)
                .max(Long::compare)
                .orElse(0L);
        team.setId(maxId + 1);
        return this.teams.add(team);
    }

    public boolean updateTeam(final long id, final Team team) {
        this.removeTeam(id);
//        .orElseThrow(UserNotFoundException::new);
        team.setId(id);
        return this.addTeam(team);
    }

    public Optional<Team> removeTeam(final long id) {
        Optional<Team> currTeam = this.teams.stream()
                .filter(sport -> sport.getId() == id)
                .findAny();
        currTeam.ifPresent(this.teams::remove);
        return currTeam;
    }
}
