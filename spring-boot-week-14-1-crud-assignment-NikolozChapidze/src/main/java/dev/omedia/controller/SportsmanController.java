package dev.omedia.controller;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Sportsman;
import dev.omedia.model.Team;
import dev.omedia.service.SportsmanService;
import dev.omedia.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("sportsmen")
@Slf4j
public class SportsmanController {
    private final TeamService teamService;
    private final SportsmanService sportsmanService;

    @Autowired
    public SportsmanController(TeamService teamService, SportsmanService sportsmanService) {
        this.teamService = teamService;
        this.sportsmanService = sportsmanService;
    }

    @GetMapping
    public Collection<Sportsman> getSportsmen(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return sportsmanService.getSportsmen(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Sportsman getSportsman(@PathVariable(name = "identifier") long id) {
        Optional<Sportsman> sportsmanOptional = sportsmanService.getSportsman(id);
        return sportsmanOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sportsman Not Found with id" + id));
    }

    @DeleteMapping("{id}")
    public Sportsman removeSportsman(@PathVariable long id) {
        return sportsmanService.removeSportsman(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addSportsman(@RequestBody Sportsman sportsman) {
        long teamId = sportsman.getTeam().getId();
        if (!teamExists(teamId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found with id " + teamId);
        }
        sportsman.setTeam(teamService.getTeam(teamId).orElseThrow());
        return sportsmanService.addSportsman(sportsman);
    }

    @PutMapping("{id}")
    public boolean updateSportsman(@PathVariable long id, @RequestBody Sportsman sportsman) {
        long teamId = sportsman.getTeam().getId();
        if (!teamExists(teamId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team Not Found with id " + teamId);
        }
        sportsman.setTeam(teamService.getTeam(teamId).orElseThrow());
        return sportsmanService.updateSportsman(id, sportsman);
    }


    public boolean teamExists(long teamId) {
        return teamService.getTeam(teamId).isPresent();
    }
}
