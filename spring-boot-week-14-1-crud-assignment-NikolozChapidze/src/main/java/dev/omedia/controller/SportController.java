package dev.omedia.controller;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Sport;
import dev.omedia.service.SportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("sports")
@Slf4j
public class SportController {
    private final SportService sportService;

    @Autowired
    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping
    public Collection<Sport> getSports(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return sportService.getSports(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Sport getSport(@PathVariable(name = "identifier") long id) {
        Optional<Sport> sportOptional = sportService.getSport(id);
        return sportOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public Sport removeSport(@PathVariable long id) {
        return sportService.removeSport(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addSport(@RequestBody Sport sport) {
        return sportService.addSport(sport);
    }

    @PutMapping("{id}")
    public boolean updateSport(@PathVariable long id, @RequestBody Sport sport) {
        return sportService.updateSport(id, sport);
    }
}
