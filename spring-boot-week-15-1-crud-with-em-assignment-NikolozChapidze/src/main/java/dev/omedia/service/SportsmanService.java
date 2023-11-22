package dev.omedia.service;

import dev.omedia.dto.Sportsman;
import dev.omedia.repository.SportsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SportsmanService {

    SportsmanRepository repository;

    @Autowired
    public SportsmanService(SportsmanRepository repository) {
        this.repository = repository;
    }

    public Collection<Sportsman> getSportsmen(final int page, final int pageSize) {
        return repository.getSportsmen(page, pageSize);
    }

    public Optional<Sportsman> getSportsman(final long id) {
        return repository.getSportsman(id);
    }

    public void addSportsman(final Sportsman sportsman) {
        repository.addSportsman(sportsman);
    }

    public Sportsman updateSportsman(final long id, final Sportsman sportsman) {
        return repository.updateSportsman(id, sportsman);
    }

    public int removeSportsman(final long id) {
        return repository.removeSportsman(id);
    }
}
