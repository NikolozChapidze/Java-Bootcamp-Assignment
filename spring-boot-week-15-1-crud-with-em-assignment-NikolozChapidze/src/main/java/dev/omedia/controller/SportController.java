package dev.omedia.controller;

import dev.omedia.dto.Sport;
//import dev.omedia.service.SportService;
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
    public int removeSport(@PathVariable long id) {
        return sportService.removeSport(id);
    }

    @PostMapping
    public void addSport(@RequestBody Sport sport) {
        sportService.addSport(sport);
    }

    @PutMapping("{id}")
    public Sport updateSport(@PathVariable long id, @RequestBody Sport sport) {
        return sportService.updateSport(id, sport);
    }
}
