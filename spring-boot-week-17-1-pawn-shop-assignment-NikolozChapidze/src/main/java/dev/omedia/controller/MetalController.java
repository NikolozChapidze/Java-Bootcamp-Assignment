package dev.omedia.controller;

import dev.omedia.domain.jewelry.Metal;
import dev.omedia.repository.GenericRepository;
import dev.omedia.repository.MetalRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jewelry/metals")
public class MetalController extends GenericController<Metal>{
    public MetalController(MetalRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<Metal>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "metal", key = "#id")
    public ResponseEntity<Metal> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "metal", key = "#updated.id")
    public ResponseEntity<Metal> update(Metal updated) {
        return super.update(updated);
    }

    @Override
    public ResponseEntity<Metal> create(Metal created) {
        return super.create(created);
    }

    @Override
    @CacheEvict(value = "metal", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
