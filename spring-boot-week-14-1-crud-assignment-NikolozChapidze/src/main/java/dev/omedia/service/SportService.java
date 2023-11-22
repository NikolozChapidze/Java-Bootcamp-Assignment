package dev.omedia.service;

import dev.omedia.model.Sport;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SportService {
    Set<Sport> sports = new HashSet<>();

    public Collection<Sport> getSports(int page, int pageSize) {
        return this.sports.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Sport> getSport(long id) {
        return this.sports.stream()
                .filter(sport -> sport.getId() == id)
                .findAny();
    }

    public boolean addSport(final Sport sport) {
        long maxId = this.sports.stream()
                .map(Sport::getId)
                .max(Long::compare)
                .orElse(0L);
        sport.setId(maxId + 1);
        return this.sports.add(sport);
    }

    public boolean updateSport(final long id, final Sport sport) {
        this.removeSport(id);
//        .orElseThrow(UserNotFoundException::new);
        sport.setId(id);
        return this.addSport(sport);
    }

    public Optional<Sport> removeSport(final long id) {
        Optional<Sport> currSport = this.sports.stream()
                .filter(sport -> sport.getId() == id)
                .findAny();
        currSport.ifPresent(this.sports::remove);
        return currSport;
    }
}
