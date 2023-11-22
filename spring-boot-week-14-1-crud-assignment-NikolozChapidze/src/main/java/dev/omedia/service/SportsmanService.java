package dev.omedia.service;

import dev.omedia.model.Sport;
import dev.omedia.model.Sportsman;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SportsmanService {
    Set<Sportsman> sportsmen = new HashSet<>();

    public Collection<Sportsman> getSportsmen(int page, int pageSize) {
        return this.sportsmen.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Sportsman> getSportsman(long id) {
        return this.sportsmen.stream()
                .filter(sportsman -> sportsman.getId() == id)
                .findAny();
    }

    public boolean addSportsman(final Sportsman sportsman) {
        long maxId = this.sportsmen.stream()
                .map(Sportsman::getId)
                .max(Long::compare)
                .orElse(0L);
        sportsman.setId(maxId + 1);
        return this.sportsmen.add(sportsman);
    }

    public boolean updateSportsman(final long id, final Sportsman sportsman) {
        this.removeSportsman(id);
//        .orElseThrow(UserNotFoundException::new);
        sportsman.setId(id);
        return this.addSportsman(sportsman);
    }

    public Optional<Sportsman> removeSportsman(final long id) {
        Optional<Sportsman> currSportsman = this.sportsmen.stream()
                .filter(sport -> sport.getId() == id)
                .findAny();
        currSportsman.ifPresent(this.sportsmen::remove);
        return currSportsman;
    }
}
