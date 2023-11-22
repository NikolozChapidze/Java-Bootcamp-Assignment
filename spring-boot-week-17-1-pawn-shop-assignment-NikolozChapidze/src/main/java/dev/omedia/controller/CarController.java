package dev.omedia.controller;

import dev.omedia.domain.Item;
import dev.omedia.domain.car.Car;
import dev.omedia.repository.CarRepository;
import dev.omedia.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("items/cars")
public class CarController {
    private final ItemService<Car> service;

    @Autowired
    public CarController(CarRepository repository) {
        this.service = new ItemService<>(repository) {};
    }

    @GetMapping("")
    @Operation(method = "GET",
            description = "Get paginated and sorted cars"
    )
    public ResponseEntity<Page<Car>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    @Operation(method = "GET",
            description = "Get car by id"
    )
    @Cacheable(value = "car", key = "#id")
    public ResponseEntity<Car> getOne(@PathVariable  Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    @Operation(method = "PUT",
            description = "Update car"
    )
    @CachePut(value = "car", key = "#updated.id")
    public ResponseEntity<Car> update(@RequestBody @Valid Car updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    @Operation(method = "POST",
            description = "Save new car in database"
    )
    public ResponseEntity<Car> create(@RequestBody @Valid Car created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    @Operation(method = "DELETE",
            description = "Delete car from database"
    )
    @CacheEvict(value = "car", key = "#id")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
