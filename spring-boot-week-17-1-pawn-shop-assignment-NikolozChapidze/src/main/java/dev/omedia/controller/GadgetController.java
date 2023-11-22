package dev.omedia.controller;

import dev.omedia.domain.gadget.Gadget;
import dev.omedia.repository.GadgetRepository;
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
@RequestMapping("items/gadgets")
public class GadgetController {
    private final ItemService<Gadget> service;

    public GadgetController(GadgetRepository repository) {
        this.service = new ItemService<>(repository) {};
    }

    @GetMapping("")
    @Operation(method = "GET",
            description = "Get paginated and sorted gadgets"
    )
    public ResponseEntity<Page<Gadget>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.getPage(pageable));
    }

    @GetMapping("/{id}")
    @Operation(method = "GET",
            description = "Get gadget by id"
    )
    @Cacheable(value = "gadget", key = "#id")
    public ResponseEntity<Gadget> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("")
    @Operation(method = "PUT",
            description = "Update gadget"
    )
    @CachePut(value = "gadget", key = "#updated.id")
    public ResponseEntity<Gadget> update(@RequestBody Gadget updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    @Operation(method = "POST",
            description = "Save new gadget in database"
    )
    public ResponseEntity<Gadget> create(@RequestBody Gadget created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    @Operation(method = "DELETE",
            description = "Delete gadget from database"
    )
    @CacheEvict(value = "gadget", key = "#id")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
