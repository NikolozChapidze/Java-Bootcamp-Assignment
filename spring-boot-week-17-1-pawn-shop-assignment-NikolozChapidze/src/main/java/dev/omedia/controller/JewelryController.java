package dev.omedia.controller;

import dev.omedia.domain.jewelry.Jewelry;
import dev.omedia.repository.JewelryRepository;
import dev.omedia.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items/jewelries")
public class JewelryController {
    private final ItemService<Jewelry> service;

    public JewelryController(JewelryRepository repository) {
        this.service = new ItemService<>(repository) {};
    }

    @GetMapping("")
    @Operation(method = "GET",
            description = "Get paginated and sorted jewelries"
    )
    public ResponseEntity<Page<Jewelry>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    @Operation(method = "GET",
            description = "Get jewelry by id"
    )
    @Cacheable(value = "jewelery", key = "#id")
    public ResponseEntity<Jewelry> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    @Operation(method = "PUT",
            description = "Update jewelry"
    )
    @CachePut(value = "jewelery", key = "#updated.id")
    public ResponseEntity<Jewelry> update(@RequestBody Jewelry updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    @Operation(method = "POST",
            description = "Save new jewelry in database"
    )
    public ResponseEntity<Jewelry> create(@RequestBody Jewelry created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    @Operation(method = "DELETE",
            description = "Delete jewelry from database"
    )
    @CacheEvict(value = "jewelery", key = "#id")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
