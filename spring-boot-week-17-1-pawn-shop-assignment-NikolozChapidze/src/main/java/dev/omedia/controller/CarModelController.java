package dev.omedia.controller;

import dev.omedia.domain.car.CarMake;
import dev.omedia.domain.car.CarModel;
import dev.omedia.repository.CarModelRepository;
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
@RequestMapping("cars/carModels")
public class CarModelController extends GenericController<CarModel> {

    public CarModelController(CarModelRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<CarModel>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "carModel", key = "#id")
    public ResponseEntity<CarModel> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "carModel", key = "#updated.id")
    public ResponseEntity<CarModel> update(CarModel updated) {
        return super.update(updated);
    }

    @Override
    @CacheEvict(value = "carModel", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
