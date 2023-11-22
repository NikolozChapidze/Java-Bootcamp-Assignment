package dev.omedia.controller;

import dev.omedia.dto.Team;
import dev.omedia.service.SportService;
import dev.omedia.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("teams")
@Slf4j
public class TeamController {
    private final TeamService teamService;
    private final SportService sportService;

    @Autowired
    public TeamController(TeamService teamService, SportService sportService) {
        this.teamService = teamService;
        this.sportService = sportService;
    }

    @GetMapping
    public Collection<Team> getTeams(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return teamService.getTeams(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Team getTeam(@PathVariable(name = "identifier") long id) {
        Optional<Team> teamOptional = teamService.getTeam(id);
        return teamOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public int removeTeam(@PathVariable long id) {
        return teamService.removeTeam(id);
    }

    @PostMapping
    public void addTeam(@RequestBody Team team) {
        long sportId = team.getSport().getId();
        if (sportNotExists(sportId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport Not Found with id " + sportId);
        }
        team.setSport(sportService.getSport(sportId).orElseThrow());
        teamService.addTeam(team);
    }

    @PutMapping("{id}")
    public Team updateTeam(@PathVariable long id, @RequestBody Team team) {
        long sportId = team.getSport().getId();
        if (sportNotExists(sportId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport Not Found with id " + sportId);
        }
        team.setSport(sportService.getSport(sportId).orElseThrow());
        return teamService.updateTeam(id, team);
    }


    public boolean sportNotExists(long sportId) {
        return sportService.getSport(sportId).isEmpty();
    }
}
