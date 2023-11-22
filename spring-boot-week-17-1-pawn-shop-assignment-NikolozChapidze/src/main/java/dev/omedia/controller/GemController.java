package dev.omedia.controller;

import dev.omedia.domain.jewelry.Gem;
import dev.omedia.repository.GemRepository;
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
@RequestMapping("jewelry/gems")
public class GemController extends GenericController<Gem>{
    public GemController(GemRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<Gem>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "gem", key = "#id")
    public ResponseEntity<Gem> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "gem", key = "#updated.id")
    public ResponseEntity<Gem> update(Gem updated) {
        return super.update(updated);
    }

    @Override
    public ResponseEntity<Gem> create(Gem created) {
        return super.create(created);
    }

    @Override
    @CacheEvict(value = "gem", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
