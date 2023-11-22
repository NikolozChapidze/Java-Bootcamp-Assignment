package dev.omedia.service;

import dev.omedia.model.Courier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourierService {
    Set<Courier> couriers = new HashSet<>();

    public Collection<Courier> getCouriers(int page, int pageSize) {
        return this.couriers.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Courier> getCourier(long id) {
        return this.couriers.stream()
                .filter(courier -> courier.getId() == id)
                .findAny();
    }

    public boolean addCourier(final Courier courier) {
        long maxId = this.couriers.stream()
                .map(Courier::getId)
                .max(Long::compare)
                .orElse(0L);
        courier.setId(maxId + 1);
        return this.couriers.add(courier);
    }

    public boolean updateCourier(final long id, final Courier courier) {
        this.removeCourier(id);
        courier.setId(id);
        return this.addCourier(courier);
    }

    public Optional<Courier> removeCourier(final long id) {
        Optional<Courier> currCourier = this.couriers.stream()
                .filter(courier -> courier.getId() == id)
                .findAny();
        currCourier.ifPresent(this.couriers::remove);
        return currCourier;
    }
}
