package dev.omedia.controller;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Sport;
import dev.omedia.model.Team;
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
    public Team removeTeam(@PathVariable long id) {
        return teamService.removeTeam(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addTeam(@RequestBody Team team) {
        long sportId = team.getSport().getId();
        if (!sportExists(sportId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport Not Found with id " + sportId);
        }
        team.setSport(sportService.getSport(sportId).orElseThrow());
        return teamService.addTeam(team);
    }

    @PutMapping("{id}")
    public boolean updateTeam(@PathVariable long id, @RequestBody Team team) {
        long sportId = team.getSport().getId();
        if (!sportExists(sportId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport Not Found with id " + sportId);
        }
        team.setSport(sportService.getSport(sportId).orElseThrow());
        return teamService.updateTeam(id, team);
    }


    public boolean sportExists(long sportId) {
        return sportService.getSport(sportId).isPresent();
    }
}
