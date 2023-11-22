package dev.omedia.controller;

import dev.omedia.domain.car.CarMake;
import dev.omedia.repository.CarMakeRepository;
import dev.omedia.repository.CarRepository;
import dev.omedia.repository.GenericRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cars/carMakes")
public class CarMakeController extends GenericController<CarMake> {

    public CarMakeController(CarMakeRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<CarMake>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "carMake", key = "#id")
    public ResponseEntity<CarMake> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "carMake", key = "#updated.id")
    public ResponseEntity<CarMake> update(CarMake updated) {
        return super.update(updated);
    }

    @Override
    public ResponseEntity<CarMake> create(CarMake created) {
        return super.create(created);
    }

    @Override
    @CacheEvict(value = "carMake", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
