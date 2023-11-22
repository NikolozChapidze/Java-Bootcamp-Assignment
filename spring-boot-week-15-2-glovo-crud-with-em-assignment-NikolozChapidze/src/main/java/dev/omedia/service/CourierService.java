package dev.omedia.service;

import dev.omedia.model.Courier;
import dev.omedia.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CourierService {
    private final CourierRepository repository;

    @Autowired
    public CourierService(CourierRepository repository) {
        this.repository = repository;
    }

    public Collection<Courier> getCouriers(int page, int pageSize) {
        return repository.getCouriers(page, pageSize);
    }

    public Optional<Courier> getCourier(long id) {
        return repository.getCourier(id);
    }

    public void addCourier(final Courier courier) {
        repository.addCourier(courier);
    }

    public Courier updateCourier(final long id, final Courier courier) {
        return repository.updateCourier(id, courier);

    }

    public int removeCourier(final long id) {
        return repository.removeCourier(id);
    }
}
