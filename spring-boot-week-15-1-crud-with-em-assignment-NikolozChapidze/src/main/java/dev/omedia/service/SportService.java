package dev.omedia.service;

import dev.omedia.dto.Sport;
import dev.omedia.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class SportService {
    private final SportRepository repository;

    @Autowired
    public SportService(SportRepository sportRepo) {
        this.repository = sportRepo;
    }

    public Collection<Sport> getSports(int page, int pageSize) {
        return repository.getSports(page, pageSize);
    }

    public Optional<Sport> getSport(long id) {
        return repository.getSport(id);
    }

    public void addSport(final Sport sport) {
        repository.addSport(sport);
    }

    public Sport updateSport(final long id, final Sport sport) {
        return repository.updateSport(id, sport);

    }

    public int removeSport(final long id) {
        return repository.removeSport(id);
    }
}
